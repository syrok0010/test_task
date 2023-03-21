package org.example.commands.server;

import org.example.commands.Command;
import org.example.dto.VoteDto;
import org.example.models.Message;
import org.example.services.TopicService;

public class VoteCommand implements Command {
    @Override
    public String run(Message message) {
        var dto = (VoteDto) message;
        try {
            if (dto.choice == null) return TopicService.Singleton.listAllVoteOptions(dto.topicName, dto.voteName);
            return TopicService.Singleton.vote(dto.topicName, dto.voteName, dto.choice, dto.username);
        }
        catch (RuntimeException e) {
            return e.getMessage();
        }
    }
}
