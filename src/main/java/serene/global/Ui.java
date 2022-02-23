package serene.global;

import serene.operation.TaskList;
import serene.task.Task;

import java.util.ArrayList;

public class Ui {
    public static final String PARTITION_LINE = "____________________________________________________________";
    public static final String INPUT_ERROR_MESSAGE = "Uhm... I'm afraid I don't know what you mean by that :/";
    public static final String INVALID_NUM_ERROR_MESSAGE = "Please enter a valid task number ;-;";
    public static final String EMPTY_DESC_ERROR_MESSAGE = "Hey! Don't try to make me record nothing for fun :<";
    public static final String EMPTY_BY_ERROR_MESSAGE = "No time input? Please remember your /by~";
    public static final String EMPTY_AT_ERROR_MESSAGE = "No time input? Please remember your /at~";
    public static final String IO_FAIL_MESSAGE = "I/O failed ;-;";

    public static void printWelcomeMessage() {
        String logo = " #####  ####### ######  ####### #     # ####### \n"
                + "#     # #       #     # #       ##    # #       \n"
                + "#       #       #     # #       # #   # #       \n"
                + " #####  #####   ######  #####   #  #  # #####   \n"
                + "      # #       #   #   #       #   # # #       \n"
                + "#     # #       #    #  #       #    ## #       \n"
                + " #####  ####### #     # ####### #     # ####### ";
        String greetLine = "Hello~ I'm Serene" + System.lineSeparator() + "What can I do for you?";
        System.out.println(PARTITION_LINE);
        System.out.println("Booting up");
        System.out.println(logo);
        printWithPartition(greetLine);
    }

    public static void printWithPartition(String input) {
        System.out.println(PARTITION_LINE);
        System.out.println(input);
        System.out.println(PARTITION_LINE);
    }

    public static void printTaskList(TaskList tasks) {
        ArrayList<Task> taskList = tasks.getTaskList();
        System.out.println(PARTITION_LINE);
        System.out.println("Here is your task list:");
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i));
        }
        System.out.println(PARTITION_LINE);
    }

    public static void printAddedTask(Task inputTask, int taskCount) {
        String toPrint;
        if (taskCount == 1) {
            toPrint = "Okay, I've added this for you:" + System.lineSeparator() +
                    inputTask + System.lineSeparator() +
                    "Now you have " + taskCount + " task in the list.";
        }
        else {
            toPrint = "Okay, I've added this for you:" + System.lineSeparator() +
                    inputTask + System.lineSeparator() +
                    "Now you have " + taskCount + " tasks in the list.";
        }
        printWithPartition(toPrint);
    }

    public static void printExitMessage() {
        printWithPartition("Till next time. Hope to see you again soon~");
    }
}
