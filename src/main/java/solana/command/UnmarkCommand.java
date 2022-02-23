package solana.command;

import solana.Storage;

import static solana.task.TaskList.tasks;

public class UnmarkCommand extends Command {
    public static final int CONVERT_TASK_TO_I = -1;

    protected String parsedInput;

    public UnmarkCommand(String parsedInput) {
        this.parsedInput = parsedInput;
    }

    @Override
    public void executeCommand() {
        try {
            int taskIndex = Integer.parseInt(parsedInput);
            tasks.get(taskIndex + CONVERT_TASK_TO_I).unmarkAsDone();
            System.out.println("OK, I've marked this task as not done yet:");
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
