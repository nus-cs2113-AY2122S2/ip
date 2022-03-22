package duke;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ExitCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnknownCommand;
import duke.command.UnmarkCommand;
import duke.command.DeleteCommand;

public class Parser {
    public static final String EXCEPTION_NEED_ARGS = "OOPS!!! There are missing descriptions.";

    /**
     * Returns a Command that can be executed later on. There are different
     * types of Command that can be created, based on the user input.
     *
     * @param fullCommand A single line argument that the user keys in
     * @return The Command containing its arguments, if any
     * @throws DukeException If there is an unknown command
     */
    public static Command parse(String fullCommand) throws DukeException {
        String commandWord = fullCommand.split(" ", 2)[0];
        int taskIndex;
        String arguments;

        switch (commandWord) {
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case TodoCommand.COMMAND_WORD:
            arguments = getSecondArgument(fullCommand);
            return new TodoCommand(arguments);
        case DeadlineCommand.COMMAND_WORD:
            arguments = getSecondArgument(fullCommand);
            return new DeadlineCommand(arguments);
        case EventCommand.COMMAND_WORD:
            arguments = getSecondArgument(fullCommand);
            return new EventCommand(arguments);
        case FindCommand.COMMAND_WORD:
            return new FindCommand(getSecondArgument(fullCommand));
        case MarkCommand.COMMAND_WORD:
            /** Zero-based indexing to be parsed in */
            taskIndex = Integer.parseInt(getSecondArgument(fullCommand)) - 1;
            return new MarkCommand(taskIndex);
        case UnmarkCommand.COMMAND_WORD:
            taskIndex = Integer.parseInt(getSecondArgument(fullCommand)) - 1;
            return new UnmarkCommand(taskIndex);
        case DeleteCommand.COMMAND_WORD:
            taskIndex = Integer.parseInt(getSecondArgument(fullCommand)) - 1;
            return new DeleteCommand(taskIndex);
        default:
            return new UnknownCommand();
        }
    }

    /**
     * Get the second argument from the user input
     *
     * @param fullCommand Represents the full command given by the user
     * @return Returns the second argument as a String
     * @throws DukeException If there is no second argument provided
     * by the user but is required by the command
     */
    public static String getSecondArgument(String fullCommand) throws DukeException {
        if (fullCommand.split(" ", 2).length < 2) {
            throw new DukeException(EXCEPTION_NEED_ARGS);
        }

        /** Return the second argument provided */
        return fullCommand.split(" ", 2)[1];
    }
}
