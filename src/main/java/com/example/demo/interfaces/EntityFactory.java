package com.example.demo.interfaces;

import com.example.demo.dto.requests.CreateAnagraficaDTO;
import com.example.demo.dto.requests.CreateUserDTO;
import com.example.demo.entity.Anagrafica;
import com.example.demo.entity.App_User;

import java.time.LocalDateTime;

public interface EntityFactory {
    App_User createEntityUser(CreateUserDTO data);

    Anagrafica createEntityAnagrafica(CreateAnagraficaDTO data);
}
