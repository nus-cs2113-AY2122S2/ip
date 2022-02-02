package bot;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The bot.
 */
public final class Sailfish {
    /**
     * The width for the screen.
     */
    private static final int WIDTH = 20;

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
            System.out.println("-".repeat(WIDTH));
            // Get input from the user.
            Command command = Command.fromString(this.scanner.nextLine());
            System.out.println("-".repeat(WIDTH));

            // Switch the command.
            switch (command.getCommand()) {
            case "list": // List all tasks.
                this.list();
                break;
            case "mark": // Mark a task as done.
                this.mark(command);
                break;
            case "unmark": // Mark a task as not done.
                this.unMark(command);
                break;
            case "bye": // Exit the app.
                System.out.println("Farewell, sailor!");
                return;
            default: // By default, we assume that the user is using a command that adds a task.
                this.addTask(command);
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
     * Helper method to get a task at a particular index.
     *
     * @param command Command object containing parsed information.
     * @return Task object.
     * @throws NumberFormatException For invalid index.
     */
    private Task getTask(Command command) throws NumberFormatException {
        int index = Integer.parseInt(command.getDesc()) - 1;
        if (index < 0 || index >= this.tasks.size()) {
            throw new NumberFormatException("Please specify a integer for the index and range!");
        }
        return this.tasks.get(index);
    }

    /**
     * List all stored tasks.
     */
    private void list() {
        if (this.tasks.size() == 0) {
            System.out.println("No tasks!");
            return;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Here are the tasks in your list:\n");

        // Add each task.
        for (int i = 0; i < this.tasks.size(); i++) {
            builder.append(String.format("%d. %s\n", i + 1, this.tasks.get(i)));
        }
        builder.deleteCharAt(builder.length() - 1);  // Remove last newline.

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
            System.out.println(e.getMessage());
            return;
        }

        // Set the required task as marked.
        task.setDone(true);
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
            System.out.println(e.getMessage());
            return;
        }

        // Set the required task as undone.
        task.setDone(false);
        System.out.printf("Ok, I've marked this task as not done yet:\n%s\n", task);
    }

    /**
     * Add a new task.
     *
     * @param command Command object containing parsed information.
     */
    private void addTask(Command command) {
        // Create the required task.
        Task newTask;
        try {
            switch (command.getCommand()) {
            case "todo":
                newTask = new Todo(command.getDesc());
                break;
            case "deadline":
                newTask = new Deadline(command.getDesc(), command.getArgument(Deadline.REQ_ARG));
                break;
            case "event":
                newTask = new Event(command.getDesc(), command.getArgument(Event.REQ_ARG));
                break;
            default:
                throw new IllegalArgumentException("Unknown command!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        // Really add it now!
        this.tasks.add(newTask);
        System.out.printf("Got it. I've added this task:\n\t%s\n" +
                "Now you have %d tasks in the list.\n", newTask, this.tasks.size());
    }
}
