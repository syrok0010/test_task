package org.example.services;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Objects;

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
        while (!Thread.currentThread().isInterrupted()) {
            try {
                new UserServerConnection(serverSocket.accept()).start();
            } catch (IOException e) {
                if (Objects.equals(e.getMessage(), "Socket closed")) return;
                throw new RuntimeException(e);
            }
        }
    }

    public void closeConnection() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
