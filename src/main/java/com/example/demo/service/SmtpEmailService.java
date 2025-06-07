package com.example.demo.service;

import com.example.demo.dto.responses.SmtpMessageDTO;
import com.example.demo.interfaces.ISmtpEmailService;
import com.example.demo.utility.exception.ErrorSendEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SmtpEmailService implements ISmtpEmailService {

    private final JavaMailSender javaMailSender;
    private static final Logger logger = LoggerFactory.getLogger(SmtpEmailService.class);


    public SmtpEmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;

    }

    @Override
    public boolean sendEmail(SmtpMessageDTO data) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(data.getTo());
            message.setSubject(data.getSubject());
            message.setText(data.getBody());

            this.javaMailSender.send(message);
            logger.atInfo().log("email inviata con successo a: " + data.getTo());
            return true;
        } catch (MailException ex) {
            System.out.println("impossibile inviare la mail.");
            logger.atError().log("errore durante la risposta al messaggio della mail:" + data.getTo());
            throw new ErrorSendEmail("errore durante l'invio della email: " + ex.getMessage());
        }
    }
}
