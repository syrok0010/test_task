package org.example.dto;

import org.example.models.Message;

public class FileDto extends Message {
    public String FileName;
    public FileDto(String command, String username) {
        super(command, username, true);
    }
}
