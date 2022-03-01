package main.java.duke.command;

import main.java.duke.task.Task;
import main.java.duke.Duke;
import main.java.duke.ui.Ui;

public class DeleteCommand implements Command {

    private final int deleteInt;

    public DeleteCommand(int deleteInt) {
        this.deleteInt = deleteInt;
    }

    public void execute() {
        Task task = Duke.tasks.get(deleteInt - 1);
        Duke.tasks.remove(deleteInt - 1);
        Duke.taskCounter--;
        Ui.printDelete(task);
    }
}