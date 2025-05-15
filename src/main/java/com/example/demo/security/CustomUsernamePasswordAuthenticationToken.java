package com.example.demo.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private String id;

    public CustomUsernamePasswordAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, String id) {
        super(principal, credentials, authorities);
        this.id = id;
    }

//    public CustomUsernamePasswordAuthenticationToken(Object principal, Object credentials) {
//        super(principal, credentials);
//    }

    public String getId() {
        return id;
    }
}
