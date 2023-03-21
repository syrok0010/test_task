package org.example.models;

import java.io.Serializable;

public class Message implements Serializable {
    public String username;
    public String command;
    public boolean server;
    public Message(String command, String username) {
        this(command, username, false);
    }
    public Message(String command, String username, boolean server) {
        if (!server && (username == null || username.isBlank())) throw new RuntimeException("Имя пользователя не может быть пустым");
        this.username = username;
        this.command = command;
    }
}
