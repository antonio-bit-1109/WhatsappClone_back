package com.example.demo.service;

import com.example.demo.dto.requests.chatMessage.ChatMessageDTO;
import com.example.demo.dto.responses.MessaggioDto;
import com.example.demo.entity.App_User;
import com.example.demo.entity.Messaggio;
import com.example.demo.enums.BrokerDestinations;
import com.example.demo.enums.MessageStatus;
import com.example.demo.interfaces.IChatWebSocketService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChatWebSocketService implements IChatWebSocketService {

    private final SimpMessagingTemplate messagingTemplate;


    public ChatWebSocketService(
            SimpMessagingTemplate messagingTemplate

    ) {
        this.messagingTemplate = messagingTemplate;
    }


    @Override
    public void sendToPrivateChat(ChatMessageDTO message, App_User user, LocalDateTime sendAt) {

//        ChatMessageDTO mess = new ChatMessageDTO(
//                message.getUserOwnerId(),
//                message.getText(),
//                message.getChatIdentity(),
//                user.getUsername()
//        );

        MessaggioDto mess = new MessaggioDto();
        mess.setContent(message.getText());
        mess.setEmail(user.getEmail());
        mess.setSendAtTime(sendAt);
        mess.setMessageStatus(MessageStatus.SENT);
        mess.setUserSender(user.getUsername());

        this.messagingTemplate
                .convertAndSend(BrokerDestinations.PRIVATE.getDestination() +
                        "/" + message.getChatIdentity(), mess);

    }


}
