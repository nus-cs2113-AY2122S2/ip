package shrek.commands;

import shrek.constant.PrintStrings;
import shrek.task.UserContent;

import java.util.ArrayList;

public class PrintOutput {
    private static final String LINE = PrintStrings.LINE;
    public static final String NEW_LINE = System.lineSeparator();
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void printList(ArrayList<UserContent> listToPrint, String messageToPrint) {
        System.out.println(messageToPrint);
        int indexOfList = 1;
        for (UserContent i : listToPrint) {
            System.out.println(indexOfList + ". " + i);
            indexOfList++;
        }
    }

    public static void printGoodbye() {
        String bye = " Bye bye! See you later!";
        System.out.print(LINE + bye + NEW_LINE + LINE);
    }

    public static void printGreeting() {
        String greet = " Oh! Hello there! I'm Shrek" + NEW_LINE + " What can I do for you?";
        System.out.print(LINE);
        System.out.println(ANSI_GREEN + PrintStrings.logo + NEW_LINE);
        System.out.println(PrintStrings.shrekLogo + NEW_LINE + ANSI_RESET);
        System.out.println(LINE + greet);
    }
}
