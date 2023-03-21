package org.example.views;

import org.example.interfaces.Mode;
import org.example.services.ServerClient;

public class Client extends Mode {
    public static String UserName = "";
    public static ServerClient ServerClient;

    public Client() {
        super("org.example.views.commands.client");
        ServerClient = new ServerClient();
        ServerClient.startConnection("127.0.0.1", 9000);
    }

    public void run() {
        System.out.println("Вы вошли в клиентский режим. Следующие команды доступны:");
        super.run();
        ServerClient.stopConnection();
    }

    protected void runCommand(String command) {
        if (UserName.isBlank() && !command.split(" ")[0].equalsIgnoreCase("login")) {
            System.out.println("You need to log in first");
            return;
        }
        super.runCommand(command);
    }
}
