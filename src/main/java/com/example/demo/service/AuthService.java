package com.example.demo.service;

import com.example.demo.dto.requests.appUser.*;
import com.example.demo.dto.responses.GetUserDTO;
import com.example.demo.dto.responses.StringResponse;
import com.example.demo.entity.Anagrafica;
import com.example.demo.entity.App_User;
import com.example.demo.interfaces.BasicCrud;
import com.example.demo.interfaces.IAuthService;
import com.example.demo.repository.AnagraficaRepository;
import com.example.demo.repository.App_UserRepository;
import com.example.demo.security.GenerateToken;
import com.example.demo.utility.adapter.CustomUserDetail;
import com.example.demo.utility.exception.InvalidCredentialsException;
import com.example.demo.utility.exception.UserNotFound;
import com.example.demo.utility.factory.Factory;
import com.example.demo.utility.mapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// nel tipo di BasicCrud vengono specificati
// tutti gli oggetti che vengono passati
// dal controller al service
@Service
public class AuthService implements IAuthService,
        BasicCrud<UserRegistrationDTO, Long, GetUserDTO, StringResponse, EditUserDTO> {

    private final App_UserRepository appUserRepository;
    private final AnagraficaRepository anagraficaRepository;
    private final Factory factory;
    private final PasswordEncoder passwordEncoder;
    // serve per eseguire il processo di autenticazione dell utente che sta facendo login
    private final AuthenticationManager authenticationManager;

    // classe per la generazione del token
    private final GenerateToken generateToken;

    // implementazione concreta del mapper appUser
    private final ModelMapper modelMapper;

    public AuthService(App_UserRepository appUserRepository,
                       AnagraficaRepository anagraficaRepository,
                       Factory factory,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       GenerateToken generateToken,
                       ModelMapper modelMapper

    ) {
        this.appUserRepository = appUserRepository;
        this.anagraficaRepository = anagraficaRepository;
        this.factory = factory;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.generateToken = generateToken;
        this.modelMapper = modelMapper;
    }


    @Override
    public void create(UserRegistrationDTO data) {

        // creazione entity user tramite factory
        App_User user = this.factory.createEntityUser(
                new CreateUserDTO(
                        data.getUsername(),
                        data.getPassword()
                ));

        App_User savedUser = this.appUserRepository.save(user);
        // creazione anagrafica tramite factory
        Anagrafica anagrafica = this.factory.createEntityAnagrafica(
                new CreateAnagraficaDTO(
                        data.getNome(),
                        data.getCognome(),
                        data.getCf().toUpperCase(),
                        data.getDataNascita(),
                        data.getLuogoNascita(),
                        "+39" + data.getTelefono(),
                        savedUser
                )
        );

        Anagrafica updatedAnagrafica = this.anagraficaRepository.save(anagrafica);
        // aggiunta dell oggetto anagrafica all entity user (relazione bidirezionale)
        App_User updatedUser = this.factory.addAnagraficaToUser(updatedAnagrafica, savedUser);

        // salvataggio entity
        this.anagraficaRepository.save(updatedAnagrafica);
        this.appUserRepository.save(updatedUser);
    }

    @Override
    public StringResponse edit(EditUserDTO dataEdit) {
        App_User user = this.getUserById(dataEdit.getIdUser());
        Anagrafica userAnagrafica = user.getAnagrafica();
        userAnagrafica.setTelefono(dataEdit.getTelefono());
        userAnagrafica.setNome(dataEdit.getNome());
        userAnagrafica.setCognome(dataEdit.getCognome());
        userAnagrafica.setLuogoDiNascita(dataEdit.getLuogoDiNascita());
        this.anagraficaRepository.save(userAnagrafica);
        return new StringResponse("dati utente salvati con successo.");
    }

    @Override
    public StringResponse login(LoginDTO dataLogin) {

        if (
                this.userExistByUsername(dataLogin.getUsername()) &&
                        this.passwordMatch(dataLogin.getPassword(), dataLogin.getUsername())
        ) {

            // mi collego alla classe iniettata come bean nella configuration per ottenere
            // la "porta" a cui passare i dati dell utente che si sta caricando per controllare che siano validi
            // metodo per l'autenticazione utente
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dataLogin.getUsername(), dataLogin.getPassword())
            );

            CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
            App_User user = userDetails.getAppUser();
            String token = this.generateToken.generateToken(user);
            return new StringResponse(token);
        }


        throw new InvalidCredentialsException("credenziali invalide o errate.");
    }

    @Override
    public StringResponse delete(Long idUser) {
        App_User user = this.getUserById(idUser);

        if (this.userAlreadyInactive(user)) {
            return new StringResponse("utente già disattivato");
        }

        user.setIsEnabled(false);
        this.appUserRepository.save(user);
        return new StringResponse("utente disattivato con successo");
    }

    @Override
    public GetUserDTO get(Long idUser) {
        // prendo le entity da mappare
        App_User user = this.getUserById(idUser);
        Anagrafica anagrafica = this.anagraficaRepository.getAnagraficaByAppUserID(user.getId());

        // le passo al mapper per essere mappate
        return this.modelMapper.fromEntityToDto(user, anagrafica);

    }

    @Override
    public List<GetUserDTO> getAll() {

        return this.appUserRepository.findAll()
                .stream()
                .map(user -> {
                    Anagrafica anagrafica = this.anagraficaRepository
                            .getAnagraficaByAppUserID(user.getId());
                    return this.modelMapper.fromEntityToDto(user, anagrafica);
                }).toList();
    }


    // METODI ACCESSORI PER LE OPERAZIONI EFFETTUATE

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
            throw new UserNotFound("utente non presente nel db");
        }

        return optUser.get();
    }

    @Override
    public App_User getUserById(Long idUser) {
        Optional<App_User> optUser = this.appUserRepository.getApp_UserById(idUser);

        if (optUser.isEmpty()) {
            throw new UserNotFound("utente non trovato");
        }

        return optUser.get();
    }

    // check if the user is inactive
    @Override
    public boolean userAlreadyInactive(App_User user) {
        return !user.getIsEnabled();
    }

}
