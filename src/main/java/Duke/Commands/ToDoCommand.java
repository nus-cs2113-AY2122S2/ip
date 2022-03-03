package Duke.Commands;

import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Tasks.Todo;
import Duke.Ui.Ui;

/**
 * Represents the command to add a todo task.
 */
public class ToDoCommand extends Command {
    private Todo toDo;

    public ToDoCommand(String description, boolean isDone) {
        toDo = new Todo(description, isDone);
    }

    /**
     * Indicates whether the program should exit.
     *
     * @return Indication on whether the program should exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command.
     *
     * @param tasks Tasklist containing of  the tasks.
     * @param ui    User interface of Duke.
     * @param storage   Storage of Duke.
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(toDo);
        System.out.println(Ui.DISPLAY_LINE + System.lineSeparator() + "Okay! I've added this task:");
        System.out.println(toDo);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.\n" + Ui.DISPLAY_LINE);
    }

}
