package commands;

import exceptions.MissingDescriptionException;
import tasks.TaskList;
import static common.Message.LIST_MESSAGE;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private static final Pattern FULL_COMMAND = Pattern.compile(COMMAND_WORD + "(?<keyword>.*)");
    private final String keyword;

    public FindCommand(String parsedInput) throws MissingDescriptionException {
        Matcher matcher = FULL_COMMAND.matcher(parsedInput);
        if (matcher.find()) {
            keyword = matcher.group("keyword").strip();
        } else {
            throw new MissingDescriptionException();
        }
    }

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
