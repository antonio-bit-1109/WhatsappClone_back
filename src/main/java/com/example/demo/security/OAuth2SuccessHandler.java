package com.example.demo.security;

import com.example.demo.entity.App_User;
import com.example.demo.service.OAuth2Service;
import com.example.demo.utility.replacing.ReplaceValues;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final GenerateToken generateToken;
    private final OAuth2Service oauth2Service;
    private static final Logger logger = LoggerFactory.getLogger(OAuth2SuccessHandler.class);
    private final ReplaceValues replaceValues;

    public OAuth2SuccessHandler(GenerateToken generateToken,
                                OAuth2Service oauth2Service,
                                ReplaceValues replaceValues) {
        this.generateToken = generateToken;
        this.oauth2Service = oauth2Service;
        this.replaceValues = replaceValues;
    }


    // classe che viene chiamata se l'autenticazione tornata da google va a buon fine
    // viene estratto l oauth2User inviato da google con i dati dell'utente
    // questo viene salvato nel db
    // e generato un token da tornare al front end per l'autenticazione degli endpoint
    // viene salvato anche il provideId di google per identificare l utente sui server google
    // dopodichè faccio il redirect all applicazione a seconda del esito
    // (/success?=token) --> in home con il token da salvare
    // (/login?err) --> pagina di errore con msg di errore fornito

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        logger.atInfo().log("OAuth2 Success Handler chiamato");
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        logger.atInfo().log(String.format("Dati utente ricevuti: %s", oauth2User.getAttributes()));


        try {
            App_User user = oauth2Service.processOAuth2User(oauth2User);
            String token = generateToken.generateToken(user);
            logger.atInfo().log(String.format("Token generato %s", token));

            String redirectUrl = "http://localhost:4200/success?token=" + token;
            logger.atInfo().log(String.format("Redirect a %s", redirectUrl));
            response.sendRedirect(redirectUrl);

        } catch (RuntimeException ex) {

            // se prendo qualche errore di tipo runtime che si verifica durante la richiesta a google di autenticazione dell utente
            // prendo l errore e lo giro su un componente front end per gestire a schermo gli errori
            String errRedirectFront = "http://localhost:4200/error?err=" + this.replaceValues.replaceValues(" ", ",", ex.getMessage());
            response.sendRedirect(errRedirectFront);
        }


    }

}
