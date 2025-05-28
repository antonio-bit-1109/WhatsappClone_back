package com.example.demo.websocketSetup;

import com.example.demo.security.GenerateToken;
import com.example.demo.service.AuthService;
import com.example.demo.utility.replacing.ReplaceValues;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Configuration
public class HandShakeInterceptor implements HandshakeInterceptor {

    private final AuthService authService;
    private final ReplaceValues replaceValues;

    public HandShakeInterceptor(AuthService authService,
                                ReplaceValues replaceValues) {
        this.authService = authService;
        this.replaceValues = replaceValues;
    }

    @Override
    public boolean beforeHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Map<String, Object> attributes
    ) throws Exception {

        String query = request.getURI().getQuery();

        if (query != null && query.startsWith("token=")) {

            try {
                String Jwt = query.substring(6);

                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(GenerateToken.getKey())
                        .build()
                        .parseClaimsJws(Jwt)
                        .getBody();


                String email = claims.get("email").toString();
                String role = (String) claims.get("role");
                String id = claims.get("id").toString();


                // se l utente è confermato esistere accetto la handshake
                // e salvo i dati presente nel token come dati di sessione della handshake
                // identifico ogni utente che salva i propri dati negli attributes aggiungendo al nome dell attributo nella mappa
                // la sua email come identificatore univoco
                // es: id-antonio@rizzuti.it --> 123
                // role-antonio@rizzuti.it --> USER
                // email-antonio@rizzuti.it --> antonio@rizzuti.it
                if (this.authService.userExistById(Long.parseLong(id))) {

                    attributes.put(
                            this.replaceValues.dinamicMapValueGrant(email, "id"),
                            id
                    );
                    attributes.put(
                            this.replaceValues.dinamicMapValueGrant(email, "role"),
                            role
                    );
                    attributes.put(
                            this.replaceValues.dinamicMapValueGrant(email, "email"),
                            email
                    );
                    return true;
                }


                return false;


            } catch (JwtException ex) {
                return false;
            }

        }


        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }

}
