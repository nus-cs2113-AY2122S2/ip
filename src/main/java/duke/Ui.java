package duke;

import java.util.Scanner;

/**
 * Create a UI for displaying message
 */
public class Ui {

    public static void greeting() {
        String greeting = "----------------------------------------------\n"
                + "Hello! Duke here :)\n"
                + "What can I do for you?\n"
                + "----------------------------------------------\n";
        System.out.println(greeting);
    }

    public static void farewell() {
        String bye = "Bye. Hope to see you again soon!\n";
        System.out.println(bye);
    }

    public static String getInput() {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        return userInput;
    }

    public static void printDeadlineErrorMsg() {
        System.out.println("Invalid input format. Please use /by for deadline.");
    }

    public static void printEventErrorMsg() {
        System.out.println("Invalid input format. Please use /at for event.");
    }

    public static void printCommandErrorMsg() {
        System.out.println("I don't understand D: Please key in your command again!");
    }

    public static void printIndexOutOfBoundMsg() {
        System.out.println("This index is not within the range :( Try again!");
    }

    public static void printKeyWordMsg() {
        System.out.println("The keyword doesn't exist in task descriptions, try other keyword please :)");
    }

    public static void printNumberFormatMsg() {
        System.out.println("You didn't input a valid number, please try again!");
    }

    public static void printDescriptionMissingMsg() {
        System.out.println("Please input some description for this Task!");
    }
}
