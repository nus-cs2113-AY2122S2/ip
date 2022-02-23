package solana.command;

import solana.task.Task;

import static solana.task.TaskList.tasks;

public class FindCommand extends Command {
    public static final int STARTING_LIST_NUMBER = 1;

    protected String parsedInput;

    public FindCommand(String parsedInput) {
        this.parsedInput = parsedInput;
    }

    @Override
    public void executeCommand() {
        String keyword = parsedInput.toLowerCase();
        int listNumber = STARTING_LIST_NUMBER;
        boolean isFound = false;

        for (Task task : tasks) {
            if (task.toString().toLowerCase().contains(keyword)) {
                System.out.print(listNumber + ".");
                System.out.println(task);
                isFound = true;
            }
            listNumber++;
        }

        if (!isFound) {
            System.out.println("No task contains \"" + keyword + "\"");
        }
        System.out.print(System.lineSeparator());
    }
}
