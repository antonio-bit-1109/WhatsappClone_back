package com.example.demo.utility.factory;

import com.example.demo.dto.requests.appUser.CreateAnagraficaDTO;
import com.example.demo.dto.requests.appUser.CreateUserDTO;
import com.example.demo.dto.responses.GetUserDTO;
import com.example.demo.entity.Anagrafica;
import com.example.demo.entity.App_User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Factory implements EntityFactory {

    private final PasswordEncoder passwordEncoder;

    public Factory(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public App_User createEntityUser(CreateUserDTO data) {
        App_User user = new App_User();
        user.setUsername(data.getUsername());
        user.setPassword(this.passwordEncoder.encode(data.getPassword()));
        user.setAccountNotExpired(true);
        user.setAccountNotLocked(true);
        user.setIsEnabled(true);
        user.setCredentialsNonExpired(true);
        return user;
    }

    @Override
    public Anagrafica createEntityAnagrafica(CreateAnagraficaDTO data) {
        Anagrafica a = new Anagrafica();
        a.setNome(data.getNome());
        a.setCognome(data.getCognome());
        a.setCf(data.getCf());
        a.setDataNascita(data.getDataNascita());
        a.setLuogoDiNascita(data.getLuogoNascita());
        a.setTelefono(data.getTelefono());
        a.setUser(data.getUser());
        return a;
    }


    public App_User addAnagraficaToUser(
            Anagrafica anagrafica,
            App_User user
    ) {
        user.setAnagrafica(anagrafica);
        return user;
    }
}
