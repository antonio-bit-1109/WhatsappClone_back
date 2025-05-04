package com.example.demo.entity;

import com.example.demo.interfaces.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Anagrafica implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime DataNascita;

    @Column(nullable = false)
    private String luogoDiNascita;

    @Column(nullable = false, unique = true, length = 16)
    private String cf;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @OneToOne(mappedBy = "anagrafica")
    private App_User appUser;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataNascita() {
        return DataNascita;
    }

    public void setDataNascita(LocalDateTime dataNascita) {
        DataNascita = dataNascita;
    }

    public String getLuogoDiNascita() {
        return luogoDiNascita;
    }

    public void setLuogoDiNascita(String luogoDiNascita) {
        this.luogoDiNascita = luogoDiNascita;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public App_User getUser() {
        return appUser;
    }

    public void setUser(App_User appUser) {
        this.appUser = appUser;
    }
}
