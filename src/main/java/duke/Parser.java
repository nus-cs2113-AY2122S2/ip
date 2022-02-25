package duke;

import duke.command.AddTaskCommand;
import duke.command.Command;
import duke.command.DeleteTaskCommand;
import duke.command.ExitProgramCommand;
import duke.command.PrintListCommand;
import duke.command.UpdateTaskStatusCommand;

import duke.exception.DukeException;
import duke.exception.DukeExceptionCause;

public class Parser {
    public static Command parseInput(String userInput) throws DukeException {
        Command newCommand;
        if (userInput.equals("bye")) {
            newCommand = new ExitProgramCommand();
        } else if (userInput.equals("list")) {
            newCommand = new PrintListCommand();
        } else if (userInput.startsWith("mark")) {
            newCommand = new UpdateTaskStatusCommand(userInput, true);
        } else if (userInput.startsWith("unmark")) {
            newCommand = new UpdateTaskStatusCommand(userInput, false);
        } else if (userInput.startsWith("deadline") || userInput.startsWith("event") || userInput.startsWith("todo")) {
            try {
                newCommand = new AddTaskCommand(userInput);
            } catch (DukeException de) {
                throw de;
            }
        } else if (userInput.startsWith("delete")) {
            try {
                newCommand = new DeleteTaskCommand(userInput);
            } catch (DukeException de) {
                throw de;
            }
        } else {
            throw new DukeException(DukeExceptionCause.INVALIDCOMMAND);
        }
        return newCommand;
    }
}
