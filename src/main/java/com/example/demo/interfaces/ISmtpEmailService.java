package com.example.demo.interfaces;

import com.example.demo.dto.responses.SmtpMessageDTO;

public interface ISmtpEmailService {
    boolean sendEmail(SmtpMessageDTO data);
}
