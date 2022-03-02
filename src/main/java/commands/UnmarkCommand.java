package commands;

import exceptions.MissingDescriptionException;
import tasks.Task;
import tasks.TaskList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static common.Message.UNMARK_MESSAGE;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private static final Pattern FULL_COMMAND = Pattern.compile(COMMAND_WORD + " (?<taskIndex>\\d+)");
    private final int taskIndex;

    public UnmarkCommand(String parsedInput) throws MissingDescriptionException {
        Matcher matcher = FULL_COMMAND.matcher(parsedInput);
        if (matcher.find()) {
            taskIndex = Integer.parseInt(matcher.group("taskIndex"));
        } else {
            throw new MissingDescriptionException();
        }
    }

    @Override
    public ExecutedCommandResults executeCommand(TaskList tasks) {
        Task task = tasks.get(taskIndex - 1);
        task.setCompleted(false);
        return new ExecutedCommandResults(UNMARK_MESSAGE, task);
    }
}
