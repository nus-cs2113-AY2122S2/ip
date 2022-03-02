package commands;

import exceptions.InvalidIndexException;
import tasks.TaskList;

/**
 * The abstract class which is the basis for all Command classes.
 */
public abstract class Command {

    /**
     * Creates the instance of an object that stores the information to be passed to the Ui class for printing.
     *
     * @param tasks The TaskList containing all the tasks
     * @return An instance containing relevant information
     * @throws InvalidIndexException Only when dealing with commands that does list indexing
     */
    public abstract ExecutedCommandResults executeCommand(TaskList tasks) throws InvalidIndexException;
}
