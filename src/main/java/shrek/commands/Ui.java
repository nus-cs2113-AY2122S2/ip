package shrek.commands;

import shrek.constant.PrintStrings;
import shrek.storage.SaveToOutput;
import shrek.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles printing of greeting message, goodbye message, list and reading of user input.
 */
public class Ui {
    private static final String LINE = PrintStrings.LINE;
    public static final String NEW_LINE = System.lineSeparator();
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    /**
     * Prints the list provided and its relevant message.
     *
     * @param listToPrint    List to be printed.
     * @param messageToPrint Message asscoiated to the list.
     */
    public static void printList(ArrayList<Task> listToPrint, String messageToPrint) {
        System.out.println(messageToPrint);
        int indexOfList = 1;
        for (Task i : listToPrint) {
            System.out.println(indexOfList + ". " + i);
            indexOfList++;
        }
    }

    /**
     * Prints the exit message.
     */
    public static void printGoodbye() {
        String bye = " Bye bye! See you later!";
        System.out.print(LINE + bye + NEW_LINE + LINE);
    }

    /**
     * Prints the initial message.
     */
    public static void printGreeting() {
        String greet = " Oh! Hello there! I'm Shrek" + NEW_LINE + " What can I do for you?";
        System.out.print(LINE);
        System.out.println(ANSI_GREEN + PrintStrings.logo + NEW_LINE + ANSI_RESET);
        System.out.println(LINE + greet);
    }

    /**
     * Reads in user command from input.
     * Exits when "bye" and saves list before exiting.
     */
    public static void readCommand() {
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        while (!userInput.equals("bye")) {
            Parser.handleInput(userInput, true);
            userInput = in.nextLine();
        }
        SaveToOutput.saveData();
    }
}
