package com.example.demo.interfaces;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface IJWTAuthFilter {

    void Send_unauthorized(HttpServletResponse response, JwtException e) throws IOException;

    void checkIfTokenJWTPresent(
            HttpServletResponse response,
            String authHeader
    ) throws IOException;
}
