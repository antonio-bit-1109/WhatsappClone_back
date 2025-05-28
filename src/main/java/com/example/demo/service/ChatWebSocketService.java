package com.example.demo.service;

import com.example.demo.dto.requests.chatMessage.ChatMessageDTO;
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
    public void sendToPrivateChat(ChatMessageDTO message) {
        this.messagingTemplate
                .convertAndSend(BrokerDestinations.PRIVATE.getDestination() +
                        "/" + message.getChatIdentity(), message.getText());

    }


}
