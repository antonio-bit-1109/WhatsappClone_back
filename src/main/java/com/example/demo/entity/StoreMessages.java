package com.example.demo.entity;

import jakarta.persistence.*;


@Entity
public class StoreMessages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String emailSender;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    @Column(nullable = false)
    private boolean haveReplied = false;

    public StoreMessages(String emailSender, String text) {
        this.emailSender = emailSender;
        this.text = text;
    }

    public StoreMessages() {
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
