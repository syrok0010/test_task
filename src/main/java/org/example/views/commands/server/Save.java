package org.example.views.commands.server;

import org.example.commands.server.SaveCommand;
import org.example.dto.FileDto;
import org.example.interfaces.IView;

public class Save implements IView {
    @Override
    public void execute(String command) {
        var commandClass = new SaveCommand();
        var dto = new FileDto("save", "");
        dto.FileName = command.split(" ")[1];
        commandClass.run(dto);
    }

    @Override
    public void printHelp() {
        System.out.println("- save <filename> – сохранение в файл всех созданных разделов и принадлежащим им голосований + их результатов");
    }
}
