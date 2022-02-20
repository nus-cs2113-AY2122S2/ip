package duke.parser;

import duke.command.*;
import duke.exception.AdditionalException;

/**
 * Represents a command parser that deals with making sense of the user command.
 */
public class Parser {

    /**
     * This is the parse method that takes in the fullCommand and makes sense of what type of command it is.
     * It then creates a Command object, depending on the type of command, and returns in to the caller.
     *
     * @param fullCommand The user input
     * @return The Command object, depending on the type of the command
     * @throws AdditionalException If the command is invalid
     * @see AdditionalException
     */
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
        case "date":
            c = new DateCommand(fullCommand);
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
            c = new ToDoCommand(fullCommand);
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

    /**
     * This is the parseFromFile method that takes in the nextLine from the file and makes sense of what the command
     * that is saved in the file means.
     * It then creates a Command object, depending on the type of command and returns it to the caller.
     *
     * @param nextLine The next line from the file.
     * @return The Command object, depending on the type of the command.
     * @throws AdditionalException If the command saved in the file is invalid.
     * @see AdditionalException
     */
    public static Command parseFromFile(String nextLine) throws AdditionalException {
        String[] words = nextLine.split("\\|");
        String command = words[0].toLowerCase();
        String fullCommand = getFullCommand(words);
        Command c;
        switch (command) {
        case "todo":
            c = new ToDoCommand(fullCommand);
            break;
        case "deadline":
            c = new DeadlineCommand(fullCommand);
            break;
        case "event":
            c = new EventCommand(fullCommand);
            break;
        default:
            throw new AdditionalException("OOPS!!! I'm afraid the command in the file is invalid");
        }
        return c;
    }

    /**
     * This is the getFullCommand method that takes in an array of words and combine them to make out the actual
     * command and then proceeds to return it.
     *
     * @param words The array of words from the file.
     * @return The actual command for parsing.
     */
    private static String getFullCommand(String[] words) {
        String fullCommand = words[0];
        for (int i = 2; i < words.length; i++) {
            fullCommand += " ";
            fullCommand += words[i];
        }
        return fullCommand;
    }

}
