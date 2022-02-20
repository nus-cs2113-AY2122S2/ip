package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

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

    public void showLoadingError() {
        System.out.println("Duke.txt doesn't exist so I'M GOING TO CREATE ONE FOR YOU LATER");
    }

    public void printGreeting() {
        System.out.println("Hello from\n" + LOGO);
        showLine();
        System.out.println("Hi there! I'm Spooky :D");
        System.out.println("What can I do for you?");
        showLine();
    }

    public String readCommand() {
        return in.nextLine();
    }

    public void printGoodbye() {
        System.out.println("Bye! Hope to see you soon :D");
    }

    public void showLine() {
        System.out.println("-----------------------------");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showList(ArrayList<Task> listOfTasks) {
        for (int i = 0; i < listOfTasks.size(); i++) {
            int numbering = i + 1;
            System.out.println(numbering + ". " + listOfTasks.get(i));
        }
    }

    public void showMarkDone(Task taskMarked) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskMarked);
    }

    public void showUnmarkDone(Task taskUnmarked) {
        System.out.println("Nice! I've marked this task as NOT done:");
        System.out.println(taskUnmarked);
    }

    public void showDeleteDone(Task taskDeleted, int numberOfTasks) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(taskDeleted);
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
    }

    public void showAddDone(Task task, int numberOfTasks) {
        System.out.println("Got it. I have added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
    }

    public void showIndexError() {
        System.out.println("The index is out of range, please try again.");
    }

    public void showFormatError() {
        System.out.println("The index should be an integer, please try again.");
    }

    public void showNoResuts() {
        System.out.println("I'm afraid that it is a 404 not found kinda scenario");
    }
      
    public void showDateError() {
        System.out.println("Please input the date in the following format: yyyy-mm-dd");
    }
}
