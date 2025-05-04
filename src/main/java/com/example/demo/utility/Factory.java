package com.example.demo.utility;

import com.example.demo.dto.requests.CreateAnagraficaDTO;
import com.example.demo.dto.requests.CreateUserDTO;
import com.example.demo.entity.Anagrafica;
import com.example.demo.entity.App_User;
import com.example.demo.interfaces.EntityFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class Factory implements EntityFactory {

    @Override
    public App_User createEntityUser(CreateUserDTO data) {
        App_User user = new App_User();
        user.setUsername(data.getUsername());
        user.setPassword(data.getPassword());
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
