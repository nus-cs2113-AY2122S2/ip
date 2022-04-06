import java.util.Scanner;

/**
 * Duke allows users to keep track of their personal list of tasks (Todo, Deadline and Events).
 * Tasks can be added, marked / unmarked as done, deleted or searched.
 * On exiting Duke, the tasks will be stored in a human-readable format (txt file).
 * The task list itself will also be stored when exiting Duke, and restored once the user runs Duke again.
 */
public class Duke {

    private static void endProgramme() {
        Storage.save();
        Ui.printBye();
    }

    /**
     * Initialise Scanner to read in user input with the help of Ui class
     */
    private static void startProgramme() {
        Scanner in = new Scanner(System.in);
        String userInput = Ui.readCommand(in);
        Parser.parseInput(userInput, in);
        endProgramme();
    }

    public static void main(String[] args) {
        Ui.printWelcome();
        Storage.load();
        startProgramme();
    }

}
