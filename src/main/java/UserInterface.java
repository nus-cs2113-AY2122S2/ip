import java.util.Scanner;

public class UserInterface {
    private Scanner uiScan;

    /**
     * Constructor.
     * @param mainScan a Scanner object that reads commands from the user.
     */
    public UserInterface(Scanner mainScan) {
        this.uiScan = mainScan;
    }

    /**
     * Starts the UserInterface.
     * Greets the user and begins the command input loop.
     */
    public void start() {
        startup();
        commandLoop();
    }

    /**
     * Reads commands from stdin and executes them using commandExec(commandInput)
     * until the user inputs "bye", upon which the function returns.
     */
    public void commandLoop() {
        String commandInput;
        do {
            commandInput = uiScan.nextLine();
            commandExec(commandInput);
        } while (!commandInput.equals("bye"));
    }

    /**
     * Checks the String inputted by the user and executes the appropriate command
     * using a switch statement.
     * @param nextLine a String containing the command inputted by the user.
     */
    private void commandExec(String nextLine) {
        if (nextLine == null) {
            System.out.println("NULL command!");
            return;
        }
        switch (nextLine) {
            case "bye":
                exit();
                break;
            default:
                echo(nextLine);
        }
    }

    /**
     * Prints the command inputted by the user to stdout.
     * @param commandInput a String containing the command inputted by the user.
     */
    private void echo(String commandInput) {
        dividerPrinter();
        System.out.println(commandInput);
        dividerPrinter();
    }

    /**
     * Prints a goodbye message and returns.
     * Does not actually terminate the program.
     */
    private void exit() {
        dividerPrinter();
        System.out.println("Goodbye. Hope to see you again soon!");
        dividerPrinter();
    }

    /**
     * Prints 37 underscores to stdout to serve as a divider.
     */
    private void dividerPrinter() {
        System.out.println("_____________________________________");
    }

    /**
     * Prints a greeting with divider lines.
     */
    private void startup() {
        dividerPrinter();
        System.out.println("Hello! I'm Michel.");
        System.out.println("What can I do for you?");
        dividerPrinter();
    }
}
