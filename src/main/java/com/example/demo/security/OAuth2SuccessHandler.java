package com.example.demo.security;

import com.example.demo.entity.App_User;
import com.example.demo.service.OAuth2Service;
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


    public OAuth2SuccessHandler(GenerateToken generateToken, OAuth2Service oauth2Service) {
        this.generateToken = generateToken;
        this.oauth2Service = oauth2Service;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        logger.atInfo().log("OAuth2 Success Handler chiamato");
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        logger.atInfo().log(String.format("Dati utente ricevuti: %s", oauth2User.getAttributes()));

        App_User user = oauth2Service.processOAuth2User(oauth2User);
        String token = generateToken.generateToken(user);
        logger.atInfo().log(String.format("Token generato %s", token));


        String redirectUrl = "http://localhost:4200/login?token=" + token;
        logger.atInfo().log(String.format("Redirect a %s", redirectUrl));
        response.sendRedirect(redirectUrl);

    }

}
