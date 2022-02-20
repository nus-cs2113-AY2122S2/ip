package duke.parser;

import duke.command.*;
import duke.exception.InvalidCommandFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static duke.common.Strings.*;

/**
 * Parses user input.
 */
public class Parser {
    /**
     * Parses user input into the relevant command object.
     * @param input a String array of the form {command word, command arguments}.
     * @return command based on user input.
     */
    public static Command parseCommand(String[] input) {
        final String command = input[0];
        final String args = input[1];
        switch (command) {
        case COMMAND_BYE:
            return prepareBye(args);
        case COMMAND_LIST:
            return prepareList(args);
        case COMMAND_TODO:
            return prepareTodo(args);
        case COMMAND_DEADLINE:
            return prepareDeadline(args);
        case COMMAND_EVENT:
            return prepareEvent(args);
        case COMMAND_MARK:
            return prepareMark(args);
        case COMMAND_UNMARK:
            return prepareUnmark(args);
        case COMMAND_DELETE:
            return prepareDelete(args);
        case COMMAND_FIND:
            return prepareFind(args);
        default:
            return new InvalidCommand();
        }
    }

    /**
     * Performs input validation for the "bye" command.
     * @param args user-supplied command arguments.
     * @return the prepared command.
     */
    private static Command prepareBye(String args) {
        try {
            if (!args.equals("")) {
                throw new InvalidCommandFormatException();
            }
            return new ByeCommand();
        } catch (InvalidCommandFormatException e) {
            return new InvalidCommand(COMMAND_BYE, USAGE_BYE);
        }
    }

    /**
     * Performs input validation for the "list" command.
     * @param args user-supplied command arguments.
     * @return the prepared command.
     */
    private static Command prepareList(String args) {
        try {
            if (args.equals("")) {
                return new ListCommand();
            }
            try {
                LocalDate queryDate = LocalDate.parse(args, DateTimeFormatter.ofPattern(FORMAT_DATE));
                return new ListCommand(queryDate);
            } catch (DateTimeParseException e) {
                throw new InvalidCommandFormatException();
            }
        } catch (InvalidCommandFormatException e) {
            return new InvalidCommand(COMMAND_LIST, USAGE_LIST, USAGE_LIST_DATE);
        }
    }

    /**
     * Performs input validation for the "todo" command.
     * @param args user-supplied command arguments.
     * @return the prepared command.
     */
    private static Command prepareTodo(String args) {
        try {
            if (args.equals("")) {
                throw new InvalidCommandFormatException();
            }
            return new TodoCommand(args.trim());
        } catch (InvalidCommandFormatException e) {
            return new InvalidCommand(COMMAND_TODO, USAGE_TODO);
        }
    }

    /**
     * Performs input validation for the "deadline" command.
     * @param args user-supplied command arguments.
     * @return the prepared command.
     */
    private static Command prepareDeadline(String args) {
        try {
            String[] parsedArgs = args.trim().split(DEADLINE_SEPARATOR, 2);
            if (parsedArgs.length != 2) {
                throw new InvalidCommandFormatException();
            }
            final String taskDescription = parsedArgs[0].trim();
            final String taskDeadline = parsedArgs[1].trim();
            return new DeadlineCommand(taskDescription, taskDeadline);
        } catch (InvalidCommandFormatException e) {
            return new InvalidCommand(COMMAND_DEADLINE, USAGE_DEADLINE, true);
        }
    }

    /**
     * Performs input validation for the "event" command.
     * @param args user-supplied command arguments.
     * @return the prepared command.
     */
    private static Command prepareEvent(String args) {
        try {
            String[] parsedArgs = args.trim().split(EVENT_SEPARATOR, 2);
            if (parsedArgs.length != 2) {
                throw new InvalidCommandFormatException();
            }
            final String eventDescription = parsedArgs[0].trim();
            final String eventTime = parsedArgs[1].trim();
            return new EventCommand(eventDescription, eventTime);
        } catch (InvalidCommandFormatException e) {
            return new InvalidCommand(COMMAND_EVENT, USAGE_EVENT, true);
        }
    }

    /**
     * Performs input validation for the "mark" command.
     * @param args user-supplied command arguments.
     * @return the prepared command.
     */
    public static Command prepareMark(String args) {
        try {
            int index = Integer.parseInt(args);
            return new MarkCommand(index);
        } catch (NumberFormatException e) {
            return new InvalidCommand(COMMAND_MARK, USAGE_MARK);
        }
    }

    /**
     * Performs input validation for the "unmark" command.
     * @param args user-supplied command arguments.
     * @return the prepared command.
     */
    public static Command prepareUnmark(String args) {
        try {
            int index = Integer.parseInt(args);
            return new UnmarkCommand(index);
        } catch (NumberFormatException e) {
            return new InvalidCommand(COMMAND_UNMARK, USAGE_UNMARK);
        }
    }

    /**
     * Performs input validation for the "delete" command.
     * @param args user-supplied command arguments.
     * @return the prepared command.
     */
    public static Command prepareDelete(String args) {
        try {
            int index = Integer.parseInt(args);
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            return new InvalidCommand(COMMAND_DELETE, USAGE_DELETE);
        }
    }

    private static Command prepareFind(String args) {
        try {
            if (args.equals("")) {
                throw new InvalidCommandFormatException();
            }
            return new FindCommand(args.trim());
        } catch (InvalidCommandFormatException e) {
            return new InvalidCommand(COMMAND_FIND, USAGE_FIND);
        }
    }
}
