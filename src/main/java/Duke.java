import java.util.Scanner;

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

        printWelcomeMessage();
        initTaskTable();
        while (true) {
            getNewInput();
        }
    }

    private static void getNewInput() {
        String inputCommand = SCANNER.nextLine();
        executeCommand(inputCommand);
    }

    private static void executeCommand(String inputCommand) {
        String[] commandArr = inputCommand.split(" ");
        int listNum;
        switch (commandArr[0]) {
        case COMMAND_LIST:
            listNum = 1;
            if (itemNum == 0) {
                System.out.println("List is empty");
            }
            else {
                for (int i = 0; i < itemNum; i++) {
                    System.out.println(listNum + ". " + taskItems[i]);
                    listNum++;
                }
            }
            break;
        case COMMAND_MARK:
            int markNum = Integer.parseInt(commandArr[1]) - 1;
            taskItems[markNum].isDone = true;
            System.out.println("Nice! I've marked this task as done:\n" + taskItems[markNum]);
            break;
        case COMMAND_UNMARK:
            int unMarkNum = Integer.parseInt(commandArr[1]) - 1;
            taskItems[unMarkNum].isDone = false;
            System.out.println("Nice! I've marked this task as not done yet:\n" + taskItems[unMarkNum]);
            break;
        case COMMAND_BYE:
            exitProgram();
            break;
        default:
            addTask(inputCommand);
            break;
        }
    }

    private static void exitProgram() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }

    private static void addTask(String inCommand) {
        String[] sentenceArr = inCommand.split(" ", 2);
        switch (sentenceArr[0]) {
        case COMMAND_TODO:
            taskItems[itemNum] = new ToDo(sentenceArr[1]);
            printAcknowledgeAddMessage();
            itemNum++;
            break;
        case COMMAND_DEADLINE:
            String[] date = sentenceArr[1].split("/by", 2);
            taskItems[itemNum] = new Deadline(date[0], date[1]);
            printAcknowledgeAddMessage();
            itemNum++;
            break;
        case COMMAND_EVENT:
            String[] finalEventArr = sentenceArr[1].split("/at", 2);
            taskItems[itemNum] = new Event(finalEventArr[0], finalEventArr[1]);
            printAcknowledgeAddMessage();
            itemNum++;
            break;
        default:
            System.out.println("Invalid input");
            break;
        }
    }

    private static void printAcknowledgeAddMessage() {
        System.out.println("Got it. I've added this task:\n" + taskItems[itemNum]);
        System.out.println("Now you have " + (itemNum + 1) +  " tasks in the list.");
    }

    private static void initTaskTable() {
        taskItems = new Task[MAX_TASK];
        itemNum = 0;
    }

    private static void printWelcomeMessage() {
        System.out.println("Hello! I'm Hage");
        System.out.println("What can I do for you?");
    }
}
