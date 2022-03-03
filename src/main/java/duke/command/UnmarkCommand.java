package main.java.duke.command;

import main.java.duke.task.Task;
import main.java.duke.Duke;
import main.java.duke.ui.Ui;

public class UnmarkCommand extends Command {
    
    private final int markInt;

    public UnmarkCommand(int markInt) {
        this.markInt = markInt;
    }

    public void execute() {
        Task task = Duke.tasks.get(markInt - 1);
        task.setDone(false);
        Ui.printUnmark(markInt, task);
    }
}