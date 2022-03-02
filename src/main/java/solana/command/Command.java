package solana.command;

import solana.task.Task;

import static solana.task.TaskList.tasks;

/**
 * Represents an executable command. This class cannot be instantiated and serves as a Parent class for specific
 * Command classes to inherit from.
 */
public abstract class Command {

    /**
     * Executes the given command. This method is to be implemented by child classes.
     */
    public abstract void executeCommand();

    /**
     * Prints the added message when a task has been added, and the current number of tasks in the tasklist.
     *
     * @param newTask The new added task.
     */
    public void printAddedPrompt(Task newTask) {
        System.out.print("Added: ");
        System.out.println(newTask);

        if (tasks.size() > 1) {
            System.out.println("You now have " + tasks.size() + " tasks in the list" + System.lineSeparator());
        } else {
            System.out.println("You now have " + tasks.size() + " task in the list" + System.lineSeparator());
        }
    }
}
