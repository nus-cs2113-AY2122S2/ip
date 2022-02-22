package Duke.Helper;

import Duke.Duke;
import Duke.Tasks.Task;

import java.util.ArrayList;

public class DisplayMessageCommand {

    protected static final int CHECKSIZE = 1;

    public static String displayMarkMessage(ArrayList<Task> listArray) {
        String checkGrammer = "items";
        if (listArray.size() == CHECKSIZE) {
            checkGrammer = "item";
        }
        return Duke.DISPLAY_LINE + System.lineSeparator() + "There are " + listArray.size() + " "
                + checkGrammer + " in the list\n"
                + "Please input which item you would like to mark.\n"
                + "For eg. 'mark 2' \n"+ Duke.DISPLAY_LINE;
    }

    public static String displayUnmarkMessage(ArrayList<Task> listArray) {
        String checkGrammer = "items";
        if (listArray.size() == CHECKSIZE) {
            checkGrammer = "item";
        }
        return Duke.DISPLAY_LINE + System.lineSeparator() + "There are " + listArray.size() + " "
                + checkGrammer + " in the list\n"
                + "Please input which item you would like to unmark.\n"
                + "For eg. 'unmark 2' \n"+ Duke.DISPLAY_LINE;
    }

    public static String displayDeleteMessage(ArrayList<Task> listArray) {
        String checkGrammer = "items";
        if (listArray.size() == CHECKSIZE) {
            checkGrammer = "item";
        }
        return Duke.DISPLAY_LINE + System.lineSeparator()+ "There are " + listArray.size() + " "
                + checkGrammer + " in the list\n"
                + "Please input which item you would like to delete.\n"
                + "For eg. 'delete 2' \n"+ Duke.DISPLAY_LINE;
    }

    public static String displayListMessage() {
        return Duke.DISPLAY_LINE + System.lineSeparator() + "There are no items in the list, please add something!:)\n"
                + Duke.DISPLAY_LINE;
    }

    public static String displayTodoMessage() {
        return Duke.DISPLAY_LINE + System.lineSeparator() + "Please input a description\n" +
                "For eg. 'todo borrow book'\n" + Duke.DISPLAY_LINE;
    }

    public static String displayDeadlineMessage() {
        return Duke.DISPLAY_LINE + System.lineSeparator() + "Please input a description, '/by', date\n" +
                "For eg. 'deadline work /by Sunday'\n" + Duke.DISPLAY_LINE;
    }

    public static String displayEventMessage() {
        return Duke.DISPLAY_LINE + System.lineSeparator() + "Please input a description, '/at', date\n" +
                "For eg. 'event meeting /at Mon 2-4pm'\n" + Duke.DISPLAY_LINE;
    }

    public static void displayInvalidInputMessage() {
        System.out.println("Please input a valid command:)\n" + Duke.DISPLAY_LINE);
    }

    public static void displayGreetMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(Duke.ANSI_BLUE + Duke.DISPLAY_LINE + System.lineSeparator() + logo);
        System.out.println("Hello! Duke here!:)");
        System.out.print("Is there anything I can do for you?\n" + Duke.DISPLAY_LINE + System.lineSeparator()
                + Duke.ANSI_RESET);
    }
}
