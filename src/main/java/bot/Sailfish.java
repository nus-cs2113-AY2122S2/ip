package bot;

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
     * Creates the bot.
     */
    public Sailfish() {
        this.scanner = new Scanner(System.in);
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
            case "bye": // Exit the app.
                System.out.println("Farewell, sailor!");
                return;
            default: // Any command not recognised by the bot.
                System.out.println(command.getCommand());
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
}
