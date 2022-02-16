package duke;

import duke.commands.Command;

import duke.exceptions.InvalidArgumentException;
import duke.tasks.Task;

import java.util.ArrayList;



public class Handler {

    protected ArrayList<Task> taskList;

    /**
     * Initialises a Handler instance with an empty lists of task
     * Functions as a singleton class (without the appropriate code), instantiate only one
     */
    public Handler() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Execute a command based on user input. Passes off control to relevant functions
     *
     * @param userCommand the user command to run
     */
    public void executeGivenCommand(Command userCommand) {
        userCommand.setTaskList(this.taskList);
        userCommand.execute();
    }
}
