package com.example.demo.utility.adapter;

import com.example.demo.entity.App_User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

// per funzionare correttamente l autenticazione con auth2.0 prevede di adattare
// l 'entity user alla classe UserDetails
// da fornire poi al servizio di autenticazione di OAUth2
public class CustomUserDetail implements UserDetails {


    private final App_User appUser;

    public CustomUserDetail(App_User user) {
        this.appUser = user;
    }

    // getter standard
    public App_User getAppUser() {
        return this.appUser;
    }

    // metodi passati dall interfaccia UserDetails da definire


    // per la gestione dei ruoli
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    // get password utente
    @Override
    public String getPassword() {
        return appUser.getPassword();
    }

    // get username utente
    @Override
    public String getUsername() {
        return appUser.getUsername();
    }

    //    Se false, l’account è scaduto → login fallisce
    @Override
    public boolean isAccountNonExpired() {
//        return UserDetails.super.isAccountNonExpired();
        return appUser.getIsAccountNotExpired();
    }

    //    Se false, l’account è bloccato (es. troppi tentativi falliti)
    @Override
    public boolean isAccountNonLocked() {
//        return UserDetails.super.isAccountNonLocked();
       return appUser.getIsAccountNotLocked();
    }

    //    Se false, le credenziali sono scadute (es. password da aggiornare)
    @Override
    public boolean isCredentialsNonExpired() {
//        return UserDetails.super.isCredentialsNonExpired();
        return appUser.getIsCredentialsNonExpired();
    }

    //    Se false, l’utente è disabilitato (es. non ha ancora confermato email)
    @Override
    public boolean isEnabled() {
//        return UserDetails.super.isEnabled();
        return appUser.getIsEnabled();
    }
}
