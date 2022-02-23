package solana.command;

import solana.Storage;

import static solana.task.TaskList.tasks;

/**
 * Represents the Mark command.
 */
public class MarkCommand extends Command {
    public static final int CONVERT_TASK_TO_I = -1;

    protected String parsedInput;

    public MarkCommand(String parsedInput) {
        this.parsedInput = parsedInput;
    }

    /**
     * Marks the given task as done and saves the updated TaskList into the savedTasks.txt file.
     */
    @Override
    public void executeCommand() {
        try {
            int taskIndex = Integer.parseInt(parsedInput);
            tasks.get(taskIndex + CONVERT_TASK_TO_I).markAsDone();
            System.out.println("Nice, I've marked this task as done:");
            System.out.println(tasks.get(taskIndex + CONVERT_TASK_TO_I));
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
