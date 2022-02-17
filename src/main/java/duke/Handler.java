package duke;

import duke.commands.Command;

import duke.tasks.Task;
import duke.tasks.TaskList;

public class Handler {

    protected TaskList<Task> taskList;

    /**
     * Initialises a Handler instance with an empty lists of task
     * Functions as a singleton class (without the appropriate code), instantiate only one
     */
    public Handler() {
        this.taskList = new TaskList<Task>();
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
