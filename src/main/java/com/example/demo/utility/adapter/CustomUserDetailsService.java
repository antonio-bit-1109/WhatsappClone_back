package com.example.demo.utility.adapter;

import com.example.demo.entity.App_User;
import com.example.demo.repository.App_UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

// classe collegata al repository di user che legge le entittà user dal db
// il servizio sarà poi passato alla configurazione di auth2 per leggere automaticamente dal db i dati utente e se corretti
// confermare il login e fornire il token
public class CustomUserDetailsService implements UserDetailsService {

    private final App_UserRepository appUserRepository;

    public CustomUserDetailsService(App_UserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }


    // carica l utente, preso tramite username,
//    direttamente dal db
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<App_User> userOpt = this.appUserRepository.getUserByIsUsername(username);

        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Utente non trovato: %s", username));
        }

        return new CustomUserDetail(userOpt.get());
    }
}
