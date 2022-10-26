package com.grammercetamol.payload.request;

import com.sun.istack.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class SignupRequest {
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String othername;
    @NotNull
    private String username;
    @NotNull
    private Set<String> role;
    @NotNull
    private String password;
    @NotNull
    private String dob;
}
