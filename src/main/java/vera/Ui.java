package vera;

import java.util.Scanner;

import static vera.constant.Messages.GOODBYE_MESSAGE;
import static vera.constant.Messages.WELCOME_MESSAGE;


public class Ui {
    private final static Scanner in = new Scanner(System.in);
    private static final String PARTITION_LINE = "______________________________"
            + "______________________________";
    private static final String LS = System.lineSeparator();
    private static final String LOGO = " __     __             \n"
            + " \\ \\   / /__ _ __ __ _ \n"
            + "  \\ \\ / / _ \\ '__/ _` |\n"
            + "   \\ V /  __/ | | (_| |\n"
            + "    \\_/ \\___|_|  \\__,_|\n";


    /**
     * Returns any user input entered by the user through
     * the program's terminal. User Input is also trimmed to
     * remove white spaces.
     *
     * @return User input
     */
    public String readCommand() {
        return in.nextLine().trim();
    }

    /**
     * Prints a partition line.
     */
    public void showLine() {
        System.out.println(PARTITION_LINE);
    }

    /**
     * Prints a message from the program to the user.
     * Can be in the form of exceptions that have occurred,
     * requests for inputs, command acknowledgement and so on.
     *
     * @param message Message to show to the user.
     */
    public void showToUser(String... message) {
        for (String s : message) {
            System.out.println(s);
        }
    }

    /**
     * Prints a welcome message to greet the user.
     */
    public void showWelcomeMessage() {
        String message = PARTITION_LINE + LS + LOGO +
                WELCOME_MESSAGE + LS + PARTITION_LINE;
        showToUser(message);
    }

    /**
     * Prints a goodbye message to the user.
     */
    public void showGoodbyeMessage() {
        System.out.println(GOODBYE_MESSAGE);
    }
}
