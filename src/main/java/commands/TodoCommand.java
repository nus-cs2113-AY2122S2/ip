package commands;

import exceptions.MissingDescriptionException;
import tasks.TaskList;
import tasks.Todo;
import static common.Message.TODO_MESSAGE;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class handles creation of TodoCommand instances
 * and execution of the todo command.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    private static final Pattern FULL_COMMAND = Pattern.compile(COMMAND_WORD + "(?<taskDescription>.*\\S.*)");
    private final String taskDescription;

    /**
     * Creates a TodoCommand instance using input from the user.
     *
     * @param parsedInput The user input
     * @throws MissingDescriptionException
     */
    public TodoCommand(String parsedInput) throws MissingDescriptionException {
        Matcher matcher = FULL_COMMAND.matcher(parsedInput);
        if (matcher.find()) {
            taskDescription = matcher.group("taskDescription").strip();
        } else {
            throw new MissingDescriptionException();
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public ExecutedCommandResults executeCommand(TaskList tasks) {
        Todo todo = new Todo(taskDescription);
        tasks.add(todo);
        return new ExecutedCommandResults(TODO_MESSAGE, todo);
    }
}
