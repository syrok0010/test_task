package org.example.dto;

import org.example.models.Message;

import java.util.ArrayList;

public class CreateDto extends Message {

    public String name;
    public String topicName;
    public String subject;
    public String description;
    public ArrayList<String> answerOptions;
    public CreateDto(String command, String username) {
        super(command, username);
    }
}
