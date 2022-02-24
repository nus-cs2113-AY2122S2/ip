package serene.global;

import serene.operation.Parser;
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
    public static final String EMPTY_TIME_ERROR_MESSAGE = "Empty time? I'll make you run out of time.";
    public static final String EMPTY_REGEX_ERROR_MESSAGE = "Uhh... What are you trying to look for?";
    public static final String NOTHING_FOUND = "No such task .-.";
    public static final String IO_FAIL_MESSAGE = "I/O failed ;-;";

    /**
     * Prints a welcome message to the user upon start up of Serene.
     */
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

    /**
     * Prints the input encapsulated in a pair of partitioning lines.
     *
     * @param input The string to be printed.
     */
    public static void printWithPartition(String input) {
        System.out.println(PARTITION_LINE);
        System.out.println(input);
        System.out.println(PARTITION_LINE);
    }

    /**
     * Prints the tasks present in the input task list.
     * They are printed with numbering, type of task and whether it is marked.
     *
     * @param tasks The task list containing the user's tasks
     */
    public static void printTaskList(TaskList tasks) {
        ArrayList<Task> taskList = tasks.getTaskList();
        System.out.println(PARTITION_LINE);
        System.out.println("Here is your task list:");
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i));
        }
        System.out.println(PARTITION_LINE);
    }

    /**
     * Prints the newly added task, as well as how many tasks are present now present in the list.
     *
     * @param inputTask The newly added task
     * @param taskCount Counter of number of tasks present
     */
    public static void printAddedTask(Task inputTask, int taskCount) {
        String toPrint;
        if (taskCount == Constant.SINGULAR) {
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

    /**
     * Prints tasks which contains what the user is searching for in the description.
     *
     * @param taskList The array of available tasks
     * @param userInput The user's input
     */
    public static void printFoundTasks(ArrayList<Task> taskList, String[] userInput) {
        try {
            String toFind = userInput[Constant.RESPONSE_INDEX_BODY];
            System.out.println(PARTITION_LINE);
            System.out.println("Here are the tasks you are looking for:");
            int counter = Constant.INITIAL_COUNTER;
            for (Task task : taskList) {
                if (Parser.isOfInterest(task, toFind)) {
                    System.out.println((counter) + "." + task);
                    counter++;
                }
            }
            if (counter == Constant.INITIAL_COUNTER) {
                System.out.println(NOTHING_FOUND);
            }
            System.out.println(PARTITION_LINE);
        } catch (ArrayIndexOutOfBoundsException e) {
            printWithPartition(EMPTY_REGEX_ERROR_MESSAGE);
        }
    }

    /**
     * Prints an exit message upon user input of the bye command.
     */
    public static void printExitMessage() {
        printWithPartition("Till next time. Hope to see you again soon~");
    }
}
