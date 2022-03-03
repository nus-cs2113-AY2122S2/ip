package Commands;

import Components.Todo;
import Exceptions.MaxTaskException;
import Interfaces.UI;
import Managers.TaskManager;

import static Functions.MessageDisp.printWithLine;

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
            printWithLine("Yup yup yup, " + System.lineSeparator()
                    + todo.toString() + "," + System.lineSeparator()
                    + "annnd there we go, it's been added!" + System.lineSeparator()
                    + "You have " + taskManager.getNumTasks() + " tasks in the list.");
        } catch (Exception e) {
            throw e;
        }
    }
}
