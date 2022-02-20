package duke.parser;

import duke.command.*;
import duke.exception.AdditionalException;

public class Parser {

    public static Command parse(String fullCommand) throws AdditionalException {
        String[] words = fullCommand.split(" ");
        String command = words[0].toLowerCase();
        Command c;
        switch (command) {
        case "bye":
            c = new ByeCommand();
            break;
        case "list":
            c = new ListCommand();
            break;
        case "find":
            c = new FindCommand(fullCommand);
            break;
        case "mark":
            c = new MarkCommand(fullCommand);
            break;
        case "unmark":
            c = new UnmarkCommand(fullCommand);
            break;
        case "delete":
            c = new DeleteCommand(fullCommand);
            break;
        case "todo":
            c = new TodoCommand(fullCommand);
            break;
        case "deadline":
            c = new DeadlineCommand(fullCommand);
            break;
        case "event":
            c = new EventCommand(fullCommand);
            break;
        default:
            throw new AdditionalException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return c;
    }

    public static Command parseFromFile(String nextLine) throws AdditionalException {
        String[] words = nextLine.split("\\|");
        String command = words[0].toLowerCase();
        String fullCommand = getFullCommand(words);
        Command c;
        switch (command) {
        case "todo":
            c = new TodoCommand(fullCommand);
            break;
        case "deadline":
            c = new DeadlineCommand(fullCommand);
            break;
        case "event":
            c = new EventCommand(fullCommand);
            break;
        default:
            throw new AdditionalException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return c;
    }

    private static String getFullCommand(String[] words) {
        String fullCommand = words[0];
        for (int i = 2; i < words.length; i++) {
            fullCommand += " ";
            fullCommand += words[i];
        }
        return fullCommand;
    }

}
