package bim.command;

import bim.BimException;
import bim.Storage;
import bim.Ui;
import bim.task.*;

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
