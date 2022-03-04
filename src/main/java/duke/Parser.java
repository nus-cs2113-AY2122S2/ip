package duke;

import duke.command.FindTaskCommand;
import duke.command.UpdateTaskStatusCommand;
import duke.command.AddTaskCommand;
import duke.command.DeleteTaskCommand;
import duke.command.Command;
import duke.command.PrintListCommand;
import duke.command.ExitProgramCommand;

import duke.exception.DukeException;
import duke.exception.DukeExceptionCause;

/**
 * Represents a parser for the user input. The Parser class contains the method for
 * converting the user input into a Command Object.
 */

public class Parser {

    /**
     * Returns a Command Object that was derived from the user input.
     * The parseInput command takes in the user input and returns a Command Object containing
     * the command to be executed by the ChatBot.
     *
     * @param userInput A string input from the user.
     * @return The Command Object containing the command to be executed by the ChatBot.
     * @throws DukeException If either the command or the index of the task is invalid or if the task index, task name,
     *                       keyword is empty.
     */
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
        } else if (userInput.startsWith("deadline") || userInput.startsWith("event") ||
                userInput.startsWith("todo")) {
            try {
                newCommand = new AddTaskCommand(userInput);
            } catch (DukeException de) {
                throw de;
            }
        } else if (userInput.startsWith("delete ")) {
            try {
                newCommand = new DeleteTaskCommand(userInput);
            } catch (DukeException de) {
                throw de;
            }
        } else if (userInput.startsWith("find ")) {
            try {
                newCommand = new FindTaskCommand(userInput);
            } catch (DukeException de) {
                throw de;
            }
        } else {
            throw new DukeException(DukeExceptionCause.InvalidCommand);
        }
        return newCommand;
    }
}
