package com.example.demo.dto.responses;

import com.example.demo.entity.Messaggio;
import com.example.demo.interfaces.DtoInterface;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ChatGetDTO implements DtoInterface {
    private List<MessaggioDto> messaggi;
    private List<MinimalUserInfoChatDTO> listaPartecipanti;
    private LocalDateTime createdAt;
    private UUID chatIdentity;

    public List<MessaggioDto> getMessaggi() {
        return messaggi;
    }

    public void setMessaggi(List<MessaggioDto> messaggi) {
        this.messaggi = messaggi;
    }

    public void setListaPartecipanti(List<MinimalUserInfoChatDTO> listaPartecipanti) {
        this.listaPartecipanti = listaPartecipanti;
    }

    public List<MinimalUserInfoChatDTO> getListaPartecipanti() {
        return listaPartecipanti;
    }
    

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UUID getChatIdentity() {
        return chatIdentity;
    }

    public void setChatIdentity(UUID chatIdentity) {
        this.chatIdentity = chatIdentity;
    }
}
