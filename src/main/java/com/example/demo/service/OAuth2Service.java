package com.example.demo.service;

import com.example.demo.entity.Anagrafica;
import com.example.demo.entity.App_User;
import com.example.demo.enums.AuthProvider;
import com.example.demo.repository.AnagraficaRepository;
import com.example.demo.repository.App_UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class OAuth2Service {


    private final App_UserRepository userRepository;
    private final AnagraficaRepository anagraficaRepository;

    public OAuth2Service(App_UserRepository userRepository, AnagraficaRepository anagraficaRepository) {
        this.userRepository = userRepository;
        this.anagraficaRepository = anagraficaRepository;
    }

    public App_User processOAuth2User(OAuth2User oauth2User) {
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("given_name");
        String surname = oauth2User.getAttribute("family_name");
        String sub = oauth2User.getAttribute("sub"); // ID Google univoco

        Optional<App_User> existingUser = userRepository.getUserByIsUsername(email);

        if (existingUser.isPresent()) {
            if (existingUser.get().getProvider() == AuthProvider.GOOGLE) {
                return existingUser.get();
            } else {
                throw new RuntimeException("Account esistente con email " + email);
            }
        }

        // Crea nuovo utente
        App_User newUser = new App_User();
        newUser.setUsername(email);
        newUser.setEmail(email);
        newUser.setProvider(AuthProvider.GOOGLE);
        newUser.setProviderId(sub);
        newUser.setRole("USER");
        newUser.setIsEnabled(true);
        newUser.setAccountNotExpired(true);
        newUser.setAccountNotLocked(true);
        newUser.setCredentialsNonExpired(true);

        App_User savedUser = userRepository.save(newUser);

        // Crea anagrafica
        Anagrafica anagrafica = new Anagrafica();
        anagrafica.setNome(name);
        anagrafica.setCognome(surname);
        anagrafica.setUser(savedUser);
        Anagrafica savedAnagrafica = anagraficaRepository.save(anagrafica);

        newUser.setAnagrafica(savedAnagrafica);

        return savedUser;
    }

}
