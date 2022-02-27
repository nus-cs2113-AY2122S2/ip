package parser;

import commands.Command;
import commands.TodoCommand;

public class Parser {
    public static Command parse(String fullCommand){
        fullCommand.trim();
        String[] splitCommands = fullCommand.split(" ", 2);
        String commandWord = splitCommands[0];
        String description = splitCommands[1];
        Command cmd;

        switch (commandWord) {
        case TodoCommand.COMMAND_WORD:
            cmd = prepareTodo(description);
            break;
        default:
            cmd = new TodoCommand("Undefined");

        }

        return cmd;
    }

    private static Command prepareTodo(String description) {
        return new TodoCommand(description);
    }
}
