package com.example.demo.dto.requests;

import com.example.demo.entity.App_User;
import com.example.demo.interfaces.DtoInterface;

import java.time.LocalDateTime;

public class CreateAnagraficaDTO implements DtoInterface {

    private String nome;
    private String cognome;
    private String cf;
    private LocalDateTime dataNascita;
    private String luogoNascita;
    private String telefono;
    private App_User user;

    public CreateAnagraficaDTO(String nome, String cognome, String cf, LocalDateTime dataNascita, String luogoNascita, String telefono, App_User user) {
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
        this.dataNascita = dataNascita;
        this.luogoNascita = luogoNascita;
        this.telefono = telefono;
        this.user = user;
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

    public App_User getUser() {
        return user;
    }

    public void setUser(App_User user) {
        this.user = user;
    }
}
