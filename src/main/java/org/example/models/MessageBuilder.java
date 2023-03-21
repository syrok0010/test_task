package org.example.models;

public class MessageBuilder {
    private String command;
    private String username;
    private boolean server;

    public MessageBuilder setCommand(String command) {
        this.command = command;
        return this;
    }

    public MessageBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public MessageBuilder setServer(boolean server) {
        this.server = server;
        return this;
    }

    public Message createMessage() {
        return new Message(command, username, server);
    }
}