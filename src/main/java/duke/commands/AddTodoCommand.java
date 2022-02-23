package duke.commands;

import duke.customexceptions.EmptyDescriptionException;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class AddTodoCommand extends Command {
    public void execute(ArrayList<Task> tasks, String userInput, int taskUniqueID) {
        try {
            String description = ui.getDescription(userInput);
            Todo newTodo = new Todo(description, taskUniqueID);
            tasks.add(newTodo);
            ui.printMessageForAdding(tasks, newTodo);
            taskUniqueID++;
        } catch (EmptyDescriptionException e) {
            ui.printEmptyDescriptionMessage();
        }
    }
}
