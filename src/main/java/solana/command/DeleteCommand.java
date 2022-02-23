package solana.command;

import solana.Storage;
import solana.task.Task;

import static solana.task.TaskList.tasks;

public class DeleteCommand extends Command {
    public static final int CONVERT_TASK_TO_I = -1;

    protected String parsedInput;

    public DeleteCommand(String parsedInput) {
        this.parsedInput = parsedInput;
    }

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
