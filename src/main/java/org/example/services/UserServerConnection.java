package org.example.services;

import org.example.commands.Command;
import org.example.models.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;

public class UserServerConnection extends Thread {
    private final ObjectOutputStream out;
    private final ObjectInputStream in;
    protected final ArrayList<Command> allCommandsInstances;

    public UserServerConnection(Socket socket) {
        allCommandsInstances = new AssemblyScanner<Command>().getAllCommandsInPackage("org.example.commands.server");
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        Message input;
        try {
            while ((input = (Message) in.readObject()) != null) {
                out.writeObject(HandleCommand(input));
            }
        }
        catch (IOException | ClassNotFoundException ignored) {}
    }

    private String HandleCommand(Message input) {
        var commandName = input.command;
        var command = allCommandsInstances.stream()
                .filter(x -> {
                    var parts = x.getClass().getName().split("\\.");
                    return parts[parts.length - 1].equalsIgnoreCase(commandName + "command");
                })
                .findAny().orElse(null);
        return Objects.requireNonNull(command).run(input);
    }
}
