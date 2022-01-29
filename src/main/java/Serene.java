import java.util.Scanner;

public class Serene {
    public static final int TASK_LIMIT = 100;
    private static final String PARTITION_LINE = "____________________________________________________________";
    private static final String ERROR_MESSAGE = "Oh? I ran into an oopsie~";
    private static final int DONE = -1;
    private static final int CONTINUE = -2;
    private static final int KEYWORD_INDEX = 0;
    private static final int TASK_INDEX = 1;
    private static final int DESCRIPTION_INDEX = 0;
    private static final int OPTIONS_INDEX = 1;
    private static Task[] taskList = new Task[TASK_LIMIT];
    private static int taskCount = 0;
    private static int statusOfSerene = CONTINUE;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        printWelcomeMessage();
        operateSerene(in);
        printExitMessage();
    }

    private static void operateSerene(Scanner in) {
        while (statusOfSerene != DONE) {
            String userInput = in.nextLine();
            statusOfSerene = parseInput(userInput);
        }
    }

    private static void printExitMessage() {
        String exitLine = "Till next time. Hope to see you again soon~";
        printWithPartition(exitLine);
    }

    private static void printWithPartition(String input) {
        System.out.println(PARTITION_LINE);
        System.out.println(input);
        System.out.println(PARTITION_LINE);
    }

    private static void printResponseList() {
        System.out.println(PARTITION_LINE);
        System.out.println("Here is your task list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(i+1 + "." + taskList[i]);
        }
        System.out.println(PARTITION_LINE);
    }

    private static int parseInput(String userInput) {
        String[] responsePartition = userInput.split(" ", 2);
        String keyword = responsePartition[KEYWORD_INDEX];
        int taskIndex = 0;
        switch(keyword) {
        case "bye":
            return DONE;
        case "list":
            printResponseList();
            break;
        case "mark":
            markTaskDone(responsePartition);
            break;
        case "unmark":
            markTaskNotDone(responsePartition);
            break;
        case "todo":
            addToDo(responsePartition[TASK_INDEX]);
            break;
        case "event":
            addEvent(responsePartition[TASK_INDEX]);
            break;
        case "deadline":
            addDeadline(responsePartition[TASK_INDEX]);
            break;
        default:
            System.out.println(ERROR_MESSAGE);
            return DONE;
        }
        return CONTINUE;
    }

    private static void markTaskNotDone(String[] responsePartition) {
        int taskIndex;
        taskIndex = Integer.parseInt(responsePartition[1]) - 1;
        taskList[taskIndex].markNotDone();
        printWithPartition("Sigh. Here we go again:\n" + taskList[taskIndex]);
    }

    private static void markTaskDone(String[] responsePartition) {
        int taskIndex;
        taskIndex = Integer.parseInt(responsePartition[1]) - 1;
        taskList[taskIndex].markDone();
        printWithPartition("Good job~ This task is now done:\n" + taskList[taskIndex]);
    }

    private static void addToDo(String userInput) {
        ToDo task = new ToDo(userInput);
        allocateTask(task);
    }

    private static void addEvent(String userInput) {
        String[] taskPartition = userInput.split(" /at ");
        Event task = new Event(taskPartition[DESCRIPTION_INDEX], taskPartition[OPTIONS_INDEX]);
        allocateTask(task);
    }

    private static void addDeadline(String userInput) {
        String[] taskPartition = userInput.split(" /by ");
        Deadline task = new Deadline(taskPartition[DESCRIPTION_INDEX], taskPartition[OPTIONS_INDEX]);
        allocateTask(task);
    }

    private static void allocateTask(Task task) {
        taskList[taskCount] = task;
        printAddedTask();
        taskCount++;
    }

    private static void printAddedTask() {
        String toPrint = "Okay, I've added this for you:" + System.lineSeparator() +
                taskList[taskCount] + System.lineSeparator() +
                "Now you have " + (taskCount+1) + " tasks in the list.";
        printWithPartition(toPrint);
    }

    private static void printWelcomeMessage() {
        String logo = " #####  ####### ######  ####### #     # ####### \n"
                + "#     # #       #     # #       ##    # #       \n"
                + "#       #       #     # #       # #   # #       \n"
                + " #####  #####   ######  #####   #  #  # #####   \n"
                + "      # #       #   #   #       #   # # #       \n"
                + "#     # #       #    #  #       #    ## #       \n"
                + " #####  ####### #     # ####### #     # ####### ";
        String greetLine = "Hello~ I'm Serene\nWhat can I do for you?";
        System.out.println(PARTITION_LINE);
        System.out.println("Booting up" + System.lineSeparator() + logo);
        printWithPartition(greetLine);
    }

}
