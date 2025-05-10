package com.example.demo.utility.factory;

import com.example.demo.dto.requests.appUser.CreateAnagraficaDTO;
import com.example.demo.dto.requests.appUser.CreateUserDTO;
import com.example.demo.entity.Anagrafica;
import com.example.demo.entity.App_User;

public interface EntityFactory {
    App_User createEntityUser(CreateUserDTO data);

    Anagrafica createEntityAnagrafica(CreateAnagraficaDTO data);
}
