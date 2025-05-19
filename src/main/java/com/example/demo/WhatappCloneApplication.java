package com.example.demo;


import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.access.annotation.Secured;


@SpringBootApplication
@EnableScheduling
@OpenAPIDefinition(
        info = @Info(
                title = "clone whatsapp",
                description = "clone of the popular whatsapp service, using websocket for message services and google auth for login",
                version = "1.0.0",
                contact = @Contact(
                        name = "Antonio Rizzuti",
                        email = "antonio.rizzuti@hotmail.com",
                        url = "https://github.com/antonio-bit-1109"
                ),
                license = @License(
                        name = "",
                        url = ""
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "",
                url = ""
        )

)
public class WhatappCloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhatappCloneApplication.class, args);
    }

}
