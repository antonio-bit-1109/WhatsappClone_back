package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// filtro che ad ogni request arrivata al server prende il token
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final GenerateToken generateToken;

    public JwtAuthFilter(GenerateToken generateToken) {
        this.generateToken = generateToken;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException
    {
        String header = request.getHeader("Autorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7); // Rimuove "Bearer "
            // Logica per validare il token
            // Se valido, imposta i dettagli dell'utente nel contesto di sicurezza
        }

        filterChain.doFilter(request, response);
    }
}

