package com.grammercetamol.payload.request;

import com.sun.istack.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class SignupRequest {
    private String firstname;
    private String lastname;
    private String othername;

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private Set<String> role;

    @NotNull
    private String password;
    private String dob;
}
