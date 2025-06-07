package com.example.demo.interfaces;

import com.example.demo.dto.requests.messageMe.ReplayMessageDTO;
import com.example.demo.dto.requests.messageMe.SendMeMessageDTO;
import com.example.demo.dto.responses.StoredMessageDTO;
import com.example.demo.dto.responses.StringResponse;
import com.example.demo.entity.StoreMessages;

import java.util.List;

public interface ISendMessage {
    void registerMessage(SendMeMessageDTO data);

    List<StoredMessageDTO> getAllEmailFromPeople();

    StringResponse replayToMessage(ReplayMessageDTO message);

    StoreMessages getMessageFromId(Long id);

    void updateMsgStatus(StoreMessages entityMsg);
}
