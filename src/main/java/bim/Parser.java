package bim;

import bim.command.*;

import java.util.Scanner;

public class Parser {
    private static final int EXPECTED_ARG_NUMBER = 2;
    private static final int INDEX_CONVERTER = 1; // Used to convert 1-based indexing to 0-based indexing

    private static final String ERROR_COMMAND_ARG = "Check your arguments!";
    private static final String ERROR_COMMAND = "I couldn't understand that!";

    private static final String DELIMITER_EVENT = " /at ";
    private static final String DELIMITER_DEADLINE = " /by ";

    private static final String OP_MARK = "mark";
    private static final String OP_UNMARK = "unmark";
    private static final String OP_ADD_DEADLINE = "deadline";
    private static final String OP_ADD_TODO = "todo";
    private static final String OP_ADD_EVENT = "event";
    private static final String OP_EXIT_PROGRAM = "bye";
    private static final String OP_LIST_TASK = "list";
    private static final String OP_DELETE_TASK = "delete";

    private static final Scanner scanner = new Scanner(System.in);

    public Parser() {
    }

    /**
     * Returns the user input.
     * Inputs that are empty or blank are ignored.
     *
     * @return User input
     */
    public String readInput() {
        String input;

        do {
            input = scanner.nextLine();
        } while (input.isBlank());

        return input;
    }

    /**
     * Returns a command object based on the input.
     * Input is parsed to split the command word and the command arguments
     *
     * @param input The full command entered by the user.
     * @return Command object
     */
    public Command parseCommand(String input) {
        String[] words = input.split(" ", 2);
        String commandWord = words[0].toLowerCase();
        String commandArg = "";
        if (words.length > 1) {
            commandArg = words[1].toLowerCase();
        }

        Command command;

        switch (commandWord) {
        case OP_LIST_TASK:
            command = new ListCommand();
            break;
        case OP_MARK:
            // Fallthrough
        case OP_UNMARK:
            command = parseMarkAndUnmarkCommand(commandWord, commandArg);
            break;
        case OP_ADD_TODO:
            // Fallthrough
        case OP_ADD_DEADLINE:
            // Fallthrough
        case OP_ADD_EVENT:
            command = parseAddCommand(commandWord, commandArg);
            break;
        case OP_DELETE_TASK:
            command = parseDeleteCommand(commandArg);
            break;
        case OP_EXIT_PROGRAM:
            command = new ExitCommand();
            break;
        default:
            command = new IncorrectCommand(ERROR_COMMAND);
            break;
        }
        return command;
    }

    /**
     * Parses the argument supplied based on the type of command.
     * If the argument is valid, an AddCommand object is created
     * and returned. Else, an IncorrectCommand object is returned instead.
     *
     * @param type todo, event or deadline
     * @param commandArg The full argument given by the user.
     * @return AddCommand if parameters are valid, IncorrectCommand otherwise.
     */
    public Command parseAddCommand(String type, String commandArg) {
        if (type.equals(OP_ADD_TODO)) {
            if (!commandArg.isEmpty()) {
                return new IncorrectCommand(ERROR_COMMAND_ARG);
            }

            return new AddCommand(type, commandArg);
        }
        else {
            if (!isValidArgument(DELIMITER_DEADLINE, commandArg) && !isValidArgument(DELIMITER_EVENT, commandArg)) {
                return new IncorrectCommand(ERROR_COMMAND_ARG);
            }

            String[] parsedValues;

            if (type.equals(OP_ADD_DEADLINE)) {
                parsedValues = commandArg.split(DELIMITER_DEADLINE);
            }
            else {
                parsedValues = commandArg.split(DELIMITER_EVENT);
            }
            String description = parsedValues[0];
            String date = parsedValues[1];
            return new AddCommand(type, description, date);
        }
    }

    /**
     * Parses the argument supplied for mark and unmark commands.
     *
     * @param type mark or unmark
     * @param commandArg the index of the task to be marked or unmarked given by the user in string
     * @return MarkCommand
     */
    public Command parseMarkAndUnmarkCommand(String type, String commandArg) {
        int index = parseIndex(commandArg);
        return new MarkCommand(type, index);
    }

    /**
     * Parses the argument supplied for delete commands.
     *
     * @param commandArg the index of the task to be deleted given by the user in string
     * @return DeleteCommand
     */
    public Command parseDeleteCommand(String commandArg) {
        int index = parseIndex(commandArg);
        return new DeleteCommand(index);
    }

    /**
     * Converts the index in the argument from string to int.
     * If the argument cannot be parsed, returns -1 instead.
     * Else, the argument is converted to int and is shifted by -1 to be 0-based.
     * @param commandArg the string given by the user.
     * @return 0-based index.
     */
    public int parseIndex(String commandArg) {
        int index;
        try {
            index = Integer.parseInt(commandArg) - INDEX_CONVERTER;
        } catch (NumberFormatException exception) {
            index = -1;
        }
        return index;
    }

    /**
     * Returns the validity of the argument
     * Checks if the argument contains the appropriate delimiter and ensures that the
     * argument is not blank
     * @param delimiter /by if the command is a deadline, /at if the command is an event
     * @param commandArg the full argument given by the user.
     * @return true if argument is valid, false otherwise.
     */
    public boolean isValidArgument(String delimiter, String commandArg) {
        if (commandArg.contains(delimiter)) {
            String[] arguments = commandArg.split(delimiter);
            return arguments.length == EXPECTED_ARG_NUMBER;
        }
        return false;
    }
}
