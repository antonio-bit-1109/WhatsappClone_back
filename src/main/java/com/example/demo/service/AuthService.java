package com.example.demo.service;

import com.example.demo.dto.requests.appUser.CreateAnagraficaDTO;
import com.example.demo.dto.requests.appUser.CreateUserDTO;
import com.example.demo.dto.requests.appUser.LoginDTO;
import com.example.demo.dto.requests.appUser.UserRegistrationDTO;
import com.example.demo.dto.responses.StringResponse;
import com.example.demo.entity.Anagrafica;
import com.example.demo.entity.App_User;
import com.example.demo.interfaces.BasicCrud;
import com.example.demo.interfaces.IAuthService;
import com.example.demo.repository.AnagraficaRepository;
import com.example.demo.repository.App_UserRepository;
import com.example.demo.utility.Factory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

// nel tipo di BasicCrud vengono specificati
// tutti gli oggeti che vengono passati
// dal controller al service
@Service
public class AuthService implements IAuthService, BasicCrud<UserRegistrationDTO> {

    private final App_UserRepository appUserRepository;
    private final AnagraficaRepository anagraficaRepository;
    private final Factory factory;
    private final PasswordEncoder passwordEncoder;

    public AuthService(App_UserRepository appUserRepository,
                       AnagraficaRepository anagraficaRepository,
                       Factory factory,
                       PasswordEncoder passwordEncoder
    ) {
        this.appUserRepository = appUserRepository;
        this.anagraficaRepository = anagraficaRepository;
        this.factory = factory;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void create(UserRegistrationDTO data) {

        // creazione entity user tramite factory
        App_User user = this.factory.createEntityUser(
                new CreateUserDTO(
                        data.getUsername(),
                        data.getPassword()
                ));

        // creazione anagrafica tramite factory
        Anagrafica anagrafica = this.factory.createEntityAnagrafica(
                new CreateAnagraficaDTO(
                        data.getNome(),
                        data.getCognome(),
                        data.getCf(),
                        data.getDataNascita(),
                        data.getLuogoNascita(),
                        data.getTelefono(),
                        user
                )
        );

        // aggiunta dell oggetto anagrafica all entity user (relazione bidirezionale)
        App_User updatedUser = this.factory.addAnagraficaToUser(anagrafica, user);

        // salvataggio entity
        this.anagraficaRepository.save(anagrafica);
        this.appUserRepository.save(updatedUser);
    }

    @Override
    public StringResponse login(LoginDTO dataLogin) {

        if (
                this.userExistByUsername(dataLogin.getUsername()) &&
                        this.passwordMatch(dataLogin.getPassword(), dataLogin.getUsername())
        ) {

            // prendi i dati utente e crea il token

        }

    }

    // controlla se l'utente esiste nel db e ritorna un booleano
    @Override
    public boolean userExistByUsername(String username) {
        Optional<App_User> userOpt = this.appUserRepository.getUserByIsUsername(username);
        return userOpt.isPresent();
    }

    // controlla se la password passata al server corrisponde a quella criptata sul db
    @Override
    public boolean passwordMatch(String rawPassword, String username) {
        return this.passwordEncoder
                .matches(rawPassword,
                        this.getUserByUsername(username).getPassword()
                );
    }

    // ritorna un App_User entity dal database
    @Override
    public App_User getUserByUsername(String username) {
        Optional<App_User> optUser = this.appUserRepository.getUserByIsUsername(username);

        if (optUser.isEmpty()) {
            throw new RuntimeException("utente non presente nel db");
        }

        return optUser.get();
    }


    @Override
    public void edit() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void get() {

    }

    @Override
    public void getAll() {

    }


}
