package com.example.demo.service;

import com.example.demo.dto.requests.messageMe.ReplayMessageDTO;
import com.example.demo.dto.requests.messageMe.SendMeMessageDTO;
import com.example.demo.dto.responses.SmtpMessageDTO;
import com.example.demo.dto.responses.StoredMessageDTO;
import com.example.demo.dto.responses.StringResponse;
import com.example.demo.entity.StoreMessages;
import com.example.demo.interfaces.ISendMessage;
import com.example.demo.repository.StoreMessageRepository;
import com.example.demo.utility.exception.BadWordFounded;
import com.example.demo.utility.exception.InvalidEmailFormat;
import com.example.demo.utility.exception.StoreMessageNotFound;
import com.example.demo.utility.extracting.ExtractBadWords;
import com.example.demo.utility.factory.entityfactory.Factory;
import com.example.demo.utility.mapper.ModelMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SendMessageService implements ISendMessage {

    private final StoreMessageRepository storeMessageRepository;
    private final Factory factory;
    private final ExtractBadWords extractBadWords;
    private final ModelMapper modelMapper;
    private final SmtpEmailService smtpEmailService;

    public SendMessageService(StoreMessageRepository storeMessageRepository,
                              Factory factory,
                              ExtractBadWords extractBadWords,
                              ModelMapper modelMapper,
                              SmtpEmailService smtpEmailService
    ) {
        this.storeMessageRepository = storeMessageRepository;
        this.factory = factory;
        this.extractBadWords = extractBadWords;
        this.modelMapper = modelMapper;
        this.smtpEmailService = smtpEmailService;
    }


    @Override
    public void registerMessage(SendMeMessageDTO data) {

        // se email non è un formato valido lancio un errore
        if (!this.extractBadWords.checkIfValidEmailFormat(data.getSender())) {
            throw new InvalidEmailFormat("formato email non valido. Riprova");
        }

        // se trovo una badWord lancia un eccezione
        // e resetto il set con le parole arrivate dal front end
        if (this.extractBadWords.checkIfBadWordsInMessage(data.getMessage())) {
            this.extractBadWords.resetSetParolacceFrontEnd();
            throw new BadWordFounded("parola non consentita trovata. Modifica il messaggio.");
        }

        // se non ce una parolaccia salvo il messaggio insieme al sender
        // e anche qui resetto il set di parole arrivate dal front
        this.storeMessageRepository.save(this.factory.createEntityStoreMessage(data));
        this.extractBadWords.resetSetParolacceFrontEnd();

    }

    @Override
    public List<StoredMessageDTO> getAllEmailFromPeople() {
        return this.storeMessageRepository.findAll()
                .stream()
                .map(this.modelMapper::fromEntityToDto)
                .toList();
    }

    @Override
    public StringResponse replayToMessage(ReplayMessageDTO message) {

        StoreMessages msg = this.getMessageFromId(message.getIdStoredMessage());
        this.smtpEmailService.sendEmail(
                new SmtpMessageDTO(msg.getEmailSender(), message.getObject(), message.getReplayMessage())
        );

        this.updateMsgStatus(msg);
        return new StringResponse("risposta inviata con successo");

    }

    @Override
    public void updateMsgStatus(StoreMessages entityMsg) {
        entityMsg.setAnsweredAt(LocalDateTime.now());
        entityMsg.setHaveReplied(true);
        this.storeMessageRepository.save(entityMsg);
    }

    @Override
    public StoreMessages getMessageFromId(Long id) {
        Optional<StoreMessages> storeMsg_opt = this.storeMessageRepository.findById(id);

        if (storeMsg_opt.isEmpty()) {
            throw new StoreMessageNotFound("impossibile reperire il messaggio originale. Contatta l'assistenza.");
        }

        return storeMsg_opt.get();
    }


}
