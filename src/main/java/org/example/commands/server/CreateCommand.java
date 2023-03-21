package org.example.commands.server;

import org.example.commands.Command;
import org.example.dto.CreateDto;
import org.example.models.Message;
import org.example.models.Topic;
import org.example.services.TopicService;

public class CreateCommand implements Command {
    private CreateDto dto;
    @Override
    public String run(Message message) {
        dto = (CreateDto) message;
        return switch (dto.subject) {
            case "topic" -> topic();
            case "vote" -> vote();
            default -> "";
        };
    }

    private String vote() {
        try {
            TopicService.Singleton.addVote(dto.topicName, dto.name, dto.description, dto.answerOptions, dto.username);
        } catch (RuntimeException e) {
            return e.getMessage();
        }
        return "Голосование создано";
    }

    private String topic() {
        try {
            if (dto.topicName == null || dto.topicName.isBlank()) throw new RuntimeException("Имя топика не может быть пустым");
            var topic = new Topic(dto.topicName, dto.username);
            TopicService.Singleton.add(topic);
        } catch (RuntimeException e) {
            return e.getMessage();
        }
        return "Топик создан";
    }
}
