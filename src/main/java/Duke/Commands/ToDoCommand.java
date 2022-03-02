package Duke.Commands;

import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Tasks.Todo;
import Duke.Ui.Ui;

public class ToDoCommand extends Command {
    private Todo toDo;

    public ToDoCommand(String description, boolean isDone) {
        toDo = new Todo(description, isDone);
    }

    public boolean isExit() {
        return false;
    }

    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(toDo);
        System.out.println(Ui.DISPLAY_LINE + System.lineSeparator() + "Okay! I've added this task:");
        System.out.println(toDo);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.\n" + Ui.DISPLAY_LINE);
    }

}
