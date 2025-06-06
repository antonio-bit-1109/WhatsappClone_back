package com.example.demo.entity;

import com.example.demo.enums.MessageStatus;
import com.example.demo.interfaces.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Messaggio implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime sendAtTime;

    @ManyToOne(cascade = CascadeType.ALL)
    private App_User owner;

    @ManyToOne
    private Chat chat;

    @Column(nullable = false)
    private MessageStatus messageStatus;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getSendAtTime() {
        return sendAtTime;
    }

    public void setSendAtTime(LocalDateTime sendAtTime) {
        this.sendAtTime = sendAtTime;
    }

    public App_User getOwner() {
        return owner;
    }

    public void setOwner(App_User owner) {
        this.owner = owner;
    }

    public MessageStatus getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(MessageStatus messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
