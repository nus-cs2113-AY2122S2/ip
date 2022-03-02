package Duke.Ui;

import Duke.DukeException;
import Duke.Storage.Storage;
import Duke.Tasks.TaskList;

import java.io.IOException;
import java.util.Scanner;

public class Ui {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m";
    public static final String DISPLAY_LINE = "----------------------------------------------------";
    private static Scanner in;
    protected static final int LISTSIZE = 1;

    public Ui () {
        this.in = new Scanner(System.in);
    }

    public static String displayMarkMessage(TaskList taskList) {
        String checkGrammer = "items";
        if (taskList.size() == LISTSIZE) {
            checkGrammer = "item";
        }
        return DISPLAY_LINE + System.lineSeparator() + "There are " + taskList.size() + " "
                + checkGrammer + " in the list\n"
                + "Please input which item you would like to mark.\n"
                + "For eg. 'mark 2' \n"+ DISPLAY_LINE;
    }

    public static String displayUnmarkMessage(TaskList taskList) {
        String checkGrammer = "items";
        if (taskList.size() == LISTSIZE) {
            checkGrammer = "item";
        }
        return DISPLAY_LINE + System.lineSeparator() + "There are " + taskList.size() + " "
                + checkGrammer + " in the list\n"
                + "Please input which item you would like to unmark.\n"
                + "For eg. 'unmark 2' \n"+ DISPLAY_LINE;
    }

    public static String displayDeleteMessage(TaskList taskList) {
        String checkGrammer = "items";
        if (taskList.size() == LISTSIZE) {
            checkGrammer = "item";
        }
        return DISPLAY_LINE + System.lineSeparator()+ "There are " + taskList.size() + " "
                + checkGrammer + " in the list\n"
                + "Please input which item you would like to delete.\n"
                + "For eg. 'delete 2' \n"+ DISPLAY_LINE;
    }

    public static String displayListMessage() {
        return DISPLAY_LINE + System.lineSeparator() + "There are no items in the list, please add something!:)\n"
                + DISPLAY_LINE;
    }

    public static String displayTodoMessage() {
        return DISPLAY_LINE + System.lineSeparator() + "Please input a description\n" +
                "For eg. 'todo borrow book'\n" + DISPLAY_LINE;
    }

    public static String displayDeadlineMessage() {
        return DISPLAY_LINE + System.lineSeparator() + "Please input a description, '/by', date\n" +
                "For eg. 'deadline work /by Sunday'\n" + DISPLAY_LINE;
    }

    public static String displayEventMessage() {
        return DISPLAY_LINE + System.lineSeparator() + "Please input a description, '/at', date\n" +
                "For eg. 'event meeting /at Mon 2-4pm'\n" + DISPLAY_LINE;
    }

    public static String displayInvalidInputMessage() {
        return ("Please input a valid command:)\n" + DISPLAY_LINE);
    }

    public static String displayInvalidLoadmessage() {
        return("There was an error in loading the file" + DISPLAY_LINE);
    }

    public void displayGreetMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(ANSI_BLUE + DISPLAY_LINE + System.lineSeparator() + logo);
        System.out.println("Hello! Duke here!:)");
        System.out.print("Is there anything I can do for you?\n" + DISPLAY_LINE + System.lineSeparator()
                + ANSI_RESET);
    }

    public static void listItems (TaskList taskList) throws DukeException {
        if (taskList.size() == 0) {
            throw new DukeException(Ui.displayListMessage());
        } else {
            System.out.println(DISPLAY_LINE  + System.lineSeparator() + "Here are the tasks in your list: ");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.print(i + 1 + ". ");
                System.out.println(taskList.get(i));
            }
            System.out.println(DISPLAY_LINE);
        }
    }

    public static void exit(Storage storage, TaskList taskList) {
        try {
            storage.saveFile(taskList);
            System.out.println("Bye. Hope to see you again soon!");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }

    public String readCommand() {
        return in.nextLine();
    }

}
