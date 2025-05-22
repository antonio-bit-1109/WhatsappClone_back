package com.example.demo.dto.requests.appUser;

import com.example.demo.interfaces.DtoInterface;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EditUserDTO implements DtoInterface {

    @NotNull
    private Long idUser;

    @NotNull
    @NotBlank(message = "il nome non può essere vuoto.")
    private String nome;
    @NotNull
    @NotBlank(message = "il cognome non può essere vuoto.")
    private String cognome;
    @NotNull
    @NotBlank(message = "il luogo di nascita non può essere vuoto.")
    private String LuogoDiNascita;
    @NotNull
    @NotBlank(message = "il telefono non può essere vuoto.")
    private String telefono;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
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

    public String getLuogoDiNascita() {
        return LuogoDiNascita;
    }

    public void setLuogoDiNascita(String luogoDiNascita) {
        LuogoDiNascita = luogoDiNascita;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
