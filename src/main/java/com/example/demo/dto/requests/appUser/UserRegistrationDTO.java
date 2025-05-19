package com.example.demo.dto.requests.appUser;

import com.example.demo.interfaces.DtoInterface;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Schema(
        name = "data registration user",
        description = "data needed for new user registration"
)
public class UserRegistrationDTO implements DtoInterface {

    @Schema(description = "username", example = "pincoPallo")
    @NotEmpty(message = "lo username non può essere null or empty.")
    private String username;

    @Schema(description = "user's password", example = "Password123!")
    @NotNull(message = "la password non può essere null")
    @NotBlank(message = "la password non può essere vuota")
    private String password;

    @Schema(description = "user's email", example = "prova@prova.it!")
    @NotNull(message = "la mail non può essere null")
    @NotBlank(message = "la mail non può essere vuota")
    @Email(message = "formato mail non valido.")
    private String email;

    @Schema(description = "user's first name", example = "Marcello")
    @NotNull(message = "il nome non può essere null")
    @NotBlank(message = "il nome non può essere vuoto")
    private String nome;

    @Schema(description = "user's last name", example = "Schiavoni")
    @NotNull(message = "il cognome non può essere null")
    @NotBlank(message = "il cognome non può essere vuoto")
    private String cognome;

    @Schema(description = "user's fiscal code", example = "MRCSVN80A01H501Z")
    @NotNull(message = "il codice fiscale non può essere null")
    @NotBlank(message = "il codice fiscale non può essere vuoto")
    private String cf;

    @Schema(description = "user's birth date", example = "1980-01-01T10:15:30")
    @NotNull(message = "la data di nascita non può essere null")
    private LocalDateTime dataNascita;

    @Schema(description = "user's birth place", example = "Roma")
    @NotNull(message = "il luogo di nascita non può essere null")
    @NotBlank(message = "il luogo di nascita non può essere vuoto")
    private String luogoNascita;

    @Schema(description = "user's phone number", example = "3331234567")
    @NotNull(message = "il telefono non può essere null")
    @NotBlank(message = "il telefono non può essere vuoto")
    private String telefono;

    public UserRegistrationDTO(String username,
                               String password,
                               String nome,
                               String cognome,
                               String cf,
                               LocalDateTime dataNascita,
                               String luogoNascita,
                               String telefono,
                               String email) {
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
        this.dataNascita = dataNascita;
        this.luogoNascita = luogoNascita;
        this.telefono = telefono;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public LocalDateTime getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDateTime dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getLuogoNascita() {
        return luogoNascita;
    }

    public void setLuogoNascita(String luogoNascita) {
        this.luogoNascita = luogoNascita;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
