package org.example.commands.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.commands.Command;
import org.example.dto.FileDto;
import org.example.models.Message;
import org.example.services.TopicService;

import java.io.*;

public class SaveCommand implements Command {
    @Override
    public String run(Message message) {
        var dto = (FileDto) message;
        try {
            PrintWriter out = new PrintWriter(dto.FileName);
            ObjectMapper mapper = new ObjectMapper();
            var strWriter = new StringWriter();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(strWriter, TopicService.Singleton.getAllTopics());
            out.println(strWriter);
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Saved";
    }
}
