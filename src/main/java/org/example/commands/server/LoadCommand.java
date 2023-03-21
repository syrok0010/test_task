package org.example.commands.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.commands.Command;
import org.example.dto.FileDto;
import org.example.models.Message;
import org.example.models.Topic;
import org.example.services.TopicService;

import java.io.File;
import java.io.IOException;

public class LoadCommand implements Command  {
    @Override
    public String run(Message message) {
        var dto = (FileDto) message;
        var mapper = new ObjectMapper();
        try {
            var read = mapper.readValue(new File(dto.FileName), Topic[].class);
            TopicService.Singleton.loadTopics(read);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
