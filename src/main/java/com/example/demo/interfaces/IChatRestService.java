package com.example.demo.interfaces;

import com.example.demo.dto.requests.chatMessage.ChatMessageDTO;
import com.example.demo.entity.Chat;

import java.util.Optional;
import java.util.UUID;

public interface IChatRestService {

    Chat save(Chat chat);

    void addMessageToChat(ChatMessageDTO messageDTO);

    Chat getChatFromIdentity(UUID identity);

    Optional<String> checkIfpartIsEmail(String valueToControl);

    Optional<String> checkIfPartIdUserId(String valueTocontrol);

    Optional<String> checkIfIdentity(String ValueToControl);
}
