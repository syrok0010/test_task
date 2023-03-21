package org.example.views.commands.client;

import org.example.Main;
import org.example.dto.VoteDto;
import org.example.interfaces.IView;
import org.example.views.Client;

public class Vote implements IView {
    @Override
    public void execute(String command) {
        var dto = new VoteDto("vote", Client.UserName);
        var parts = command.split(" ");
        for (var part : parts) {
            if (part.contains("-t=")) {
                dto.topicName = part.split("=")[1];
                continue;
            }
            if (part.contains("-v=")) dto.voteName = part.split("=")[1];
        }
        System.out.print(Client.ServerClient.sendMessage(dto));
        var choice = Integer.parseInt(Main.in.nextLine());
        var newDto = new VoteDto("vote", Client.UserName);
        newDto.choice = choice;
        newDto.topicName = dto.topicName;
        newDto.voteName = dto.voteName;
        System.out.println(Client.ServerClient.sendMessage(newDto));
    }

    @Override
    public void printHelp() {
        System.out.println("- vote -t=<topic> -v=<vote> - запускает выбор ответа в голосовании для текущего пользователя");
    }
}
