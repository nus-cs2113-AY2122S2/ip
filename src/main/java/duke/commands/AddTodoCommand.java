package duke.commands;

import duke.customexceptions.EmptyDescriptionException;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

/**
 * Adds a Todo object into the array list of tasks.
 */
public class AddTodoCommand extends AddCommand {
    private boolean saveIsRequired = true;
    public void execute(ArrayList<Task> tasks, String userInput, int taskUniqueID) {
        try {
            String description = ui.getDescription(userInput);
            Todo newTodo = new Todo(description, taskUniqueID);
            tasks.add(newTodo);
            ui.printMessageForAdding(tasks, newTodo);
            taskUniqueID++;
        } catch (EmptyDescriptionException e) {
            saveIsRequired = false;
            ui.printEmptyDescriptionMessage();
        }
    }

    public boolean isSaveRequired() {
        return saveIsRequired;
    }
}
