package com.example.demo.security;

import com.example.demo.dto.responses.StringResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

// filtro che ad ogni request arrivata al server prende il token
@Component
public class JwtAuthFilter extends OncePerRequestFilter {


    // Generate token è un component (singleton) di conseguenza non va spiecificato
    // nelle prop della classe ma puo venire direttamente iniettato (dependency injection)
    // e usato nella classe
    public JwtAuthFilter(GenerateToken generateToken) {
    }


    // metodo nel quale specificare quali metodi non devono essere filtrati perche in permitt all
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // Specifica gli endpoint che non richiedono autenticazione (permitAll)
        String requestURI = request.getRequestURI();
        return requestURI.startsWith("/auth/login") ||
                requestURI.startsWith("/auth/register");
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)

            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            // Blocca le richieste senza token o con token malformato
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            StringResponse resp = new StringResponse("Token mancante o non valido");
            response.setContentType("application/json");
            ObjectMapper objectMapper = new ObjectMapper();
            response.getWriter().write(objectMapper.writeValueAsString(resp));
            return;
        }


        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7); // Rimuove "Bearer "
            // Logica per validare il token
            // Se valido, imposta i dettagli dell'utente nel contesto di sicurezza

            try {

                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(GenerateToken.getKey()) // usa la chiave privata segreta
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                String username = claims.getSubject();
                Long id = claims.get("id", Long.class);
                String role = claims.get("role", String.class);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // Puoi anche caricare l'utente dal DB se vuoi
                    List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            } catch (JwtException e) {
                // Token non valido (firma errata, scaduto, ecc.)
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

        }

        filterChain.doFilter(request, response);
    }
}

