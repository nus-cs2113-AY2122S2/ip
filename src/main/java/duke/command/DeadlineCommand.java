package main.java.duke.command;

import main.java.duke.task.Deadline;
import main.java.duke.Duke;
import main.java.duke.ui.Ui;
import main.java.duke.exception.DukeException;

public class DeadlineCommand extends Command {
    
    private String input;

    public DeadlineCommand(String input) {
        this.input = input;
    }

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