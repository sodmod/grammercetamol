package com.grammercetamol.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse extends Response{
    private String token;

    private String type = "Bearer";

    private Long id;

    private String username;

    private String email;

    private List<String> roles;

    public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles, String response, int responseCode){
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        super.message = response;
        super.responseCode = responseCode;
    }
}
