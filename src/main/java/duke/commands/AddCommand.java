package duke.commands;

import duke.task.Task;
import duke.userinterface.UserInterface;

import java.util.ArrayList;

/**
 * AddCommand contains the additional abstract isSaveRequired boolean variable to be validated against
 * for data saving while adding
 */
public abstract class AddCommand extends Command {
    public static UserInterface ui = new UserInterface();
    public abstract void execute(ArrayList<Task> tasks, String userInput, int taskUniqueID);
    public abstract boolean isSaveRequired();
}