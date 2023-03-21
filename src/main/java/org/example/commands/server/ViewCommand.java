package org.example.commands.server;

import org.example.commands.Command;
import org.example.dto.ViewDto;
import org.example.models.Message;
import org.example.services.TopicService;

public class ViewCommand implements Command {
    @Override
    public String run(Message message) {
        try {
            var dto = (ViewDto) message;
            if (dto.voteName == null || dto.voteName.isBlank())
                return TopicService.Singleton.listAllVotesInTopic(dto.topicName);
            return TopicService.Singleton.getVoteView(dto.topicName, dto.voteName);
        }
        catch (RuntimeException e) {
            return e.getMessage();
        }
    }
}
