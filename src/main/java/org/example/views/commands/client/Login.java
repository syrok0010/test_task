package org.example.views.commands.client;

import org.example.interfaces.IView;
import org.example.views.Client;

public class Login implements IView {
    @Override
    public void execute(String command) {
        var commandSplit = command.split(" ");
        var username = "";
        for (var i: commandSplit) {
            if (!i.contains("-u=")) continue;
            username = i.split("=")[1];
        }
        if (username.isBlank()) throw new RuntimeException("Username can't be null");
        Client.UserName = username;
    }

    @Override
    public void printHelp() {
        System.out.println("- login -u=username – подключиться к серверу с указанным именем пользователя (все остальные команды доступны только после выполнения login)");
    }
}
