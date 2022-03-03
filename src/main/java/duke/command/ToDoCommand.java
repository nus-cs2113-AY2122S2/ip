package main.java.duke.command;

import main.java.duke.task.ToDo;
import main.java.duke.Duke;
import main.java.duke.ui.Ui;

public class ToDoCommand extends Command {
    
    private String input;

    public ToDoCommand(String input) {
        this.input = input;
    }

    public void execute() {
        ToDo task = new ToDo(this.input);
        Duke.tasks.add(task);
        Duke.taskCounter++;
        Ui.printTask(task);
    }
}