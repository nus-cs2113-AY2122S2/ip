package solana.command;

import solana.Storage;
import solana.task.Task;

import static solana.task.TaskList.tasks;

/**
 * Represents the Delete command.
 */
public class DeleteCommand extends Command {
    public static final int CONVERT_TASK_TO_I = -1;

    protected String parsedInput;

    public DeleteCommand(String parsedInput) {
        this.parsedInput = parsedInput;
    }

    /**
     * Prints the deleted message when a task has been deleted, and the current number of tasks in the TaskList.
     *
     * @param taskIndex Index of the task to be deleted.
     */
    public static void printDeletedPrompt(int taskIndex) {
        Task toBeDeleted = tasks.get(taskIndex);
        System.out.print("Deleted: ");
        System.out.println(toBeDeleted);
        tasks.remove(taskIndex);

        if (tasks.size() > 1) {
            System.out.println("You now have " + tasks.size() + " tasks in the list");
        } else {
            System.out.println("You now have " + tasks.size() + " task in the list");
        }
    }

    /**
     * Deletes a task from the TaskList and saves the updated TaskList into the savedTasks.txt file.
     */
    @Override
    public void executeCommand() {
        try {
            int taskIndex = Integer.parseInt(parsedInput);
            printDeletedPrompt(taskIndex + CONVERT_TASK_TO_I);
        } catch (NumberFormatException e) {
            System.out.println("Input a task number!");
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("No such task number!");
        }

        Storage storage = new Storage();
        storage.saveTasks();
        System.out.print(System.lineSeparator());
    }
}
