import java.util.Scanner;

/**
 * Duke is an application that allows users to keep track of their list of tasks such as Todo, Deadline and Event.
 * Users can add a new task, mark as done or not done, delete or find a task.
 */

public class Duke {

    private static void run() {
        Ui.showWelcomeMessage();
        Storage.loadData();
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        Command.executeCommand(userInput, in);
        Storage.saveData();
        Ui.showGoodByeMessage();
    }

    /** Initializes the application and starts the interaction with the user. */
    public static void main(String[] args) {
        run();
    }
}
