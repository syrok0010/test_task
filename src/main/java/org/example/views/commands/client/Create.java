package org.example.views.commands.client;

import org.example.Main;
import org.example.dto.CreateDto;
import org.example.interfaces.IView;
import org.example.views.Client;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Create implements IView {
    private CreateDto dto;
    private String[] commandParts;
    @Override
    public void execute(String command) {
        commandParts = command.split(" ");
        var method = commandParts[1];
        dto = new CreateDto(commandParts[0], Client.UserName);
        dto.subject = method;
        try {
            this.getClass().getMethod(method).invoke(this);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Client.ServerClient.sendMessage(dto));
    }

    public void topic() {
        for (var part: commandParts) {
            if (!part.contains("-n=")) continue;
            dto.topicName = part.split("=")[1];
        }
    }
    public void vote() {
        for (var part: commandParts) {
            if (!part.contains("-t=")) continue;
            dto.topicName = part.split("=")[1];
        }
        System.out.println("Введите название голосования:");
        dto.name = Main.in.nextLine();
        System.out.println("Введите описание голосования:");
        dto.description = Main.in.nextLine();
        System.out.println("Введите количество вариантов ответа:");
        var n = Integer.parseInt(Main.in.nextLine());
        dto.answerOptions = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
            dto.answerOptions.add(Main.in.nextLine());
    }

    @Override
    public void printHelp() {
        System.out.println("- create topic -n=<topic> - создает новый раздел c уникальным именем заданным в параметре -n");
        System.out.println("- create vote -t=<topic> - запускает создание нового голосования в разделе указанном в параметре -t");
    }
}
