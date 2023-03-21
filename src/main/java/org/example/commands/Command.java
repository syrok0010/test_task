package org.example.commands;

import org.example.models.Message;

public interface Command {
    String run(Message message);
}
