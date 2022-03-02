package commands;

import exceptions.MissingDescriptionException;
import tasks.Deadline;
import tasks.TaskList;
import static common.Message.DEADLINE_MESSAGE;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private static final Pattern FULL_COMMAND = Pattern.compile(COMMAND_WORD + "(?<taskDescription>.*\\S.*)"
            + "/by (?<by>.*\\S.*)");
    private final String taskDescription;
    private final String by;

    public DeadlineCommand(String parsedInput) throws MissingDescriptionException {
        Matcher matcher = FULL_COMMAND.matcher(parsedInput);
        if (matcher.find()) {
            taskDescription = matcher.group("taskDescription").strip();
            by = matcher.group("by").strip();
        } else {
            throw new MissingDescriptionException();
        }
    }

    @Override
    public ExecutedCommandResults executeCommand(TaskList tasks) {
        Deadline deadline = new Deadline(taskDescription, by);
        tasks.add(deadline);
        return new ExecutedCommandResults(DEADLINE_MESSAGE, deadline);
    }
}
