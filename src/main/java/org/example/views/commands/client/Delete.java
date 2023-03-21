package org.example.views.commands.client;

import org.example.dto.DeleteDto;
import org.example.interfaces.IView;
import org.example.views.Client;

public class Delete implements IView {
    @Override
    public void execute(String command) {
        var dto = new DeleteDto("delete", Client.UserName);
        var parts = command.split(" ");
        for (var part : parts) {
            if (part.contains("-t=")) {
                dto.topicName = part.split("=")[1];
                continue;
            }
            if (part.contains("-v=")) dto.name = part.split("=")[1];
        }
        System.out.println(Client.ServerClient.sendMessage(dto));
    }

    @Override
    public void printHelp() {
        System.out.println("- delete -t=topic -v=<vote> - удалить голосование с именем <vote> из <topic> (удалить может только пользователь его создавший)");
    }
}
