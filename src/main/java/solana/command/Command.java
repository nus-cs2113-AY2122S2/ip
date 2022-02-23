package solana.command;

import solana.task.Task;

import static solana.task.TaskList.tasks;

public abstract class Command {

    public abstract void executeCommand();

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
