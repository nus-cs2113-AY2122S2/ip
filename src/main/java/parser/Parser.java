package parser;

import commands.*;

import java.util.List;

public class Parser {
    public static Command parse(String fullCommand){
        fullCommand.trim();
        String[] splitCommands = fullCommand.split(" ", 2);
        String description;
        String commandWord = splitCommands[0].trim();
        Command cmd;

        switch (commandWord) {
        case TodoCommand.COMMAND_WORD:
            description = splitCommands[1].trim();
            cmd = prepareTodo(description);
            break;
        case DeadlineCommand.COMMAND_WORD:
            description = splitCommands[1].trim();
            cmd = prepareDeadline(description);
            break;
        case EventCommand.COMMAND_WORD:
            description = splitCommands[1].trim();
            cmd = prepareEvent(description);
            break;
        case ListCommand.COMMAND_WORD:
            cmd = new ListCommand();
            break;
        default:
            cmd = new TodoCommand("Undefined");

        }

        return cmd;
    }

    private static Command prepareTodo(String description) {
        return new TodoCommand(description);
    }

    private static Command prepareDeadline(String fullDescription) {
        String[] descriptions = fullDescription.split("/by", 2);
        String description = descriptions[0].trim();
        String by = descriptions[1].trim();
        return new DeadlineCommand(description, by);
    }

    private static Command prepareEvent(String fullDescription) {
        String[] descriptions = fullDescription.split("/at", 2);
        String description = descriptions[0].trim();
        String at = descriptions[1].trim();
        return new EventCommand(description, at);
    }
}
