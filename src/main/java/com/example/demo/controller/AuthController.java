package com.example.demo.controller;

import com.example.demo.dto.requests.appUser.EditUserDTO;
import com.example.demo.dto.requests.appUser.LoginDTO;
import com.example.demo.dto.requests.appUser.UserRegistrationDTO;
import com.example.demo.dto.responses.GetUserDTO;
import com.example.demo.dto.responses.StringResponse;
import com.example.demo.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// tag usato per descrivere a cosa serve questo controller, che poi sarà riflettuto
// nella pagina di swagger come descrizione del controller
@Tag(
        name = "CRUD REST API for user in WhatsApp Clone 'Puzzapp'",
        description = "services used for CREATE USER, EDIT, LOGIN, GET GETALL the users in the application"
)
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // documentare il singolo controller con annotation @Operation
    @Operation(summary = "create a new account in the application",
            description = "register a new user inside PuzzApp application")

    // specificare il tipo di risposta del controller, o piu di uno se presenti
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP STATUS REGISTERED SUCCESSFULLY"
            )
    )
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
