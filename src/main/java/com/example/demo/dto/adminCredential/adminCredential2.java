package com.example.demo.dto.adminCredential;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "data2")
public record adminCredential2(Map<String, String> admincredential2) {
}
