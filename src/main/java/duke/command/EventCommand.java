package main.java.duke.command;

import main.java.duke.task.Event;
import main.java.duke.Duke;
import main.java.duke.ui.Ui;
import main.java.duke.exception.DukeException;

public class EventCommand implements Command {
    
    private String input;

    public EventCommand(String input) {
        this.input = input;
    }

    public void execute() throws DukeException {
        String[] splitString = input.split("/at ", 2);
        if (splitString.length < 2) {
            throw new DukeException("Oh no! You need to include a date after '/at'!");
        } else if (splitString[0].equals("")) {
            throw new DukeException("Oh no! You need a description for this event!");
        } else {
            Event task = new Event(splitString[0], splitString[1]);
            Duke.tasks.add(task);
            Duke.taskCounter++;
            Ui.printTask(task);
        }
    }
}