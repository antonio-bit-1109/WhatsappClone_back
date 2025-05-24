package com.example.demo.utility.mapper;

import com.example.demo.dto.responses.ChatGetDTO;
import com.example.demo.dto.responses.MessaggioDto;
import com.example.demo.entity.Anagrafica;
import com.example.demo.entity.App_User;
import com.example.demo.entity.Chat;
import com.example.demo.entity.Messaggio;

public interface IModelMapper<D1, D2> {
    D1 fromEntityToDto(App_User user, Anagrafica anagrafica);

    D2 fromEntityToDto_generic(App_User user, Anagrafica anagrafica);

    ChatGetDTO fromEntityToDto(Chat chat, App_User user);

    MessaggioDto fromEntityToDto(Messaggio msg);


}
