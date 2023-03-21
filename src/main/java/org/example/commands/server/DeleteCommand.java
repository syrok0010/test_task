package org.example.commands.server;

import org.example.commands.Command;
import org.example.dto.DeleteDto;
import org.example.models.Message;
import org.example.services.TopicService;

public class DeleteCommand implements Command {
    @Override
    public String run(Message message) {
        System.out.println("On server");
        DeleteDto dto = (DeleteDto) message;
        try {
            TopicService.Singleton.deleteVote(dto.topicName, dto.name, dto.username);
        } catch (RuntimeException e) {
            return e.getMessage();
        }
        return "Успешно удалено";
    }
}
