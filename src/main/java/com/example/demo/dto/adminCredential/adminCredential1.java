package com.example.demo.dto.adminCredential;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "data1")
public record adminCredential1(Map<String, String> admincredential1) {
}
