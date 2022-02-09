package Duke.Helper;

import Duke.Duke;

public class DisplayMessages {

    public static void displayMarkMessage(int itemNumber) {
        String checkGrammer = "items";
        if (itemNumber == 1) {
            checkGrammer = "item";
        }
        System.out.print(Duke.DISPLAY_LINE + "There are " + itemNumber + " " + checkGrammer + " in the list\n"
                + "Please input which item you would like to mark.\n"
                + "For eg. 'mark 2' \n"+ Duke.DISPLAY_LINE);
    }

    public static void displayUnmarkMessage(int itemNumber) {
        String checkGrammer = "items";
        if (itemNumber == 1) {
            checkGrammer = "item";
        }
        System.out.print(Duke.DISPLAY_LINE + "There are " + itemNumber + " " + checkGrammer + " in the list\n"
                + "Please input which item you would like to unmark.\n"
                + "For eg. 'unmark 2' \n"+ Duke.DISPLAY_LINE);
    }

    public static void displayListMessage() {
        System.out.print(Duke.DISPLAY_LINE + "There are no items in the list, please add something!:)\n" +
                Duke.DISPLAY_LINE);
    }

    public static void displayTodoMessage() {
        System.out.print(Duke.DISPLAY_LINE + "Please input a description\n" +
                "For eg. 'todo borrow book'\n" + Duke.DISPLAY_LINE);
    }

    public static void displayDeadlineMessage() {
        System.out.print(Duke.DISPLAY_LINE + "Please input a description, '/by', date\n" +
                "For eg. 'deadline work /by Sunday'\n" + Duke.DISPLAY_LINE);
    }

    public static void displayEventMessage() {
        System.out.print(Duke.DISPLAY_LINE + "Please input a description, '/at', date\n" +
                "For eg. 'event meeting /at Mon 2-4pm'\n" + Duke.DISPLAY_LINE);
    }

    public static void displayInvalidInputMessage() {
        System.out.println("Please input a valid command:)\n" + Duke.DISPLAY_LINE );
    }

    public static void displayGreetMessage () {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(Duke.ANSI_BLUE + Duke.DISPLAY_LINE + logo);
        System.out.println("Hello! Duke.Duke here!:)");
        System.out.print("Is there anything I can do for you?\n" + Duke.DISPLAY_LINE + Duke.ANSI_RESET);
    }
}
