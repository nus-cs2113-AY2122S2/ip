package bim;

import bim.command.*;

import java.util.List;
import java.util.Scanner;

public class Parser {
    private static final int EXPECTED_ARG_NUMBER = 2;

    private static final String ERROR_COMMAND_ARG = "Check your arguments!";
    private static final String ERROR_COMMAND = "I couldn't understand that!";
    private static final String ERROR_EMPTY_KEYWORD = "Keyword cannot be empty!";

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
    private static final String OP_FIND_TASK = "find";

    private static final Scanner scanner = new Scanner(System.in);

    public Parser() { }

    public boolean isValidArgument(String commandArg) {
        return !commandArg.isEmpty();
    }

    public String readInput() {
        String input;

        do {
            input = scanner.nextLine();
        } while (input.isBlank());

        return input;
    }

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
        case OP_FIND_TASK:
            command = parseFindCommand(commandArg);
            break;
        default:
            command = new IncorrectCommand(ERROR_COMMAND);
            break;
        }
        return command;
    }

    public Command parseFindCommand(String commandArg) {
        if (commandArg.isEmpty()) {
            return new IncorrectCommand(ERROR_EMPTY_KEYWORD);
        }
        return new FindCommand(commandArg);
    }


    public Command parseAddCommand(String type, String commandArg) {
        if (type.equals(OP_ADD_TODO)) {
            if (!isValidArgument(commandArg)) {
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

    public Command parseMarkAndUnmarkCommand(String type, String commandArg) {
        int index = parseIndex(commandArg);
        return new MarkCommand(type, index);
    }

    public Command parseDeleteCommand(String commandArg) {
        int index = parseIndex(commandArg);
        return new DeleteCommand(index);
    }


    public int parseIndex(String commandArg) {
        int index;
        try {
            index = Integer.parseInt(commandArg) - 1;
        } catch (NumberFormatException exception) {
            index = -1;
        }
        return index;
    }

    public boolean isValidArgument(String delimiter, String commandArg) {
        if (commandArg.contains(delimiter)) {
            String[] arguments = commandArg.split(delimiter);
            return arguments.length == EXPECTED_ARG_NUMBER;
        }
        return false;
    }
}
