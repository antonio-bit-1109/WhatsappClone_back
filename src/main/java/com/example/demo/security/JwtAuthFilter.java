package com.example.demo.security;

import com.example.demo.dto.responses.StringResponse;
import com.example.demo.interfaces.IJWTAuthFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter implements IJWTAuthFilter {

    private final GenerateToken generateToken;

    public JwtAuthFilter(GenerateToken generateToken) {
        this.generateToken = generateToken;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith("/auth/login") ||
                path.startsWith("/auth/register") ||
                path.startsWith("/oauth2") ||      // Abilita endpoint OAuth2
                path.startsWith("/.well-known") || // Necessario per OpenID Configuration
                path.startsWith("/ws") ||
                path.startsWith("/ws/")
                ;
    }

    @Override
    protected void doFilterInternal
            (
                    HttpServletRequest request,
                    HttpServletResponse response,
                    FilterChain filterChain
            )
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        this.checkIfTokenJWTPresent(response, authHeader);

        String jwt = authHeader.substring(7); // rimuove "Bearer "

        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(GenerateToken.getKey())
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();

            String username = claims.getSubject();
            String role = (String) claims.get("role");
            String id = claims.get("id").toString();

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                List<SimpleGrantedAuthority> authorities =
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));

                UsernamePasswordAuthenticationToken authToken =
                        new CustomUsernamePasswordAuthenticationToken(username, null, authorities, id);

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        } catch (JwtException e) {
            // Token non valido
            this.Send_unauthorized(response, e);
            return;
        }

        filterChain.doFilter(request, response);
    }


    //    Questo metodo verifica la presenza e la validità formale
    //    del token JWT nell'header di autorizzazione:
    @Override
    public void checkIfTokenJWTPresent(
            HttpServletResponse response,
            String authHeader
    )
            throws IOException {


        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            StringResponse s = new StringResponse("Token JWT assente o richiesta malformata.");
            new ObjectMapper().writeValue(response.getWriter(), s);
            return;
        }

    }

    @Override
    public void Send_unauthorized(HttpServletResponse response, JwtException e)
            throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        StringResponse errorResponse = new StringResponse("Token JWT non valido: " + e.getMessage());
        new ObjectMapper().writeValue(response.getWriter(), errorResponse);
    }
}
