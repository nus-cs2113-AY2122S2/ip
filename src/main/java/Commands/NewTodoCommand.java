package Commands;

import Components.Todo;

import Exceptions.MaxTaskException;

import Interfaces.UI;

import Managers.TaskManager;

/**
 * Command for Bao to create a task to add to task list.
 */
public class NewTodoCommand extends Command {
    private String description;

    /**
     * Creates new-todo command with specified task description.
     *
     * @param description Description of task to be completed.
     */
    public NewTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Creates and adds the new task to the task list.
     *
     * @param taskManager TaskManager to execute command on.
     * @param ui The interface that provides interaction with the user.
     */
    @Override
    public void execute(TaskManager taskManager, UI ui) throws MaxTaskException {
        try {
            Todo todo = new Todo(description);
            taskManager.addTask(todo);
            ui.newTaskMessage(todo);
        } catch (Exception e) {
            throw e;
        }
    }
}
