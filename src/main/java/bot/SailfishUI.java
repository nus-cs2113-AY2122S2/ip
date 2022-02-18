package bot;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * The bot.
 */
public final class SailfishUI {
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
    private final TaskManager manager;

    /**
     * Creates the bot.
     */
    public SailfishUI() throws IOException {
        this.scanner = new Scanner(System.in);
        this.manager = new TaskManager();
    }

    /**
     * Makes the bot take control of the application.
     * <p>
     * This method shows the welcome message and then waits for input.
     *
     * @throws IOException Error saving data.
     */
    public void takeControl() throws IOException {
        // Prints the welcome text.
        this.printWelcome();

        // Input loop.
        while (true) {
            System.out.println("-".repeat(WIDTH));
            // Get input from the user.
            CommandParser commandParser = CommandParser.fromString(this.scanner.nextLine());
            System.out.println("-".repeat(WIDTH));

            // Switch the command.
            switch (commandParser.getCommand()) {
            case "list": // List all tasks.
                this.list();
                break;
            case "mark": // Mark a task as done.
                this.mark(commandParser);
                break;
            case "unmark": // Mark a task as not done.
                this.unMark(commandParser);
                break;
            case "delete":
                this.delete(commandParser);
                break;
            case "find":
                this.find(commandParser);
                break;
            case "bye": // Exit the app.
                this.bye();
                return;
            default: // By default, we assume that the user is using a command that adds a task.
                this.addTask(commandParser);
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
        if (this.manager.getNumTasks() == 0) {
            System.out.println("No tasks!");
            return;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Here are the tasks in your list:\n");

        // Add each task.
        for (int i = 0; i < this.manager.getNumTasks(); i++) {
            builder.append(String.format("%d. %s\n", i + 1, this.manager.getTask(i)));
        }
        builder.deleteCharAt(builder.length() - 1);  // Remove last newline.

        // Print the tasks.
        System.out.println(builder);
    }

    /**
     * Mark a task as done.
     *
     * @param commandParser Command object containing parsed information.
     */
    private void mark(CommandParser commandParser) {
        // Get the index of the task to mark.
        Task task;
        try {
            task = this.manager.getTask(commandParser);
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
     * @param commandParser Command object containing parsed information.
     */
    private void unMark(CommandParser commandParser) {
        Task task;
        try {
            task = this.manager.getTask(commandParser);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        }

        // Set the required task as undone.
        task.setDone(false);
        System.out.printf("Ok, I've marked this task as not done yet:\n%s\n", task);
    }

    /**
     * Find tasks with description matching provided keyword.
     *
     * @param commandParser Command object containing parsed information.
     */
    private void find(CommandParser commandParser) {
        // Do not allow search with empty search string.
        if (commandParser.getDesc().isEmpty()) {
            System.out.println("Search parameter cannot be blank!");
            return;
        }
        
        List<Task> tasks = this.manager.findTasks(commandParser.getDesc());
        if (tasks.size() == 0) {
            System.out.println("No tasks matched your keyword!");
            return;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("We found the following tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            builder.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        builder.deleteCharAt(builder.length() - 1);

        // Print found tasks.
        System.out.println(builder);
    }

    /**
     * Exit the application.
     *
     * @throws IOException Error saving data.
     */
    private void bye() throws IOException {
        System.out.println("Farewell, sailor!");
        this.manager.saveData();
    }

    /**
     * Delete a task by index.
     *
     * @param commandParser Command object containing parsed information.
     */
    private void delete(CommandParser commandParser) {
        Task task;
        try {
            task = this.manager.getTask(commandParser);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        }

        // Remove this task.
        this.manager.deleteTask(task);
        System.out.printf("Noted. I've removed this task:\n\t%s\n" +
                "Now you have %d tasks in the list.\n", task, this.manager.getNumTasks());
    }

    /**
     * Add a new task.
     *
     * @param commandParser Command object containing parsed information.
     */
    private void addTask(CommandParser commandParser) {
        // Create the required task.
        Task newTask;
        try {
            switch (commandParser.getCommand()) {
            case "todo":
                newTask = new Todo(commandParser.getDesc(), false);
                break;
            case "deadline":
                newTask = new Deadline(commandParser.getDesc(), false, commandParser.getArgument(Deadline.REQ_ARG));
                break;
            case "event":
                newTask = new Event(commandParser.getDesc(), false, commandParser.getArgument(Event.REQ_ARG));
                break;
            default:
                throw new IllegalArgumentException("Unknown command!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        // Really add it now!
        this.manager.addTask(newTask);
        System.out.printf("Got it. I've added this task:\n\t%s\n" +
                "Now you have %d tasks in the list.\n", newTask, this.manager.getNumTasks());
    }
}
