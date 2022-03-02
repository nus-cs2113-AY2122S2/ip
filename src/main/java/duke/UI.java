package duke;

import duke.common.Messages;
import duke.task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class UI {
    private final Scanner in;
    private final PrintStream out;

    public UI() {
        this(System.in, System.out);
    }

    public UI(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Reads the text entered by the user.
     *
     * @return command (full line) entered by the user
     */
    public String getUserCommand() {
        String input = in.nextLine();
        return input.strip();
    }

    /**
     *  Prints the intro message upon the start of the application.
     */
    public void printIntro() {
        out.println(Messages.INTRO_LOGO);
        printMessageWithBorder(Messages.WELCOME_MESSAGE);
    }

    /**
     *  Prints the outro message before the application ends.
     */
    public void printOutro() {
        out.println(Messages.OUTRO_LOGO);
        printMessageWithBorder(Messages.GOODBYE_MESSAGE);
    }

    /**
     *  Prints the message enclosed with border
     */
    public void printMessageWithBorder(String message){
        out.println(Messages.BORDER);
        out.println(message);
        out.println(Messages.BORDER);
    }

    /**
     *  Prints a border
     */
    public static void printBorder() {
        System.out.println(Messages.BORDER);
    }

    /**
     * Prints the list of task
     * The function checks if the list is empty.
     * If the list is empty it would let the user know.
     * Else the list of task is printed.
     *
     * @param taskList The TaskList object that contains list of task
     */
    public static void printTaskList(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            System.out.println("No task available!");
        }
        for (int i = 0 ; i < taskList.size(); i++) {
            System.out.println(" " +(i + 1) +"." + taskList.get(i));
        }
    }
}
