package duke;

import duke.command.Command;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ExitCommand;
import duke.command.AddCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.DeleteCommand;

public class Parser {
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
            // Throw exception if unknown command inputted
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static String getArguments(String fullCommand) throws DukeException {
        if (fullCommand.split(" ", 2).length < 2) {
            throw new DukeException("OOPS!!! There are missing descriptions.");
        }

        return fullCommand.split(" ", 2)[1];
    }
}
