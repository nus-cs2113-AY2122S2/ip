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
        System.out.println("Farewell, sailor!");
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
