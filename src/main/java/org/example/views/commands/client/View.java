package org.example.views.commands.client;

import org.example.dto.ViewDto;
import org.example.interfaces.IView;
import org.example.views.Client;

public class View implements IView {
    @Override
    public void execute(String command) {
        var dto = new ViewDto("view", Client.UserName);
        var parts = command.split(" ");
        for (var part : parts) {
            if (part.contains("-t=")) {
                dto.topicName = part.split("=")[1];
                continue;
            }
            if (part.contains("-v=")) dto.voteName = part.split("=")[1];
        }
        System.out.println(Client.ServerClient.sendMessage(dto));
    }

    @Override
    public void printHelp() {
        System.out.println("- view -t=<topic> - показывает список уже созданных разделов в формате: <topic (votes in topic=<count>)>");
        System.out.println("- view -t=<topic> -v=<vote> - отображает информацию по голосованию\n" +
                "\t▪ тема голосования\n" +
                "\t▪ варианты ответа и количество пользователей выбравших данный вариант");
    }
}
