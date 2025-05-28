package com.example.demo.websocketSetup;

import com.example.demo.security.GenerateToken;
import com.example.demo.service.AuthService;
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

    public HandShakeInterceptor(AuthService authService) {
        this.authService = authService;
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
                if (this.authService.userExistById(Long.parseLong(id))) {
                    attributes.put("id", id);
                    attributes.put("role", role);
                    attributes.put("email", email);
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
