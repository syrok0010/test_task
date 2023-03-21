package org.example.views;

import org.example.interfaces.Mode;
import org.example.services.MultiUserServer;

public class Server extends Mode {
    public Server() {
        super("org.example.views.commands.server");
    }

    public void run() {
        System.out.println("Сервер стартует...");
        var server = new MultiUserServer(9000);
        server.start();
        System.out.println("Вы вошли в серверный режим. Следующие команды доступны:");
        super.run();
        server.interrupt();
    }
}
