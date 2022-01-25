import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Scanner uiScan;
    private ArrayList<String> tasks;

    /**
     * Constructor.
     * @param mainScan a Scanner object that reads commands from the user.
     */
    public UserInterface(Scanner mainScan) {
        this.uiScan = mainScan;
        this.tasks = new ArrayList<>();
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
            case "list":
                printTasks();
                break;
            default:
                addTask(nextLine);
        }
    }

    /**
     * Prints the command inputted by the user to stdout.
     * @param commandInput a String containing the command inputted by the user.
     */
    private void echo(String commandInput) {
        printDivider();
        System.out.println(commandInput);
        printDivider();
    }

    /**
     * Stores a string in an ArrayList.
     * @param task a String representing the task to be stored.
     */
    private void addTask(String task) {
        printDivider();
        this.tasks.add(task);
        System.out.println("added: " + task);
        printDivider();
    }

    /**
     * Prints all tasks stored in memory by addTask(String)
     */
    private void printTasks() {
        printDivider();
        for (int i = 1; i <= this.tasks.size(); i++) {
            System.out.println(i + ". " + this.tasks.get(i - 1));
        }
        printDivider();
    }

    /**
     * Prints a goodbye message and returns.
     * Does not actually terminate the program.
     */
    private void exit() {
        printDivider();
        System.out.println("Goodbye. Hope to see you again soon!");
        printDivider();
    }

    /**
     * Prints 37 underscores to stdout to serve as a divider.
     */
    private void printDivider() {
        System.out.println("_____________________________________");
    }

    /**
     * Prints a greeting with divider lines.
     */
    private void startup() {
        printDivider();
        System.out.println("Hello! I'm Michel.");
        System.out.println("What can I do for you?");
        printDivider();
    }
}
