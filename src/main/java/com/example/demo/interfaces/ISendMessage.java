package com.example.demo.interfaces;

import com.example.demo.dto.requests.messageMe.SendMeMessageDTO;

public interface ISendMessage {
    void registerMessage(SendMeMessageDTO data);
}
