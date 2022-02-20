package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a user interface that prints and read users input to and from the command line respectively.
 */
public class Ui {

    private static final String LOGO = "\n" +
            "  .-')     _ (`-.                           .-. .-')               \n" +
            " ( OO ).  ( (OO  )                          \\  ( OO )              \n" +
            "(_)---\\_)_.`     \\ .-'),-----.  .-'),-----. ,--. ,--.   ,--.   ,--.\n" +
            "/    _ |(__...--''( OO'  .-.  '( OO'  .-.  '|  .'   /    \\  `.'  / \n" +
            "\\  :` `. |  /  | |/   |  | |  |/   |  | |  ||      /,  .-')     /  \n" +
            " '..`''.)|  |_.' |\\_) |  |\\|  |\\_) |  |\\|  ||     ' _)(OO  \\   /   \n" +
            ".-._)   \\|  .___.'  \\ |  | |  |  \\ |  | |  ||  .   \\   |   /  /\\_  \n" +
            "\\       /|  |        `'  '-'  '   `'  '-'  '|  |\\   \\  `-./  /.__) \n" +
            " `-----' `--'          `-----'      `-----' `--' '--'    `--'      \n";

    private static Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * This is the showLoadingError method that prints the error message when the file to be read from does not
     * exist.
     */
    public void showLoadingError() {
        System.out.println("Duke.txt doesn't exist so I'M GOING TO CREATE ONE FOR YOU LATER");
    }

    /**
     * This is the printGreeting method that prints out greeting message and the logo of Duke.
     */
    public void printGreeting() {
        System.out.println("Hello from\n" + LOGO);
        showLine();
        System.out.println("Hi there! I'm Spooky :D");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * This is the readCommand method that reads the user input from the command line.
     *
     * @return The user input as a String.
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * This is the printGoodbye method that prints the goodbye message.
     */
    public void printGoodbye() {
        System.out.println("Bye! Hope to see you soon :D");
    }

    /**
     * This is the showLine method that prints a line.
     */
    public void showLine() {
        System.out.println("-----------------------------");
    }

    /**
     * This is the showError method that prints the error message.
     *
     * @param message The error message to be printed.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    public void showList(ArrayList<Task> listOfTasks) {
        for (int i = 0; i < listOfTasks.size(); i++) {
            int numbering = i + 1;
            System.out.println(numbering + ". " + listOfTasks.get(i));
        }
    }

    /**
     * This is the showMarkCompleted method that prints the confirmation that a task has been marked as done.
     *
     * @param taskMarked The task that is marked as done.
     */
    public void showMarkCompleted(Task taskMarked) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskMarked);
    }

    /**
     * This is the showUnmarkCompleted method that prints the confirmation that a task has been marked as NOT done.
     *
     * @param taskUnmarked The task that is marked as NOT done.
     */
    public void showUnmarkCompleted(Task taskUnmarked) {
        System.out.println("Nice! I've marked this task as NOT done:");
        System.out.println(taskUnmarked);
    }

    /**
     * This is the showDeleteDone method that prints the confirmation that a task has been deleted.
     *
     * @param taskDeleted The task that is deleted.
     * @param numberOfTasks The number of tasks left in the list of tasks.
     */
    public void showDeleteDone(Task taskDeleted, int numberOfTasks) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(taskDeleted);
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
    }

    /**
     * This is the showAddDone method that prints the confirmation that a task has been added.
     *
     * @param task The task that is added.
     * @param numberOfTasks The number of tasks in the list of tasks.
     */
    public void showAddDone(Task task, int numberOfTasks) {
        System.out.println("Got it. I have added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
    }

    /**
     * This is the showIndexError method that prints the error message when the index is out of range.
     */
    public void showIndexError() {
        System.out.println("The index is out of range, please try again.");
    }

    /**
     * This is the showFormatError method that prints the error message when the user input should be an integer
     * rather than a String.
     */
    public void showFormatError() {
        System.out.println("The index should be an integer, please try again.");
    }

    /**
     * This is the showNoResults method that prints the error message when the keyword cannot be found in any of the
     * tasks.
     */
    public void showNoResults() {
        System.out.println("I'm afraid that it is a 404 not found kinda scenario");
    }

    /**
     * This is the showDateError method that prints the error message when the date which the user input is in the
     * incorrect format.
     */
    public void showDateError() {
        System.out.println("Please input the date in the following format: yyyy-mm-dd");
    }

}
