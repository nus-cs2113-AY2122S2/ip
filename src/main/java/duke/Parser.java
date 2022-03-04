package duke;

import duke.command.Command;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ExitCommand;
import duke.command.AddCommand;
import duke.command.MarkCommand;
import duke.command.UnknownCommand;
import duke.command.UnmarkCommand;
import duke.command.DeleteCommand;

public class Parser {
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

        switch (commandWord) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(commandWord, getArguments(fullCommand));
        case "find":
            return new FindCommand(getArguments(fullCommand));
        case "mark":
            taskIndex = Integer.parseInt(getArguments(fullCommand)) - 1;
            return new MarkCommand(taskIndex);
        case "unmark":
            taskIndex = Integer.parseInt(getArguments(fullCommand)) - 1;
            return new UnmarkCommand(taskIndex);
        case "delete":
            taskIndex = Integer.parseInt(getArguments(fullCommand)) - 1;
            return new DeleteCommand(taskIndex);
        default:
            return new UnknownCommand();

        }
    }

    public static String getArguments(String fullCommand) throws DukeException {
        if (fullCommand.split(" ", 2).length < 2) {
            throw new DukeException("OOPS!!! There are missing descriptions.");
        }

        return fullCommand.split(" ", 2)[1];
    }
}
