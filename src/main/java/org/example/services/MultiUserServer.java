package org.example.services;

import java.io.IOException;
import java.net.ServerSocket;

public class MultiUserServer extends Thread {
    private final ServerSocket serverSocket;
    public MultiUserServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException("Порт недоступен или что-то еще");
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                new UserServerConnection(serverSocket.accept()).start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
