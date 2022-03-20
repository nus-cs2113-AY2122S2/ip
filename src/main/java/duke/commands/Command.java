package duke.commands;

import duke.task.Task;
import duke.userinterface.UserInterface;

import java.util.ArrayList;

/**
 * Abstract Command class contains the abstract .execute method which all child command classes must implement.
 * Also contains an instance of ui which child command classes use.
 */
public abstract class Command {
    public static UserInterface ui = new UserInterface();
    public abstract void execute(ArrayList<Task> tasks, String userInput, int taskUniqueID);
}