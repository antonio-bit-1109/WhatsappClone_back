package com.example.demo.service;

import com.example.demo.dto.requests.CreateAnagraficaDTO;
import com.example.demo.dto.requests.CreateUserDTO;
import com.example.demo.dto.requests.UserRegistrationDTO;
import com.example.demo.entity.Anagrafica;
import com.example.demo.entity.App_User;
import com.example.demo.interfaces.BasicCrud;
import com.example.demo.interfaces.DtoInterface;
import com.example.demo.interfaces.IAuthService;
import com.example.demo.repository.AnagraficaRepository;
import com.example.demo.repository.App_UserRepository;
import com.example.demo.utility.Factory;
import org.springframework.stereotype.Service;

import java.util.List;

// nel tipo di BasicCrud vengono specificati
// tutti gli oggeti che vengono passati
// dal controller al service
@Service
public class AuthService implements IAuthService, BasicCrud<UserRegistrationDTO> {

    private final App_UserRepository appUserRepository;
    private final AnagraficaRepository anagraficaRepository;
    private final Factory factory;

    public AuthService(App_UserRepository appUserRepository,
                       AnagraficaRepository anagraficaRepository,
                       Factory factory) {
        this.appUserRepository = appUserRepository;
        this.anagraficaRepository = anagraficaRepository;
        this.factory = factory;
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
