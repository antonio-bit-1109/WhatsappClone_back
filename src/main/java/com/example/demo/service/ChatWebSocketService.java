package com.example.demo.service;

import com.example.demo.dto.requests.chatMessage.ChatMessageDTO;
import com.example.demo.entity.Chat;
import com.example.demo.interfaces.IChatWebSocketService;
import com.example.demo.repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChatWebSocketService implements IChatWebSocketService {

    private final ChatRepository chatRepository;

    public ChatWebSocketService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }


    /**
     * Retrieves the UUID identity of a chat based on the provided chat data.
     *
     * @param dataChat the data transfer object containing the chat details,
     *                 including the ID of the chat to identify
     * @return the UUID identity of the chat associated with the given chat ID
     */
//    @Override
//    public UUID retriveUuidChat(ChatMessageDTO dataChat) {
//        Chat chat = this.chatRepository.getChatById(dataChat.get());
//        return chat.getIdentity();
//    }
}
