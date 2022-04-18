package duke.command;

import duke.data.task.Todo;

import java.util.ArrayList;

import static duke.common.Strings.*;

/**
 * Adds a new todo task.
 */
public class TodoCommand extends Command {
    private final Todo newTodo;

    public TodoCommand(String taskDescription) {
        super();
        newTodo = new Todo(taskDescription);
    }

    @Override
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
