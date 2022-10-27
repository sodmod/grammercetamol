package com.grammercetamol.controllers;

import com.grammercetamol.mail.mailtoken_confirmation.Email_ConfirmationService;
import com.grammercetamol.models.services.Services;
import com.grammercetamol.payload.request.LoginRequest;
import com.grammercetamol.payload.request.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    Services services;

    @Autowired
    Email_ConfirmationService email_confirmationService;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody @Validated SignupRequest signupRequest){
        return services.registerUser(signupRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated LoginRequest loginRequest){
        return services.authenticateUser(loginRequest);
    }

    @GetMapping("/confirm/token/{token}")
    public ResponseEntity<?> confirmOtp(@PathVariable("token") String token){
        return null;
    }

}
