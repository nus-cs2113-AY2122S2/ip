package commands;

import exceptions.InvalidIndexException;
import exceptions.MissingDescriptionException;
import tasks.Task;
import tasks.TaskList;
import static common.Message.UNMARK_MESSAGE;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class handles creation of UnmarkCommand instances
 * and execution of the unmark command.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private static final Pattern FULL_COMMAND = Pattern.compile(COMMAND_WORD + " (?<taskIndex>\\d+)");
    private final int taskIndex;

    /**
     * Creates an UnmarkCommand instance using input from the user.
     *
     * @param parsedInput The user input
     * @throws MissingDescriptionException
     */
    public UnmarkCommand(String parsedInput) throws MissingDescriptionException {
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
        task.setCompleted(false);
        return new ExecutedCommandResults(UNMARK_MESSAGE, task);
    }
}
