package com.example.demo.controller;

import com.example.demo.dto.requests.UserRegistrationDTO;
import com.example.demo.interfaces.IAuthService;
import com.example.demo.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody UserRegistrationDTO dataRegistration
    ) {
        this.authService.create(dataRegistration);
        return null;
    }


}
