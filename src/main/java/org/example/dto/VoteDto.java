package org.example.dto;

import org.example.models.Message;

public class VoteDto extends Message {
    public String topicName;
    public String voteName;
    public Integer choice = null;

    public VoteDto(String command, String username) {
        super(command, username);
    }
}
