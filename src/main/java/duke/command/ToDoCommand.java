package main.java.duke.command;

import main.java.duke.task.ToDo;
import main.java.duke.Duke;
import main.java.duke.ui.Ui;

/**
 * Class for ToDoCommand. It is created when the user requests to create a new ToDo.
 */

public class ToDoCommand extends Command {
    
    private String input;

    /**
     * Constructor for ToDoCommand.
     * 
     * @param input User input.
     */
    public ToDoCommand(String input) {
        this.input = input;
    }

    /**
     * Method to carry out command. It creates a ToDo which is added onto the list, and calls
     * Ui to print that the ToDo has been added onto the task list.
     */
    public void execute() {
        ToDo task = new ToDo(this.input);
        Duke.tasks.add(task);
        Duke.taskCounter++;
        Ui.printTask(task);
    }
}