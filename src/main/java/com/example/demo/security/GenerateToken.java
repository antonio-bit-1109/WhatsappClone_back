package com.example.demo.security;


import com.example.demo.entity.Anagrafica;
import com.example.demo.entity.App_User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


// classe che tiene tutto il necessario per la generazione del token
@Component
public class GenerateToken {

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_TIME = 86400000; // 1 giorno in millisecondi

    public static Key getKey() {
        return key;
    }

    public String generateToken(App_User user, Anagrafica anagrafica) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole()); // Aggiungi eventuali ruoli o informazioni extra
        claims.put("id", user.getId());
        claims.put("image", user.getProfileImage());
        claims.put("full_name", String.format(anagrafica.getNome() + " " + anagrafica.getCognome()));
        claims.put("email", user.getEmail());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

}
