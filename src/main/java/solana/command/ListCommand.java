package solana.command;

import solana.task.Task;

import static solana.task.TaskList.tasks;

public class ListCommand extends Command {
    public static final int STARTING_LIST_NUMBER = 1;

    public static void printTasks() {
        int listNumber = STARTING_LIST_NUMBER;
        for (Task task : tasks) {
            System.out.print(listNumber + ".");
            System.out.println(task);
            listNumber++;
        }
    }

    @Override
    public void executeCommand() {
        if (tasks.size() == 0) {
            System.out.println("No tasks yet!");
        } else {
            System.out.println("Here are the tasks in your list:");
            printTasks();
        }
        System.out.print(System.lineSeparator());
    }
}
