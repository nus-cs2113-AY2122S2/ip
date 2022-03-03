package Commands;

import Components.Todo;
import Exceptions.MaxTaskException;
import Interfaces.UI;
import Managers.TaskManager;

import static Functions.MessageDisp.printWithLine;

public class NewTodoCommand extends Command {
    private String description;

    public NewTodoCommand(String description) {
        this.description = description;
    }

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
