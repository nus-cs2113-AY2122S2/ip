package bot;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Sailfish {
    /**
     * The width for the screen.
     */
    private static final int width = 20;

    /**
     * Scanner object provided to the bot.
     */
    private final Scanner scanner;

    /**
     * The list of tasks stored by the bot.
     */
    private final List<Task> tasks;

    /**
     * Creates the bot.
     */
    public Sailfish() {
        this.scanner = new Scanner(System.in);
        this.tasks = new ArrayList<>();
    }

    /**
     * Makes the bot take control of the application.
     * <p>
     * This method shows the welcome message and then waits for input.
     */
    public void takeControl() {
        // Prints the welcome text.
        this.printWelcome();

        // Input loop.
        while (true) {
            System.out.println("-".repeat(width));
            // Get input from the user.
            Command command = Command.fromString(this.scanner.nextLine().toLowerCase());
            System.out.println("-".repeat(width));

            // Switch the command.
            switch (command.getCommand()) {
            case "list": // List all tasks.
                this.list();
                break;
            case "mark":
                this.mark(command);
                break;
            case "unmark":
                this.unMark(command);
                break;
            case "bye": // Exit the app.
                System.out.println("Farewell, sailor!");
                return;
            default:
                this.add(command);
                break;
            }
        }
    }

    /**
     * Prints the welcome for the first time to the user.
     */
    private void printWelcome() {
        System.out.println("░██████╗░█████╗░██╗██╗░░░░░███████╗██╗░██████╗██╗░░██╗\n" +
                "██╔════╝██╔══██╗██║██║░░░░░██╔════╝██║██╔════╝██║░░██║\n" +
                "╚█████╗░███████║██║██║░░░░░█████╗░░██║╚█████╗░███████║\n" +
                "░╚═══██╗██╔══██║██║██║░░░░░██╔══╝░░██║░╚═══██╗██╔══██║\n" +
                "██████╔╝██║░░██║██║███████╗██║░░░░░██║██████╔╝██║░░██║\n" +
                "╚═════╝░╚═╝░░╚═╝╚═╝╚══════╝╚═╝░░░░░╚═╝╚═════╝░╚═╝░░╚═╝\n" +
                "Commanding the seas since 2022.\n" +
                "What can Poseidon do for you today?");
    }

    /**
     * List all stored tasks.
     */
    private void list() {
        if (this.tasks.size() == 0 ) {
            System.out.println("No tasks!");
            return;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Here are the tasks in your list:\n");

        // Print each task.
        for (int i = 0; i < this.tasks.size(); i++) {
            builder.append(String.format("%d. %s\n", i + 1, this.tasks.get(i)));
        }
        builder.deleteCharAt(builder.length() - 1);

        // Print the tasks.
        System.out.println(builder);
    }

    /**
     * Mark a task as done.
     *
     * @param command Command object containing parsed information.
     */
    private void mark(Command command) {
        // Get the index of the task to mark.
        Task task;
        try {
            task = this.getTask(command);
        } catch (NumberFormatException e) {
            System.out.println("Please specify a integer for the index and range!");
            return;
        }

        // Set the required task as marked.
        task.setMarked(true);
        System.out.printf("Nice! I have marked this task as done:\n%s\n", task);
    }

    /**
     * Mark a task as undone.
     *
     * @param command Command object containing parsed information.
     */
    private void unMark(Command command) {
        Task task;
        try {
            task = this.getTask(command);
        } catch (NumberFormatException e) {
            System.out.println("Please specify a integer for the index and range!");
            return;
        }

        // Set the required task as undone.
        task.setMarked(false);
        System.out.printf("Ok, I've marked this task as not done yet:\n%s\n", task);
    }

    /**
     * Helper method to get a task at a particular index.
     *
     * @param command Command object containing parsed information.
     * @return Task object.
     * @throws NumberFormatException For invalid index.
     */
    private Task getTask(Command command) throws NumberFormatException {
        int index = Integer.parseInt(command.getDesc()) - 1;
        if (index < 0 || index >= this.tasks.size()) {
            throw new NumberFormatException();
        }
        return this.tasks.get(index);
    }

    /**
     * Add a new task.
     *
     * @param command Command object containing parsed information.
     */
    private void add(Command command) {
        this.tasks.add(new Task(String.format("%s %s", command.getCommand(), command.getDesc())));
        System.out.printf("Added task: %s %s\n", command.getCommand(), command.getDesc());
    }
}
