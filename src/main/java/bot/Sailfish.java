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
        StringBuilder builder = new StringBuilder();
        builder.append("Here are the tasks in your list:\n");

        // Print each task.
        for (int i = 0; i < this.tasks.size(); i++) {
            builder.append(String.format("%d. %s\n", i + 1, this.tasks.get(i)));
        }

        // Print the tasks.
        System.out.println(builder);
    }

    private void add(Command command) {
        this.tasks.add(new Task(String.format("%s %s", command.getCommand(), command.getDesc())));
        System.out.printf("Added task: %s %s\n", command.getCommand(), command.getDesc());
    }
}
