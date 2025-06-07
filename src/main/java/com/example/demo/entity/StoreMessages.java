package com.example.demo.entity;

import com.example.demo.interfaces.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
public class StoreMessages implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String emailSender;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    @Column(nullable = false)
    private boolean haveReplied = false;

    @Column(nullable = false)
    private LocalDateTime receivedAt;

    @Column(nullable = true)
    private LocalDateTime answeredAt;

    public StoreMessages(String emailSender, String text, LocalDateTime receivedAt) {
        this.emailSender = emailSender;
        this.text = text;
        this.receivedAt = receivedAt;
    }

    public StoreMessages() {
    }

    public void setAnsweredAt(LocalDateTime answeredAt) {
        this.answeredAt = answeredAt;
    }

    public void setReceivedAt(LocalDateTime receivedAt) {
        this.receivedAt = receivedAt;
    }

    public LocalDateTime getAnsweredAt() {
        return answeredAt;
    }

    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }

    public boolean getHaveReplied() {
        return this.haveReplied;
    }

    public void setHaveReplied(boolean haveReplied) {
        this.haveReplied = haveReplied;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailSender() {
        return emailSender;
    }

    public void setEmailSender(String emailSender) {
        this.emailSender = emailSender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
