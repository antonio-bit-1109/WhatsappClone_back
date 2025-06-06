package com.example.demo.utility.mapper;

import com.example.demo.dto.responses.ChatGetDTO;
import com.example.demo.dto.responses.MessaggioDto;
import com.example.demo.entity.*;

public interface IModelMapper<D1, D2, D3, D4> {
    D1 fromEntityToDto(App_User user, Anagrafica anagrafica);

    D2 fromEntityToDto_generic(App_User user, Anagrafica anagrafica);

    D3 fromEntityToMinimal(App_User user, Anagrafica anagrafica);

    D4 fromEntityToDto(StoreMessages entityMsg);

    ChatGetDTO fromEntityToDto(Chat chat, App_User user);

    MessaggioDto fromEntityToDto(Messaggio msg);


}
