package duke.Commands;

import duke.exception.IllegalFormatException;
import duke.exception.IndexOutOfRangeException;
import duke.tasks.TaskList;

/**
 * Represents a command.
 */

public abstract class Command {
    public abstract void execute() throws IndexOutOfRangeException, IllegalFormatException;
    protected TaskList taskList;

    public Command(){

    }

    public void setData(TaskList taskList){
        this.taskList = taskList;
    }
}
