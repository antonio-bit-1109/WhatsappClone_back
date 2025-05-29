package com.example.demo.service;

import com.example.demo.dto.requests.chatMessage.ChatMessageDTO;
import com.example.demo.entity.App_User;
import com.example.demo.enums.BrokerDestinations;
import com.example.demo.interfaces.IChatWebSocketService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatWebSocketService implements IChatWebSocketService {

    private final SimpMessagingTemplate messagingTemplate;


    public ChatWebSocketService(
            SimpMessagingTemplate messagingTemplate

    ) {
        this.messagingTemplate = messagingTemplate;
    }


    @Override
    public void sendToPrivateChat(ChatMessageDTO message, App_User user) {

        ChatMessageDTO mess = new ChatMessageDTO(
                message.getUserOwnerId(),
                message.getText(),
                message.getChatIdentity(),
                user.getUsername()
        );
        this.messagingTemplate
                .convertAndSend(BrokerDestinations.PRIVATE.getDestination() +
                        "/" + message.getChatIdentity(), mess);

    }


}
