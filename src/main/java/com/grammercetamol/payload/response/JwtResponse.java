package com.grammercetamol.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse extends Response{
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;

    private List<String> roles;

    public JwtResponse(String accessToken, Long id, String username, List<String> roles, String response, int responseCode){
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.roles = roles;
        super.message = response;
        super.responseCode = responseCode;
    }
}
