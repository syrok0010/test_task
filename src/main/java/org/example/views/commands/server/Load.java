package org.example.views.commands.server;

import org.example.commands.server.LoadCommand;
import org.example.dto.FileDto;
import org.example.interfaces.IView;

public class Load implements IView {
    @Override
    public void execute(String command) {
        var commandClass = new LoadCommand();
        var dto = new FileDto("save", "");
        dto.FileName = command.split(" ")[1];
        commandClass.run(dto);
    }

    @Override
    public void printHelp() {
        System.out.println("- load <filename> - загрузка данных из файла");
    }
}
