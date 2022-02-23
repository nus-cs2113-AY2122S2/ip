package duke.commands;

import duke.task.Task;
import duke.userinterface.UserInterface;

import java.util.ArrayList;

public abstract class Command {
    public static UserInterface ui = new UserInterface();
    public abstract void execute(ArrayList<Task> tasks, String userInput, int taskUniqueID);
}