package main.java.duke.command;

import main.java.duke.task.Deadline;
import main.java.duke.Duke;
import main.java.duke.ui.Ui;
import main.java.duke.exception.DukeException;

/**
 * Class for DeadlineCommand. It is called when the user wants to create a Deadline.
 */

public class DeadlineCommand extends Command {
    
    private String input;

    /**
     * Constructor for DeadlineCommand.
     * 
     * @param input User input.
     */
    public DeadlineCommand(String input) {
        this.input = input;
    }

    /**
     * Method to carry out the command. It checks if user input is valid, creates a new Deadline
     * and adds it onto the task list. It then calls the Ui to print if the task has been added.
     * 
     * @throws DukeException If user input is invalid.
     */
    public void execute() throws DukeException {
        String[] splitString = input.split(" /by ", 2);
        if (splitString.length < 2) {
            throw new DukeException("Oh no! You need to include a date after '/by'!");
        } else if (splitString[0].equals("")) {
            throw new DukeException("Oh no! You need a description for this event!");
        } else {
            Deadline task = new Deadline(splitString[0], convertDate(splitString[1]), 
                    convertTime(splitString[1]));
            Duke.tasks.add(task);
            Duke.taskCounter++;
            Ui.printTask(task);
        }
    }
}