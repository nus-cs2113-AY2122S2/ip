import java.util.Scanner;

public class Serene {
    public static final int TASK_LIMIT = 100;
    private static final String PARTITION_LINE = "____________________________________________________________";
    private static final String ERROR_MESSAGE = "Oh? I ran into an oopsie~";
    private static final int DONE = -1;
    private static final int CONTINUE = -2;
    private static final int RESPONSE_PARTITION_INDEX_KEYWORD = 0;
    private static final int RESPONSE_PARTITION_INDEX_BODY = 1;
    private static final int TASK_PARTITION_INDEX_DESCRIPTION = 0;
    private static final int TASK_PARTITION_INDEX_OPTIONS = 1;
    private static Task[] taskList = new Task[TASK_LIMIT];
    private static int taskCount = 0;
    private static int statusOfSerene = CONTINUE;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        printWelcomeMessage();
        operateSerene(in);
        printExitMessage();
    }

    private static void printWelcomeMessage() {
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

    private static void printWithPartition(String input) {
        System.out.println(PARTITION_LINE);
        System.out.println(input);
        System.out.println(PARTITION_LINE);
    }

    private static void operateSerene(Scanner in) {
        while (statusOfSerene != DONE) {
            String userInput = in.nextLine();
            statusOfSerene = parseInput(userInput);
        }
    }

    private static int parseInput(String userInput) {
        String[] responsePartition = userInput.split(" ", 2);
        String keyword = responsePartition[RESPONSE_PARTITION_INDEX_KEYWORD];
        switch (keyword) {
        case "bye":
            return DONE;
        case "list":
            printTaskList();
            break;
        case "mark":
            markTaskDone(responsePartition);
            break;
        case "unmark":
            markTaskNotDone(responsePartition);
            break;
        case "todo":
            addToDo(responsePartition[RESPONSE_PARTITION_INDEX_BODY]);
            break;
        case "event":
            addEvent(responsePartition[RESPONSE_PARTITION_INDEX_BODY]);
            break;
        case "deadline":
            addDeadline(responsePartition[RESPONSE_PARTITION_INDEX_BODY]);
            break;
        default:
            System.out.println(ERROR_MESSAGE);
            return DONE;
        }
        return CONTINUE;
    }

    private static void printTaskList() {
        System.out.println(PARTITION_LINE);
        System.out.println("Here is your task list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + "." + taskList[i]);
        }
        System.out.println(PARTITION_LINE);
    }

    private static void markTaskDone(String[] responsePartition) {
        int taskIndex;
        taskIndex = Integer.parseInt(responsePartition[RESPONSE_PARTITION_INDEX_BODY]) - 1;
        taskList[taskIndex].markDone();
        printWithPartition("Good job~ This task is now done:" + System.lineSeparator() + taskList[taskIndex]);
    }

    private static void markTaskNotDone(String[] responsePartition) {
        int taskIndex;
        taskIndex = Integer.parseInt(responsePartition[RESPONSE_PARTITION_INDEX_BODY]) - 1;
        taskList[taskIndex].markNotDone();
        printWithPartition("Sigh. Here we go again:" + System.lineSeparator() + taskList[taskIndex]);
    }

    private static void addToDo(String userInput) {
        ToDo task = new ToDo(userInput);
        allocateTask(task);
    }

    private static void addEvent(String userInput) {
        String[] taskPartition = userInput.split(" /at ");
        Event task = new Event(taskPartition[TASK_PARTITION_INDEX_DESCRIPTION],
                taskPartition[TASK_PARTITION_INDEX_OPTIONS]);
        allocateTask(task);
    }

    private static void addDeadline(String userInput) {
        String[] taskPartition = userInput.split(" /by ");
        Deadline task = new Deadline(taskPartition[TASK_PARTITION_INDEX_DESCRIPTION],
                taskPartition[TASK_PARTITION_INDEX_OPTIONS]);
        allocateTask(task);
    }

    private static void allocateTask(Task inputTask) {
        taskList[taskCount] = inputTask;
        taskCount++;
        printAddedTask();
    }

    private static void printAddedTask() {
        String toPrint = "Okay, I've added this for you:" + System.lineSeparator() +
                taskList[taskCount - 1] + System.lineSeparator() +
                "Now you have " + taskCount + " tasks in the list.";
        printWithPartition(toPrint);
    }

    private static void printExitMessage() {
        String exitLine = "Till next time. Hope to see you again soon~";
        printWithPartition(exitLine);
    }
}