package com.example.demo.utility.factory.entityfactory;

import com.example.demo.dto.requests.appUser.CreateAnagraficaDTO;
import com.example.demo.dto.requests.appUser.CreateUserDTO;
import com.example.demo.dto.requests.chatMessage.ChatMessageDTO;
import com.example.demo.dto.requests.chatMessage.CreateChatDTO;
import com.example.demo.dto.requests.messageMe.SendMeMessageDTO;
import com.example.demo.entity.*;

public interface EntityFactory {
    App_User createEntityUser(CreateUserDTO data, boolean isRegisteringAdmin);

    Anagrafica createEntityAnagrafica(CreateAnagraficaDTO data);

    StorageLogs createEntityStorageLog(
            String timeStamp,
            String logType,
            Integer ProcessId,
            String message,
            String threadName
    );

    StoreMessages createEntityStoreMessage(SendMeMessageDTO data);

    Chat createEntityChat(CreateChatDTO data);

    Messaggio createEntityMessaggio(ChatMessageDTO dto, App_User user, Chat chat);

    void addChatToUser(Long idUser, Chat chat);

    App_User addAnagraficaToUser(
            Anagrafica anagrafica,
            App_User user
    );

    void addMessaggioToUser(App_User user, Messaggio messaggio);

    void addMessaggioToChat(Chat chat, Messaggio messaggio);
}
