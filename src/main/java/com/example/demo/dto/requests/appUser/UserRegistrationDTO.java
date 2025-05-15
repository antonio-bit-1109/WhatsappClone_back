package com.example.demo.dto.requests.appUser;

import com.example.demo.interfaces.DtoInterface;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class UserRegistrationDTO implements DtoInterface {

    @NotNull(message = "lo username non può essere null.")
    @NotBlank(message = "lo username non può essere vuoto.")
    private String username;

    @NotNull(message = "la password non può essere null")
    @NotBlank(message = "la password non può essere vuota")
    private String password;

    @NotNull(message = "la mail non può essere null")
    @NotBlank(message = "la mail non può essere vuota")
    @Email(message = "formato mail non valido.")
    private String email;

    @NotNull(message = "il nome non può essere null")
    @NotBlank(message = "il nome non può essere vuoto")
    private String nome;

    @NotNull(message = "il cognome non può essere null")
    @NotBlank(message = "il cognome non può essere vuoto")
    private String cognome;

    @NotNull(message = "il codice fiscale non può essere null")
    @NotBlank(message = "il codice fiscale non può essere vuoto")
    private String cf;

    @NotNull(message = "la data di nascita non può essere null")
    private LocalDateTime dataNascita;

    @NotNull(message = "il luogo di nascita non può essere null")
    @NotBlank(message = "il luogo di nascita non può essere vuoto")
    private String luogoNascita;

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
