package duke;
import java.util.Scanner;
import java.io.InputStream;

/**
 * @author TaiAnn
 */


public class Ui {
    private static final String LINESEPERATOR = "_________________________________________________\n";
    private final Scanner input;
    private String output;

    public Ui() {
        this(System.in);
    }


    public Ui(InputStream input) {
        this.input = new Scanner(input);
    }

    private static boolean ignoreSpace(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }

    /**
     * Handles the main UI of the application
     */
    public static void showWelcome() {

        System.out.println(LINESEPERATOR +
                "\tHello! I'm Duke.\n"  +
                "\tWhat can I do for you?\n" +
                "\tHere are the list of menu: \n" +
                "\t 1. todo ... \n" +
                "\t 2. deadline ... by ... \n" +
                "\t 3. event ... at ... \n" +
                "\t 4. list = show the entire list of tasks! \n" +
                "\t 5. mark ...(number) \n" +
                "\t 6. unmark ...(number) \n" +
                "\t 7. delete ...(number) \n" +
                "\t 8. find ... \n" +

                LINESEPERATOR);
    }

    public void showGoodbyeMessage() {
        output = LINESEPERATOR + "Bye. Hope to see you again soon.\n" + LINESEPERATOR;
    }


    /**
     * Reads in a user input and returns it
     * @return
     */
    public String readCommand() {
        System.out.println("Please choose a command: " );
        String user_input = input.nextLine();
        return user_input;
    }

    public String showLoadingError(String errorMessage) {
        return LINESEPERATOR + ":( OOPS!!! " + errorMessage + "\n" + LINESEPERATOR;
    }
}

