package commands;

import exceptions.InvalidIndexException;
import exceptions.MissingDescriptionException;
import tasks.Task;
import tasks.TaskList;
import static common.Message.DELETE_MESSAGE;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class handles the creation of DeleteCommand instances
 * and execution of the delete command.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private static final Pattern FULL_COMMAND = Pattern.compile(COMMAND_WORD + " (?<taskIndex>\\d+)");
    private final int taskIndex;

    /**
     * Creates a DeleteCommand instance using input from the user.
     *
     * @param parsedInput The user input
     * @throws MissingDescriptionException
     */
    public DeleteCommand(String parsedInput) throws MissingDescriptionException {
        Matcher matcher = FULL_COMMAND.matcher(parsedInput);
        if (matcher.find()) {
            taskIndex = Integer.parseInt(matcher.group("taskIndex"));
        } else {
            throw new MissingDescriptionException();
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public ExecutedCommandResults executeCommand(TaskList tasks) throws InvalidIndexException {
        if (taskIndex <= 0 || taskIndex > tasks.size()) {
            throw new InvalidIndexException();
        }
        Task task = tasks.get(taskIndex - 1);
        tasks.remove(taskIndex - 1);
        return new ExecutedCommandResults(DELETE_MESSAGE, task);
    }
}
