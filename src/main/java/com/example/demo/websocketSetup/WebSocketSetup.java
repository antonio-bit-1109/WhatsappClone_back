package com.example.demo.websocketSetup;

import com.example.demo.enums.BrokerDestinations;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketSetup implements WebSocketMessageBrokerConfigurer {


    // configurazione websocket principale dell applicazione
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOrigins("http://localhost:4200")
                .withSockJS();

    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // QUANDO IL SERVER INVIA MESSAGGI AL CLIENT
        // abilita un broker che gestisce il canale /chat-private e /chat-room
        // gestisce i canali che vengono chiamati
        //- Utilizzati quando il server invia messaggi ai client
        registry.enableSimpleBroker(
                BrokerDestinations.PRIVATE.getDestination(),
                BrokerDestinations.ROOM.getDestination());

        // QUANDO I CLIENT INVIANO MESSAGGI AL SERVER
        //1. **Definisce un prefisso di routing** per i messaggi che devono essere gestiti dall'applicazione
        //2. **Crea un "punto di ingresso"** per i messaggi che i client inviano al server per l'elaborazione
        //3. **Collega i messaggi in arrivo** ai metodi del controller annotati con `@MessageMapping`
        registry.setApplicationDestinationPrefixes("/app");
    }
}
