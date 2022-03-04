package bim;

import bim.command.Command;
import bim.command.ExitCommand;
import bim.command.DeadlineCommand;
import bim.command.EventCommand;
import bim.command.TodoCommand;
import bim.command.FindCommand;
import bim.command.MarkCommand;
import bim.command.IncorrectCommand;
import bim.command.DeleteCommand;
import bim.command.ListCommand;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Extract relevant information from user input. The information is then used to prepare the command
 * that the user wishes to execute.
 */
public class Parser {
    private static final int EXPECTED_NUMBER_OF_FIELDS = 2;
    private static final int INDEX_CONVERTER = 1; // Used to convert 1-based indexing to 0-based indexing

    private static final String ERROR_EMPTY_KEYWORD = "Keyword cannot be empty!";
    private static final String ERROR_EMPTY_DESCRIPTION = "Task description cannot be empty!";
    private static final String ERROR_COMMAND_DATE = "Invalid date format, expecting yyyy-mm-dd";
    private static final String ERROR_COMMAND = "I could not understand that!";
    private static final String ERROR_COMMAND_DELIMITER_1 = "Missing or invalid delimiter.";
    private static final String ERROR_COMMAND_DELIMITER_2 = "Expecting /by for deadline, /at for event!";
    private static final String ERROR_MISSING_ARGUMENT = "Please provide both description and date!";

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

    public Parser() {
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
        String commandArg = (words.length > 1) ? words[1] : "";

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
            command = parseTodoCommand(commandArg);
            break;
        case OP_ADD_DEADLINE:
            command = parseDeadlineCommand(commandArg);
            break;
        case OP_ADD_EVENT:
            command = parseEventCommand(commandArg);
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
        return new FindCommand(commandArg.toLowerCase());
    }

    /**
     * Returns a TodoCommand ready for execution
     * If the argument is empty, then an IncorrectCommand object is returned instead.
     *
     * @param commandArg The full argument given by the user.
     * @return TodoCommand if parameters are valid, IncorrectCommand otherwise.
     */
    public Command parseTodoCommand(String commandArg) {
        if (commandArg.isEmpty()) {
            return new IncorrectCommand(ERROR_EMPTY_DESCRIPTION);
        }
        return new TodoCommand(commandArg);
    }

    /**
     * Returns a DeadlineCommand ready for execution
     * If the argument is empty or invalid, then an IncorrectCommand object is returned instead.
     *
     * @param commandArg The full argument given by the user.
     * @return DeadlineCommand if parameters are valid, IncorrectCommand otherwise.
     */
    public Command parseDeadlineCommand(String commandArg) {
        if (!commandArg.contains(DELIMITER_DEADLINE.strip())) {
            return new IncorrectCommand(ERROR_COMMAND_DELIMITER_1 + "\n" + ERROR_COMMAND_DELIMITER_2);
        }

        String[] splitValues;
        splitValues = commandArg.split(DELIMITER_DEADLINE);

        try {
            String description = splitValues[0];
            LocalDate date = LocalDate.parse(splitValues[1]);
            return new DeadlineCommand(description, date);
        } catch (IndexOutOfBoundsException missingDate) {
            return new IncorrectCommand(ERROR_MISSING_ARGUMENT);
        }  catch (DateTimeParseException invalidDate) {
            return new IncorrectCommand(ERROR_COMMAND_DATE);
        }
    }

    /**
     * Returns a EventCommand ready for execution
     * If the argument is empty or invalid, then an IncorrectCommand object is returned instead.
     *
     * @param commandArg The full argument given by the user.
     * @return EventCommand if parameters are valid, IncorrectCommand otherwise.
     */
    public Command parseEventCommand(String commandArg) {
        if (!commandArg.contains(DELIMITER_EVENT.strip())) {
            return new IncorrectCommand(ERROR_COMMAND_DELIMITER_1 + "\n" + ERROR_COMMAND_DELIMITER_2);
        }

        String[] splitValues;
        splitValues = commandArg.split(DELIMITER_EVENT);

        try {
            String description = splitValues[0];
            LocalDate date = LocalDate.parse(splitValues[1]);
            return new EventCommand(description, date);
        } catch (IndexOutOfBoundsException missingDate) {
            return new IncorrectCommand(ERROR_MISSING_ARGUMENT);
        }  catch (DateTimeParseException invalidDate) {
            return new IncorrectCommand(ERROR_COMMAND_DATE);
        }
    }


    /**
     * Parses the argument supplied for mark and unmark commands.
     *
     * @param type       mark or unmark
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
     *
     * @param commandArg the string given by the user.
     * @return 0-based index in integer.
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
     *
     * @param delimiter  /by if the task is a deadline, /at if the task is an event
     * @param commandArg the full argument given by the user.
     * @return true if argument is valid, false otherwise.
     */
    public boolean hasValidDelimiter(String delimiter, String commandArg) {
        if (commandArg.contains(delimiter)) {
            String[] arguments = commandArg.split(delimiter);
            return arguments.length == EXPECTED_NUMBER_OF_FIELDS;
        }
        return false;
    }
}
