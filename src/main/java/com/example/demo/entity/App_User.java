package com.example.demo.entity;

import com.example.demo.enums.AuthProvider;
import com.example.demo.interfaces.BaseEntity;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class App_User implements BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private boolean isAccountNotExpired;

    @Column(nullable = false)
    private boolean isAccountNotLocked;

    @Column(nullable = false)
    private boolean isCredentialsNonExpired;

    @Column(nullable = true)
    private String profileImage;

    @Column(nullable = false)
    private boolean isEnabled;

    @Column(nullable = false)
    private String role;

    @OneToOne(cascade = CascadeType.ALL)
    private Anagrafica anagrafica;

    @OneToMany(mappedBy = "owner")
    private List<Messaggio> listaMessaggi;

    @ManyToMany
    private List<Chat> listaChats;

    @Enumerated(EnumType.STRING)
    private AuthProvider provider = AuthProvider.LOCAL;

    private String providerId;

    public List<Chat> getListaChats() {
        return listaChats;
    }

    public void setListaChats(List<Chat> listaChats) {
        this.listaChats = listaChats;
    }

    public List<Messaggio> getListaMessaggi() {
        return listaMessaggi;
    }

    public void setListaMessaggi(List<Messaggio> listaMessaggi) {
        this.listaMessaggi = listaMessaggi;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getIsAccountNotExpired() {
        return isAccountNotExpired;
    }

    public void setAccountNotExpired(boolean accountNotExpired) {
        isAccountNotExpired = accountNotExpired;
    }

    public boolean getIsAccountNotLocked() {
        return isAccountNotLocked;
    }

    public void setAccountNotLocked(boolean accountNotLocked) {
        isAccountNotLocked = accountNotLocked;
    }

    public boolean getIsCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Anagrafica getAnagrafica() {
        return anagrafica;
    }

    public void setAnagrafica(Anagrafica anagrafica) {
        this.anagrafica = anagrafica;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
