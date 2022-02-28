package util.DukeClasses;

import util.miscellaneous.Chatbot;
import util.task.Task;

import java.util.ArrayList;

public class DukePrinter implements Chatbot {
    public static void linePrinter() {
        System.out.print("\t");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printGreeting() {
        linePrinter();
        System.out.println("\t" + GREETING_MSG_01);
        System.out.println("\t" + GREETING_MSG_02);
        linePrinter();

    }

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

    public static void printDelete(ArrayList<Task> tasks, int index) {
        linePrinter();
        System.out.println("\t" + DELETE_MSG);
        System.out.println("\t" + "   " + (tasks.get(index)).toString());
        linePrinter();
    }

    public static void printMark(ArrayList<Task> tasks, int markedItem) {
        linePrinter();
        System.out.println("\t" + MARKED_MSG);
        System.out.println("\t" + "   " + (tasks.get(markedItem)).toString());
        linePrinter();

    }

    public static void printUnmark(ArrayList<Task> tasks, int unmarkedItem) {
        linePrinter();
        System.out.println("\t" + UNMARKED_MSG);
        System.out.println("\t" + "   " + (tasks.get(unmarkedItem)).toString());
        linePrinter();

    }

    public static void printSave() {
        linePrinter();
        System.out.println("\t" + SAVE_MSG);
        linePrinter();
    }


    public static void exitLine() {
        linePrinter();
        System.out.println("\t" + GOODBYE_MSG);
        linePrinter();
    }

    public static void echo (String line) {
        linePrinter();
        System.out.println("\t" + " " + line);
        linePrinter();
    }
}

