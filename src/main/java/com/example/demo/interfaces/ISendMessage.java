package com.example.demo.interfaces;

import com.example.demo.dto.requests.messageMe.SendMeMessageDTO;
import com.example.demo.dto.responses.StoredMessageDTO;

import java.util.List;

public interface ISendMessage {
    void registerMessage(SendMeMessageDTO data);

    List<StoredMessageDTO> getAllEmailFromPeople();
}
