package commands;

import exceptions.MissingDescriptionException;
import tasks.TaskList;
import static common.Message.LIST_MESSAGE;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class handles the creation of FindCommand instances
 * and execution of the find command.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private static final Pattern FULL_COMMAND = Pattern.compile(COMMAND_WORD + "(?<keyword>.*)");
    private final String keyword;

    /**
     * Creates a FindCommand instance using input from the user.
     *
     * @param parsedInput The user input
     * @throws MissingDescriptionException
     */
    public FindCommand(String parsedInput) throws MissingDescriptionException {
        Matcher matcher = FULL_COMMAND.matcher(parsedInput);
        if (matcher.find()) {
            keyword = matcher.group("keyword").strip();
        } else {
            throw new MissingDescriptionException();
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public ExecutedCommandResults executeCommand(TaskList tasks) {
        TaskList foundTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskDescription().contains(keyword)) {
                foundTasks.add(tasks.get(i));
            }
        }
        return new ExecutedCommandResults(LIST_MESSAGE, foundTasks);
    }
}
