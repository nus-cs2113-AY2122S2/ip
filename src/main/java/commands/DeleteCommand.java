package commands;

import exceptions.MissingDescriptionException;
import tasks.Task;
import tasks.TaskList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static common.Message.DELETE_MESSAGE;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private static final Pattern FULL_COMMAND = Pattern.compile(COMMAND_WORD + " (?<taskIndex>\\d+)");
    private final int taskIndex;

    public DeleteCommand(String parsedInput) throws MissingDescriptionException {
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
        tasks.remove(taskIndex - 1);
        return new ExecutedCommandResults(DELETE_MESSAGE, task);
    }
}
