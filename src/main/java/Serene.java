import java.util.Scanner;

public class Serene {
    public static String logo = " #####  ####### ######  ####### #     # ####### \n"
            + "#     # #       #     # #       ##    # #       \n"
            + "#       #       #     # #       # #   # #       \n"
            + " #####  #####   ######  #####   #  #  # #####   \n"
            + "      # #       #   #   #       #   # # #       \n"
            + "#     # #       #    #  #       #    ## #       \n"
            + " #####  ####### #     # ####### #     # ####### ";
    public static String greetLine = "Hello~ I'm Serene\nWhat can I do for you?";
    public static String partitionLine = "____________________________________________________________";
    public static String exitLine = "Till next time. Hope to see you again soon~";
    public static final int TASK_LIMIT = 100;
    private static final int DONE = -1;
    private static final int CONTINUE = -2;
    private static Task[] taskList = new Task[TASK_LIMIT];
    private static int taskCount = 0;



    public static void printWithPartition(String input) {
        System.out.println(partitionLine);
        System.out.println(input);
        System.out.println(partitionLine);
    }

    public static String getTaskWithStatus (int taskIndex) {
        return "[" + taskList[taskIndex].getStatusIcon() + "] " + taskList[taskIndex].getDescription();
    }

    public static void printResponseList() {
        System.out.println(partitionLine);
        System.out.println("Here is your task list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(i+1 + "." + getTaskWithStatus(i));
        }
        System.out.println(partitionLine);
    }

    public static int parseInput(String userInput) {
        String[] responsePartition = userInput.split(" ");
        String keyword = responsePartition[0];
        int taskIndex = 0;
        switch(keyword) {
        case "bye":
            return DONE;
        case "list":
            printResponseList();
            break;
        case "mark":
            taskIndex = Integer.parseInt(responsePartition[1]) - 1;
            taskList[taskIndex].markDone();
            printWithPartition("Good job~ This task is now done:\n" + getTaskWithStatus(taskIndex));
            break;
        case "unmark":
            taskIndex = Integer.parseInt(responsePartition[1]) - 1;
            taskList[taskIndex].markNotDone();
            printWithPartition("Sigh. Here we go again:\n" + getTaskWithStatus(taskIndex));
            break;
        default:
            Task t = new Task(userInput);
            taskList[taskCount] = t;
            printWithPartition("added: " + userInput);
            taskCount++;
        }
        return CONTINUE;
    }

    public static void printWelcomeMessage() {
        System.out.println(partitionLine);
        System.out.println("Booting up\n" + logo);
        printWithPartition(greetLine);
    }

    public static void main(String[] args) {
        int statusOfSerene = CONTINUE;
        String[] taskList = new String[TASK_LIMIT];
        int taskCount = 0;
        Scanner in = new Scanner(System.in);
        printWelcomeMessage();
        while (statusOfSerene != DONE) {
            String userInput = in.nextLine();
            statusOfSerene = parseInput(userInput);
        }
        printWithPartition(exitLine);
    }
}
