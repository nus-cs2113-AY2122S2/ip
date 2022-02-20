package duke.command;

import duke.data.task.Todo;

import java.util.ArrayList;

import static duke.common.Strings.MESSAGE_IMPOSSIBLE;
import static duke.common.Strings.MESSAGE_TODO_ADDED;

public class TodoCommand extends Command {
    private final Todo newTodo;

    public TodoCommand(String taskDescription) {
        super();
        newTodo = new Todo(taskDescription);
    }

    public ArrayList<String> execute() {
        if (taskList == null) {
            commandFeedback.add(MESSAGE_IMPOSSIBLE);
            commandFeedback.add(null);
            return commandFeedback;
        }
        taskList.addTask(newTodo);
        commandFeedback.add(MESSAGE_TODO_ADDED);
        commandFeedback.add(newTodo.toString());
        return commandFeedback;
    }
}
