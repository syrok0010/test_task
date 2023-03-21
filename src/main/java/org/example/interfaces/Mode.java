package org.example.interfaces;

import org.example.Main;
import org.example.services.AssemblyScanner;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Mode {
    protected final ArrayList<IView> allCommandsInstances;

    public Mode(String packageName) {
        allCommandsInstances = new AssemblyScanner<IView>().getAllCommandsInPackage(packageName);
    }
    public void run() {
        help();
        System.out.println("Введите команду:");
        while (true) {
            var command = Main.in.nextLine();
            if (Objects.equals(command, "exit")) return;
            runCommand(command);
        }
    }

    private void help() {
        for(var command: allCommandsInstances) command.printHelp();
        System.out.println("- exit - завершение работы программы");
    }

    protected void runCommand(String command) {
        var commandName = command.split(" ")[0];
        var view = allCommandsInstances.stream()
                .filter(x -> {
                    var parts = x.getClass().getName().split("\\.");
                    return parts[parts.length - 1].equalsIgnoreCase(commandName);
                })
                .findAny().orElse(null);
        Objects.requireNonNull(view).execute(command);
    }


}
