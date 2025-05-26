package com.example.demo.service;

import com.example.demo.dto.requests.chatMessage.ChatMessageDTO;
import com.example.demo.dto.requests.chatMessage.CreateChatDTO;
import com.example.demo.dto.responses.ChatGetDTO;
import com.example.demo.entity.App_User;
import com.example.demo.entity.Chat;
import com.example.demo.entity.Messaggio;
import com.example.demo.interfaces.BasicCrud;
import com.example.demo.interfaces.IChatRestService;
import com.example.demo.repository.ChatRepository;
import com.example.demo.repository.MessaggioRepository;
import com.example.demo.utility.exception.NoChatFound;
import com.example.demo.utility.factory.entityfactory.Factory;
import com.example.demo.utility.mapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChatRestService
        implements BasicCrud<
        CreateChatDTO,
        Object,
        ChatGetDTO,
        Object,
        Object,
        Long
        >, IChatRestService {

    private final ChatRepository chatRepository;
    private final Factory factory;
    private final AuthService authService;
    private final ModelMapper modelMapper;
    private final MessaggioRepository messaggioRepository;

    public ChatRestService(ChatRepository chatRepository,
                           Factory factory,
                           AuthService authService,
                           ModelMapper modelMapper,
                           MessaggioRepository messaggioRepository) {
        this.chatRepository = chatRepository;
        this.factory = factory;
        this.authService = authService;
        this.modelMapper = modelMapper;
        this.messaggioRepository = messaggioRepository;
    }


    /**
     * Creates a new chat entity using the provided data and saves it to the repository.
     *
     * @param dataRegistration the data required to create the chat, including participant information
     */
    @Override
    public void create(CreateChatDTO dataRegistration) {
        this.chatRepository.save(
                this.factory.createEntityChat(dataRegistration)
        );
    }

    @Override
    public Object edit(Object dataEdit) {
        return null;
    }

    @Override
    public Object delete(Object data) {
        return null;
    }

    @Override
    public ChatGetDTO get(Object dataGet) {
        return null;
    }

    // ritornare una lista di tutte le mie chat (userId che le sta richiedendo)
    // quindi una lista contenente tutte le chats nel quale sono presente
    // con le informazioni relative per ogni chat
    // chi sono gli utenti
    // tutti i messaggi presenti per ogni chat
    // tutti i dati relativi alla chat
    @Override
    public List<ChatGetDTO> getAll(Optional<Long> userId) {

        // prendo lo user che sta facendo la richiesta
        Long idUser = this.returnOptional(userId);
        App_User user = this.authService.getUserById(idUser);
        // dello user prendo tutte le chat nel quale è coinvolto
        List<Chat> chats = user.getListaChats();

        return chats.stream()
                .map(chat -> this.modelMapper.fromEntityToDto(chat, user))
                .toList();

    }


    private Long returnOptional(Optional<Long> opt) {
        if (opt.isEmpty()) {
            return null;
        }
        return opt.get();
    }

    @Override
    public Chat save(Chat chat) {
        return this.chatRepository.save(chat);
    }


    @Override
    public void addMessageToChat(ChatMessageDTO message) {

        App_User user = this.authService.getUserById(message.getUserOwnerId());
        Chat chat = this.getChatFromIdentity(UUID.fromString(message.getChatIdentity()));

        Messaggio mess = this.factory
                .createEntityMessaggio(
                        message,
                        user,
                        chat
                );
        this.messaggioRepository.save(mess);

        this.factory.addMessaggioToUser(user, mess);
        this.factory.addMessaggioToChat(chat, mess);
    }

    @Override
    public Chat getChatFromIdentity(UUID identity) {
        Optional<Chat> opt_chat = this.chatRepository.getChatByIdentity(identity);

        if (opt_chat.isEmpty()) {
            throw new NoChatFound("nessuna chat trovata per l'identity fornito.");
        }

        return opt_chat.get();
    }
}
