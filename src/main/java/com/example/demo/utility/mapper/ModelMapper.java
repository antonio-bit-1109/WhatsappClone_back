package com.example.demo.utility.mapper;

import com.example.demo.dto.responses.GetUserDTO;
import com.example.demo.entity.Anagrafica;
import com.example.demo.entity.App_User;
import org.springframework.stereotype.Component;


@Component
public class ModelMapper implements IModelMapper {


    // ritornare una response che sia le get di un singolo utente
    // contenente dati di user e anagrafica
    @Override
    public GetUserDTO fromEntityToDto(App_User user, Anagrafica anagrafica) {
        GetUserDTO dto = new GetUserDTO();
        dto.setCf(anagrafica.getCf());
        dto.setCognome(anagrafica.getCognome());
        dto.setDataNascita(anagrafica.getDataNascita().toString());
        dto.setTelefono(anagrafica.getTelefono());
        dto.setUsername(user.getUsername());
        dto.setNome(anagrafica.getNome());
        dto.setCognome(anagrafica.getCognome());
        dto.setLuogoNascita(anagrafica.getLuogoDiNascita());
        return dto;
    }

}
