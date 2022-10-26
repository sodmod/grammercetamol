package com.grammercetamol.models.services;

import com.grammercetamol.mail.mailtoken_confirmation.Email_ConfirmationService;
import com.grammercetamol.models.AppUsers;
import com.grammercetamol.models.ERole;
import com.grammercetamol.models.Role;
import com.grammercetamol.models.implementation.UserDetailsImpl;
import com.grammercetamol.models.repository.AppUserRepository;
import com.grammercetamol.models.repository.RoleRepository;
import com.grammercetamol.payload.request.LoginRequest;
import com.grammercetamol.payload.request.SignupRequest;
import com.grammercetamol.payload.response.JwtResponse;
import com.grammercetamol.payload.response.MessageResponse;
import com.grammercetamol.security.jwt.JwtUtils;
import com.grammercetamol.security.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.grammercetamol.payload.response.StaticStrings.StaticStrings.*;

@Component
@Service
public class Services {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    Email_ConfirmationService email_confirmationService;



    public ResponseEntity<?> registerUser(@Validated @NotNull SignupRequest signupRequest) {

        if (appUserRepository.existsByUsername(signupRequest.getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already is already taken!", 0));
        }

//        Create new appUsers's account

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null){
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role ->{
                switch (role) {
                    case "admin" -> {
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                    }
                    case "student" -> {
                        Role studentRole = roleRepository.findByName(ERole.ROLE_STUDENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(studentRole);
                    }
                    case "mod" -> {
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                    }
                    default -> {
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                    }
                }
            });
        }
        try {
            Date dob = new SimpleDateFormat("dd/MM/yyyy").parse(signupRequest.getDob());
            AppUsers appUsers = new AppUsers(
                    signupRequest.getFirstname(),
                    signupRequest.getLastname(),
                    signupRequest.getOthername(),
                    signupRequest.getUsername(),
                    passwordEncoder
                            .bCryptPasswordEncoder()
                            .encode(
                                    signupRequest
                                            .getPassword()
                            ),
                    dob
            );



            email_confirmationService.SaveAndSendToken(
                    signupRequest.getUsername(),
                    signupRequest.getFirstname(),
                    signupRequest.getLastname()
            );

            appUsers.setRoles(roles);
            appUserRepository.save(appUsers);
        } catch (ParseException e){

        }



        return  ResponseEntity.ok(new MessageResponse(REGISTRATION_SUCCESSFULLY, SUCCESSCODE));
    }

    public ResponseEntity<?> authenticateUser(@Validated @NotNull LoginRequest loginRequest){

        AppUsers appUsers = appUserRepository.findByUsername(loginRequest.getUsername()).orElseThrow();
        System.out.println(appUsers);



        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new JwtResponse(
                        jwt,
                        userDetails.getId(),
                        userDetails.getUsername(),
                        roles,
                        LOGIN_SUCCESS,
                        SUCCESSCODE));

    }

}
