package com.example.demo.utility.mapper;

import com.example.demo.dto.responses.*;
import com.example.demo.entity.Anagrafica;
import com.example.demo.entity.App_User;
import com.example.demo.entity.Chat;
import com.example.demo.entity.Messaggio;
import org.springframework.stereotype.Component;


@Component
public class ModelMapper implements IModelMapper<GetUserDTO, MinimalUserInfoChatDTO, ExtraMinimalUserInfo> {


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

    @Override
    public MinimalUserInfoChatDTO fromEntityToDto_generic(App_User user, Anagrafica anagrafica) {
        MinimalUserInfoChatDTO dto = new MinimalUserInfoChatDTO();
        dto.setCognome(anagrafica.getCognome());
        dto.setProfileImage(user.getProfileImage());
        dto.setNome(anagrafica.getNome());
        dto.setUsername(user.getUsername());
        dto.setTelefono(anagrafica.getTelefono());
        dto.setEmail(user.getEmail());
        return dto;
    }

    @Override
    public ExtraMinimalUserInfo fromEntityToMinimal(App_User user, Anagrafica anagrafica) {
        ExtraMinimalUserInfo dto = new ExtraMinimalUserInfo();
        dto.setId(user.getId());
        dto.setName(anagrafica.getNome());
        dto.setSurname(anagrafica.getCognome());
        dto.setUsername(user.getUsername());
        return dto;
    }


    /**
     * Transforms a Chat entity into a ChatGetDTO data transfer object.
     *
     * @param chat The Chat entity to be converted into a ChatGetDTO.
     * @return A ChatGetDTO containing the data mapped from the provided Chat entity.
     */
    @Override
    public ChatGetDTO fromEntityToDto(Chat chat, App_User user) {
        ChatGetDTO c = new ChatGetDTO();
        c.setChatIdentity(chat.getIdentity());
        c.setCreatedAt(chat.getCreatedAt());
        c.setMessaggi(chat.getListaMessaggi()
                .stream()
                .map(this::fromEntityToDto)
                .toList()
        );
        c.setListaPartecipanti(chat.getParticipants().stream()
                .filter(appUser -> !appUser.equals(user))
                .map(App_User -> fromEntityToDto_generic(App_User, App_User.getAnagrafica()))
                .toList());
        return c;
    }

    @Override
    public MessaggioDto fromEntityToDto(Messaggio msg) {
        return new MessaggioDto(msg.getSendAtTime(), msg.getMessageStatus(), msg.getContent(), msg.getOwner().getUsername());
    }


}
