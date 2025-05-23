package com.example.demo.enums;

public enum BrokerDestinations {

    PRIVATE("/chat-private"),
    ROOM("/chat-room");

    private final String destination;

    BrokerDestinations(String destination) {
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }
}
