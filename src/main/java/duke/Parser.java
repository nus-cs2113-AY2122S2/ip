package duke;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ExitCommand;
import duke.command.AddCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnknownCommand;
import duke.command.UnmarkCommand;
import duke.command.DeleteCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    // Command Constants
    public static final String LIST = "list";
    public static final String BYE = "bye";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String FIND = "find";
    public static final String MARK = "mark";
    public static final String UNMARK = "unmark";
    public static final String DELETE = "delete";

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
        String[] argumentList;
        String description;
        Task newTask;
        LocalDate dateInput;

        switch (commandWord) {
        case LIST:
            return new ListCommand();
        case BYE:
            return new ExitCommand();
        case TODO:
            arguments = getArguments(fullCommand);
            newTask = new Todo(arguments);
            return new TodoCommand(newTask);
        case DEADLINE:
            arguments = getArguments(fullCommand);
            try {
                argumentList = arguments.split("/by", 2);
                description = argumentList[0].trim(); // eg. return book
                dateInput = LocalDate.parse(argumentList[1].trim()); // yyyy-mm-dd
                newTask = new Deadline(description, dateInput);
                return new DeadlineCommand(newTask);
            } catch (IndexOutOfBoundsException | DateTimeParseException e) {
                throw new DukeException("Incorrect input format. Please enter " +
                        "in this format: deadline project /by 2022-12-31");
            }
        case EventCommand.COMMAND_WORD:
            arguments = getArguments(fullCommand);
            return new EventCommand(arguments);
        case FIND:
            return new FindCommand(getArguments(fullCommand));
        case MARK:
            taskIndex = Integer.parseInt(getArguments(fullCommand)) - 1;
            return new MarkCommand(taskIndex);
        case UNMARK:
            taskIndex = Integer.parseInt(getArguments(fullCommand)) - 1;
            return new UnmarkCommand(taskIndex);
        case DELETE:
            taskIndex = Integer.parseInt(getArguments(fullCommand)) - 1;
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
    public static String getArguments(String fullCommand) throws DukeException {
        if (fullCommand.split(" ", 2).length < 2) {
            throw new DukeException("OOPS!!! There are missing descriptions.");
        }

        return fullCommand.split(" ", 2)[1];
    }
}
