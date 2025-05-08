package com.example.demo.controller;

import com.example.demo.dto.requests.appUser.LoginDTO;
import com.example.demo.dto.requests.appUser.UserRegistrationDTO;
import com.example.demo.dto.responses.StringResponse;
import com.example.demo.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/register")
    public ResponseEntity<StringResponse> register(
            @Valid @RequestBody UserRegistrationDTO dataRegistration
    ) {
        this.authService.create(dataRegistration);
        return ResponseEntity.ok(new StringResponse("registrazione effettuata con successo."));
    }


    @PostMapping("/login")
    public ResponseEntity<StringResponse> login(
            @Valid @RequestBody LoginDTO loginData
    ) {
      return ResponseEntity.ok(this.authService.login(loginData))  ;
    }
}
