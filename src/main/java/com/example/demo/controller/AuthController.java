package com.example.demo.controller;

import com.example.demo.dto.requests.appUser.EditUserDTO;
import com.example.demo.dto.requests.appUser.LoginDTO;
import com.example.demo.dto.requests.appUser.UserRegistrationDTO;
import com.example.demo.dto.responses.GetUserDTO;
import com.example.demo.dto.responses.StringResponse;
import com.example.demo.service.AuthService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok(this.authService.login(loginData));
    }

    @GetMapping("/get/{idUser}")
    public ResponseEntity<?> getUser(@NotNull @PathVariable("idUser") Long IdUser) {
        return ResponseEntity.ok(this.authService.get(IdUser));
    }

    // soft delete dell utente
    @GetMapping("/delete/{idUser}")
    public ResponseEntity<StringResponse> deleteUser(@NotNull @PathVariable("idUser") Long IdUser) {
        return ResponseEntity.ok(this.authService.delete(IdUser));
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<GetUserDTO>> getAll() {
        return ResponseEntity.ok(this.authService.getAll());
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editUser(
            @Valid @RequestBody EditUserDTO dataEdit
    ) {
        return ResponseEntity.ok(this.authService.edit(dataEdit));
    }
}
