package bim.command;

import bim.BimException;
import bim.Storage;
import bim.Ui;

import bim.task.Deadline;
import bim.task.Event;
import bim.task.ToDo;
import bim.task.Task;
import bim.task.TaskList;

/**
 * Adds a task to the TaskList. The type of task depends on <code>type</code>
 */
public class AddCommand extends Command {
    private static final String TYPE_DEADLINE = "deadline";
    private static final String TYPE_TODO = "todo";

    private String type;
    private String description;
    private String date;

    public AddCommand(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public AddCommand(String type, String description, String date) {
        this.type = type;
        this.description = description;
        this.date = date;
    }

    /**
     * Creates a new task of the given type, writes the new task to
     * the data file, adds it to the task list and prints out the newly added task
     *
     * @param tasks   Task lists to be added to
     * @param ui      Ui object to print messages
     * @param storage Storage object for saving to disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask;
        if (type.equals(TYPE_TODO)) {
            newTask = new ToDo(this.description);
        }
        else if (type.equals(TYPE_DEADLINE)) {
            newTask = new Deadline(this.description, this.date);
        }
        else {
            newTask = new Event(this.description, this.date);
        }

        try {
            storage.writeData(newTask);
            tasks.addTask(newTask);
            ui.printAddTaskMessage(newTask, tasks.getSize());
        } catch (BimException exception) {
            ui.printErrorMessage(exception.getMessage());
        }

    }

}
