package solana;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    public static final String PROMPT = "> ";

    private final Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Prints logo and welcome message at startup.
     */
    public void printIntro() {
        String logo = "  __|   _ \\  |       \\     \\ |    \\\n"
                + "\\__ \\  (   | |      _ \\   .  |   _ \\\n"
                + "____/ \\___/ ____| _/  _\\ _|\\_| _/  _\\\n";

        System.out.print(System.lineSeparator());
        System.out.println(logo);
        System.out.println("Hi, I'm Solana");
        System.out.println("What can I do for you?" + System.lineSeparator());
    }

    /**
     * Reads user's input.
     *
     * @return User's input.
     */
    public String readInput() {
        System.out.print(PROMPT);
        String input = in.nextLine();
        return input;
    }
}
