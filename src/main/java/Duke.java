import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {
    // String assets for Dukebot GUI
    final static String BORDER = "__________________________________________________";
    final static String INTRO_LOGO = "   ___             _               ___             _     \n" +
            "  |   \\   _  _    | |__    ___    | _ )    ___    | |_   \n" +
            "  | |) | | +| |   | / /   / -_)   | _ \\   / _ \\   |  _|  \n" +
            "  |___/   \\_,_|   |_\\_\\   \\___|   |___/   \\___/   _\\__|  \n" +
            "_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| \n" +
            "\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-' ";
    final static String OUTRO_LOGO = "                               __\n" +
            "                            _ / /\n" +
            "  __ ___      ____      __ (_) | \n" +
            " / _` \\ \\ /\\ / /\\ \\ /\\ / /   | | \n" +
            "| (_| |\\ V  V /  \\ V  V /   _| | \n" +
            " \\__,_| \\_/\\_/    \\_/\\_/   (_) | \n" +
            "                              \\_\\";
    // moved as static variable to resolve NoSuchElementException for piping input file
    static Scanner sc = new Scanner(System.in);

    // Global variable for list of task
    static List<Task> taskList = new ArrayList<>();
    static int numOfTask = 0;

    public static void printIntro() {
        System.out.println(INTRO_LOGO);
        printBorder();
        System.out.println(" Good Morning sir, I am DukeBot, your personal assistant! " +
                            "\n What can I do for you today?");
        printBorder();
    }

    public static void printOutro() {
        System.out.println(OUTRO_LOGO);
        printBorder();
        System.out.println(" I guess it's time to say good bye... " +
                            "\n Please come again soon!");
        printBorder();
    }

    public static void printBorder(){
        System.out.println(BORDER);
    }

    public static String getCommand(String input){
        return input.split(" ")[0];
    }

    public static void printTaskList() {
        int taskCount = 1;
        for (Task task : taskList){
            System.out.print(" "+taskCount +".");
            System.out.println(task);
            taskCount++;
        }
    }

    public static String getDeadlineTaskDescription(String input) {
        int firstSpaceIndex = input.indexOf(" ");
        int slashIndex = input.indexOf("/by");
        // +1 to exclude " " and -1 to exclude "/"
        String description = input.substring(firstSpaceIndex + 1, slashIndex - 1);
        return description.trim();
    }
    public static String getEventTaskDescription(String input) {
        int firstSpaceIndex = input.indexOf(" ");
        int slashIndex = input.indexOf("/at");
        // +1 to exclude " " and -1 to exclude "/"
        String description = input.substring(firstSpaceIndex + 1, slashIndex - 1);
        return description.trim();
    }

    public static String getToDoTaskDescription(String input) {
        int firstSpaceIndex = input.indexOf(" ");
        // +1 to exclude " "
        String description = input.substring(firstSpaceIndex + 1);
        return description.trim();
    }

    public static String getEventDateTime(String input){
        int slashIndex = input.indexOf("/at");
        // +3 to exclude "/at"
        String dateTime = input.substring(slashIndex + 3);
        return dateTime.trim();
    }

    public static String getDeadlineDate(String input){
        int slashIndex = input.indexOf("/by");
        // +3 to exclude "/by"
        String date = input.substring(slashIndex + 3);
        return date.trim();
    }

    public static void printTaskUpdate(Task newTask) {
        System.out.println("Got it!. I've added this task: ");
        System.out.println(newTask);
        System.out.println("Now you have " +numOfTask +" tasks in the list.");
    }

    public static void addTaskToTaskList(String input, String type) {
        Task newTask;
        switch (type){
        case "deadline":
            String deadlineDescription = getDeadlineTaskDescription(input);
            String dueDateTime = getDeadlineDate(input);
            newTask = new Deadline(deadlineDescription, dueDateTime);
            break;
        case "event":
            String eventDescription = getEventTaskDescription(input);
            String dueDate = getEventDateTime(input);
            newTask = new Event(eventDescription, dueDate);
            break;
        default:
            String todoDescription = getToDoTaskDescription(input);
            newTask = new Todo(todoDescription);
            break;
        }
        taskList.add(newTask);
        numOfTask++;
        printTaskUpdate(newTask);
    }

    public static Task getTask(int taskNumber) {
        // -1 is to offset the counting of array list from 0
        return taskList.get(taskNumber - 1);
    }

    public static int getTaskNumber(String input){
        return Integer.parseInt(input.split(" ")[1]);
    }

    public static void markTask(String input, boolean doneStatus) {
        int taskNum = getTaskNumber(input);
        Task markedTask = getTask(taskNum);
        markedTask.setDone(doneStatus);

        if (doneStatus){
            System.out.println("Nice! I'v marked this task as done:");
        }else{
            System.out.println("Okay! I'v marked this task as not done:");
        }
        System.out.println(markedTask);
    }

    public static void main(String[] args) {
        printIntro();
        while (true){
            String input = sc.nextLine();
            String command = getCommand(input);
            switch (command) {
            case "bye":
                printOutro();
                System.exit(0);
                break;
            case "mark":
                markTask(input, true);
                break;
            case "unmark":
                markTask(input, false);
                break;
            case "list":
                printTaskList();
                break;
            default:
                printBorder();
                addTaskToTaskList(input, command);
                printBorder();
                break;
            }
        }
    }
}
