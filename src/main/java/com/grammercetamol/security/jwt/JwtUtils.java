package com.grammercetamol.security.jwt;

import com.grammercetamol.models.implementation.UserDetailsImpl;
import io.jsonwebtoken.*;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;

@Data
@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${jwt.app.jwtSecret}")
    private String jwtSecret;
    @Value("${jwt.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication){
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().setClaims(claims)
                .setSubject(
                        userPrincipal.getUsername()
                )
                .setIssuedAt(
                        new Date()
                )
                .setExpiration(
                        new Date(
                                (new Date())
                                        .getTime() + jwtExpirationMs
                        )
                )
                .signWith(
                        HS512, jwtSecret
                )
                .compact();
    }

    public String getUserNameFromJwtToken(String token){
        return Jwts.parser()
                .setSigningKey(
                        jwtSecret
                )
                .parseClaimsJws(
                        token
                )
                .getBody()
                .getSubject();
    }

    public boolean validateJwtToken(String authToken){
        try {
            Jwts
                    .parser()
                    .setSigningKey(
                            jwtSecret
                    ).
                    parseClaimsJws(
                            authToken
                    );
            return true;

        } catch (SignatureException e){
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e){
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e){
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e){
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e){
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

}
