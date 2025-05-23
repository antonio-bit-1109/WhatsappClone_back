package com.example.demo.entity;


import com.example.demo.interfaces.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class Chat implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // fornito internamente

    @Column(nullable = false)
    private UUID identity; // fornito internamente

    @Column(nullable = false)
    private LocalDateTime createdAt; // fornito internamente

    @ManyToMany
    private List<App_User> participants; // fornito dal front end id utenti (Long)
    
    @OneToMany(mappedBy = "chat")
    private List<Messaggio> listaMessaggi; // alla creazione della chat nessun messaggio.


    // metodo per popolare la lista
    public void addIntoPartecipantsList(App_User user) {
        this.getParticipants().add(user);
    }


    // getter and setter

    public UUID getIdentity() {
        return identity;
    }

    public void setIdentity(UUID identity) {
        this.identity = identity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<App_User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<App_User> participants) {
        this.participants = participants;
    }

    public List<Messaggio> getListaMessaggi() {
        return listaMessaggi;
    }

    public void setListaMessaggi(List<Messaggio> listaMessaggi) {
        this.listaMessaggi = listaMessaggi;
    }
}
