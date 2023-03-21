package org.example.dto;

import org.example.models.Message;

public class DeleteDto extends Message {
    public String name;
    public String topicName;
    public DeleteDto(String command, String username) {
        super(command, username);
    }
}
