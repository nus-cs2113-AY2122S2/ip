package util.DukeClasses;

import util.miscellaneous.Chatbot;
import util.task.Task;

import java.util.ArrayList;

/**
 * Includes Duke outputs
 */
public class DukePrinter implements Chatbot {
    /**
     * Print a straight solid line
     */
    public static void linePrinter() {
        System.out.print("\t");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Print the greeting message
     */
    public static void printGreeting() {
        System.out.println("Hello from\n" + LOGO);

        linePrinter();
        System.out.println("\t" + GREETING_MSG_01);
        System.out.println("\t" + GREETING_MSG_02);
        linePrinter();

    }

    /**
     * Print the list of tasks that user adds
     *
     * @param tasks The tasks that user adds
     */
    public static void printList(ArrayList<Task> tasks) {
        linePrinter();

        if (tasks.isEmpty()) {
            System.out.println("\t" + NO_TASK_FOUND_MSG);
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("\t" + " " + Integer.toString(i + 1) + " " + (tasks.get(i)).toString());
            }
        }

        linePrinter();
    }

    /**
     * Print the delete message
     *
     * @param tasks The tasks that user adds
     * @param index The index of the deleted task
     */
    public static void printDelete(ArrayList<Task> tasks, int index) {
        linePrinter();
        System.out.println("\t" + DELETE_MSG);
        System.out.println("\t" + "   " + (tasks.get(index)).toString());
        linePrinter();
    }

    /**
     * Print the mark message
     *
     * @param tasks The tasks that user adds
     * @param markedItem The index of the marked task
     */
    public static void printMark(ArrayList<Task> tasks, int markedItem) {
        linePrinter();
        System.out.println("\t" + MARKED_MSG);
        System.out.println("\t" + "   " + (tasks.get(markedItem)).toString());
        linePrinter();

    }

    /**
     * Print the unmark message
     *
     * @param tasks The tasks that user adds
     * @param unmarkedItem The index of the unmarked task
     */
    public static void printUnmark(ArrayList<Task> tasks, int unmarkedItem) {
        linePrinter();
        System.out.println("\t" + UNMARKED_MSG);
        System.out.println("\t" + "   " + (tasks.get(unmarkedItem)).toString());
        linePrinter();

    }

    /**
     * Print the save message
     */
    public static void printSave() {
        linePrinter();
        System.out.println("\t" + SAVE_MSG);
        linePrinter();
    }

    /**
     * Print the exit message
     */
    public static void exitLine() {
        linePrinter();
        System.out.println("\t" + GOODBYE_MSG);
        linePrinter();
    }

    /**
     * Print the string given
     * @param line The string given
     */
    public static void echo (String line) {
        linePrinter();
        System.out.println("\t" + " " + line);
        linePrinter();
    }
}

