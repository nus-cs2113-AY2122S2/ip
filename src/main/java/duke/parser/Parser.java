package duke.parser;

import duke.command.*;
import duke.exception.AdditionalException;

/**
 * Represents a command parser that deals with making sense of the user command.
 */
public class Parser {

    /**
     * Makes sense of what type of command it is.
     * It then creates a Command object, depending on the type of command, and returns in to the caller.
     *
     * @param fullCommand The user input.
     * @return The Command object, depending on the type of the command.
     * @throws AdditionalException If the command is invalid.
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
     * Makes sense of what the command that is saved in the file is.
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
     * Combine an array of Strings to make out the actual command and then proceeds to return it.
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

    /**
     * Returns the description for todo commands.
     *
     * @param typeOfTask This is the type of task.
     * @param fullCommand This is the full command that the user input
     * @return The description of the tasks.
     */
    public static String getDescription(String typeOfTask, String fullCommand) {
        int lowerBound = typeOfTask.length();
        int upperBound = fullCommand.length();
        String description = fullCommand.substring(lowerBound, upperBound);
        return description.trim();
    }

    /**
     * Returns the description for deadline and event commands.
     *
     * @param typeOfTask This is the type of task.
     * @param preposition This is the preposition that is used in the commands.
     * @param fullCommand This is the full command that the user input.
     * @return The description of the tasks.
     */
    public static String getDescription(String typeOfTask, String preposition, String fullCommand) throws AdditionalException {
        int lowerBound = typeOfTask.length();
        int upperBound = fullCommand.indexOf(preposition);
        if (upperBound == -1) {
            throw new AdditionalException("You don't know basic grammar or what?");
        }
        String description = fullCommand.substring(lowerBound, upperBound);
        return description.trim();
    }

    /**
     * Returns the location for event commands.
     *
     * @param firstPreposition This is the first preposition used in the commands.
     * @param secondPreposition This is the second preposition used in the commands.
     * @param fullCommand This is the full command that the user input.
     * @return The location of the events.
     */
    public static String getLocation(String firstPreposition, String secondPreposition, String fullCommand)
            throws AdditionalException {
        int lowerBound = fullCommand.indexOf(firstPreposition) + firstPreposition.length();
        int upperBound = fullCommand.indexOf(secondPreposition);
        if (upperBound == -1) {
            throw new AdditionalException("The event has to be ON a certain date right?");
        }
        String location = fullCommand.substring(lowerBound, upperBound);
        return location.trim();
    }

    /**
     * Returns the date for deadline and event commands.
     *
     * @param preposition This is the preposition used in the commands.
     * @param fullCommand This is the full command that the user input.
     * @return The date of the tasks.
     */
    public static String getDate (String preposition, String fullCommand) {
        int lowerBound = fullCommand.indexOf(preposition) + preposition.length();
        int upperBound = fullCommand.length();
        String date = fullCommand.substring(lowerBound, upperBound);
        return date.trim();
    }

    /**
     * Returns the index for mark and unmark commands.
     *
     * @param fullCommand This is the full command that the user input.
     * @return The index to mark or unmark.
     * @throws AdditionalException If the user did not input exactly 1 index.
     * @see AdditionalException
     */
    public static int getIndex(String fullCommand) throws AdditionalException {
        String[] words = fullCommand.split(" ");
        if (words.length != 2) {
            throw new AdditionalException("Please input the index and only the index");
        }
        int index = Integer.parseInt(words[1]) - 1;
        return index;
    }

}
