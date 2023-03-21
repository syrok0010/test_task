package org.example.dto;

import org.example.models.Message;

public class ViewDto extends Message {
    public String topicName;
    public String voteName;
    public ViewDto(String command, String username) {
        super(command, username);
    }
}
