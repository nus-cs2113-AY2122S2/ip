import java.util.Scanner;
import java.util.ArrayList;

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

    // Global variable for list of task
    static ArrayList<Task> taskList = new ArrayList<Task>();
    static int numOfTask = 0;

    public static void printIntro(){
        System.out.println(INTRO_LOGO);
        printBorder();
        System.out.println(" Good Morning sir, I am DukeBot, your personal assistant! " +
                            "\n What can I do for you today?");
        printBorder();
    }

    public static void printOutro(){
        System.out.println(OUTRO_LOGO);
        printBorder();
        System.out.println(" I guess it's time to say good bye... \n Please come again soon!");
        printBorder();
    }

    public static void printBorder(){
        System.out.println(BORDER);
    }

    public static String getCommand(String input){
        return input.split(" ")[0];
    }

    public static void printTaskList(){
        int counter = 1;
        for (Task task : taskList){
            System.out.print(" "+counter +".");
            printTask(task);
            counter++;
        }
    }

    public static void addTaskToTaskList(String taskName){
        Task newTask = new Task(taskName);
        taskList.add(newTask);
        numOfTask++;
    }

    public static Task getTask(int taskNumber){
        // -1 is to offset the counting of array list from 0
        return taskList.get(taskNumber - 1);
    }

    public static int getTaskNumber(String input){
        return Integer.parseInt(input.split(" ")[1]);
    }

    public static void printTask(Task task){
        System.out.println(" ["+task.getStatusIcon()+"] "+task.getTaskName());
    }

    public static void markTask(String input, boolean doneStatus){
        int taskNum = getTaskNumber(input);
        Task markedTask = getTask(taskNum);
        markedTask.setDone(doneStatus);

        if (doneStatus == true){
            System.out.println("Nice! I'v marked this task as done:");
        }else{
            System.out.println("Okay! I'v marked this task as not done:");
        }
        printTask(markedTask);
    }

    public static void main(String[] args) {
        printIntro();
        while (true){
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            String command = getCommand(input);
            switch (command){
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
                addTaskToTaskList(input);
                System.out.println(" added: "+input);
                printBorder();
                break;
            }
        }
    }
}
