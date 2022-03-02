package commands;

import exceptions.MissingDescriptionException;
import tasks.Event;
import tasks.TaskList;
import static common.Message.EVENT_MESSAGE;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class handles the creation of EventCommand instances
 * and execution of the event command.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private static final Pattern FULL_COMMAND = Pattern.compile(COMMAND_WORD + "(?<taskDescription>.*\\S.*)"
            + "/at (?<at>.*\\S.*)");
    private final String taskDescription;
    private final String at;

    /**
     * Creates an EventCommand instance using input from the user.
     *
     * @param parsedInput The user input
     * @throws MissingDescriptionException
     */
    public EventCommand(String parsedInput) throws MissingDescriptionException {
        Matcher matcher = FULL_COMMAND.matcher(parsedInput);
        if (matcher.find()) {
            taskDescription = matcher.group("taskDescription").strip();
            at = matcher.group("at");
        } else {
            throw new MissingDescriptionException();
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public ExecutedCommandResults executeCommand(TaskList tasks) {
        Event event = new Event(taskDescription, at);
        tasks.add(event);
        return new ExecutedCommandResults(EVENT_MESSAGE, event);
    }
}
