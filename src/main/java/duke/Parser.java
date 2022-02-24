package duke;

import duke.command.*;
import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Parser {
    public static final String COMMAND_EXIT = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_FIND = "find";
    public static final String COMMAND_DELETE = "delete";
    /**
     * Checks the String inputted by the user and executes the appropriate command
     * using a switch statement.
     *
     * @param nextLine The command to be executed.
     */
    public static Command parse(String nextLine) throws DukeException {
        ArrayList<String> pieces = new ArrayList<>(Arrays.asList(nextLine.split(" ")));
        String commandType = pieces.get(0);
        switch (commandType) {
        case COMMAND_EXIT:
            return new ExitCommand();
        case COMMAND_LIST:
            return new ListCommand();
        case COMMAND_MARK:
            String taskToMark = pieces.get(1);
            return new MarkCommand(taskToMark);
        case COMMAND_UNMARK:
            String taskToUnmark = pieces.get(1);
            return new UnmarkCommand(taskToUnmark);
        case COMMAND_DELETE:
            String taskToDelete = pieces.get(1);
            return new DeleteCommand(taskToDelete);
        case COMMAND_FIND:
            String taskToFind = pieces.get(1);
            return new FindCommand(taskToFind);
        case COMMAND_TODO:
        case COMMAND_DEADLINE:
        case COMMAND_EVENT:
            return new AddCommand(pieces);
        default:
            throw new DukeException("Command not found.");
        }
    }
}
