import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    public static final int MAX_TASK = 100;
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_TODO = "todo";

    private static Task[] taskItems;
    private static int itemNum;
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        welcomeMessage();
        initTaskTable();

        while (true) {
            String inCommand = SCANNER.nextLine();
            executeCommand(inCommand);
        }

    }

    private static void executeCommand(String inCommand) {
        String[] commandArr = inCommand.split(" ");
        int listNum;
        switch (commandArr[0]) {
        case COMMAND_LIST:
            listNum = 1;
            if (itemNum == 0) {
                System.out.println("List is empty");
            }
            else {
                for (int i = 0; i < itemNum; i++) {
                    System.out.println(listNum + ".[" + taskItems[i].getTaskType() + "]" + "[" + taskItems[i].getStatusIcon() + "] " + taskItems[i].description);
                    listNum++;
                }
            }
            break;
        case COMMAND_MARK:
            int markNum = Integer.parseInt(commandArr[1]) - 1;
            taskItems[markNum].isDone = true;
            System.out.println("Nice! I've marked this task as done:\n" + "[" + taskItems[markNum].taskType + "]"+ "[X] " + taskItems[markNum].description);
            break;
        case COMMAND_UNMARK:
            int unMarkNum = Integer.parseInt(commandArr[1]) - 1;
            taskItems[unMarkNum].isDone = false;
            System.out.println("Nice! I've marked this task as not done yet:\n" + "[" + taskItems[unMarkNum].taskType + "]" + "[ ] " + taskItems[unMarkNum].description);
            break;
        case COMMAND_BYE:
            exitProgram();
            break;
        default:
            addTask(inCommand);
            break;
        }
    }

    private static void exitProgram() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }

    private static void addTask(String inCommand) {
        String[] sentenceArr = inCommand.split(" ");
        String[] taskToInput;
        String printTask;
        switch (sentenceArr[0]) {
        case COMMAND_TODO:
            taskToInput = Arrays.copyOfRange(sentenceArr, 1, sentenceArr.length);
            printTask = arrayToString(taskToInput);
            taskItems[itemNum] = new Task(printTask);
            taskItems[itemNum].taskType = 'T';
            System.out.println("Got it. I've added this task:\n" + "[T][ ] " + printTask + "\nNow you have " + (itemNum + 1) +  " tasks in the list.");
            itemNum++;
            break;
        case COMMAND_DEADLINE:
            int getDateIndex = Arrays.asList(sentenceArr).indexOf("/by");
            taskToInput = Arrays.copyOfRange(sentenceArr, 1, getDateIndex);
            String[] date = Arrays.copyOfRange(sentenceArr, getDateIndex + 1, sentenceArr.length);
            String[] finalDeadlineArr = concatArray(taskToInput, date, " (by:");
            printTask = arrayToString(finalDeadlineArr);
            taskItems[itemNum] = new Task(printTask);
            taskItems[itemNum].taskType = 'D';
            System.out.println("Got it. I've added this task:\n" + "[D][ ] " + printTask + "\nNow you have " + (itemNum + 1) +  " tasks in the list.");
            itemNum++;
            break;
        case COMMAND_EVENT:
            int getTimeIndex = Arrays.asList(sentenceArr).indexOf("/at");
            taskToInput = Arrays.copyOfRange(sentenceArr, 1, getTimeIndex);
            String[] timing = Arrays.copyOfRange(sentenceArr, getTimeIndex + 1, sentenceArr.length);
            String[] finalEventArr = concatArray(taskToInput, timing, "(at:");
            printTask = arrayToString(finalEventArr);
            taskItems[itemNum] = new Task(printTask);
            taskItems[itemNum].taskType = 'D';
            System.out.println("Got it. I've added this task:\n" + "[E][ ] " + printTask + "\nNow you have " + (itemNum + 1) +  " tasks in the list.");
            itemNum++;
            break;
        default:
            System.out.println("Invalid input");
            break;
        }

    }

    private static String[] concatArray(String[] taskToInput, String[] time, String timeFormat) {
        int length = taskToInput.length + time.length + 2;
        String[] finalTask = new String[length];
        int pos = 0;
        for (String element : taskToInput) {
            finalTask[pos] = element;
            pos++;
        }
        finalTask[pos] = timeFormat;
        pos++;
        for (String element : time) {
            finalTask[pos] = element;
            pos++;
        }
        finalTask[pos] = ")";
        return finalTask;
    }

    private static String arrayToString(String[] taskToInput) {
        return Arrays.toString(taskToInput).replace(",", "").replace("[", "").replace("]", "");
    }

    private static void initTaskTable() {
        taskItems = new Task[MAX_TASK];
        itemNum = 0;
    }

    private static void welcomeMessage() {
        System.out.println("Hello! I'm Hage");
        System.out.println("What can I do for you?");
    }
}
