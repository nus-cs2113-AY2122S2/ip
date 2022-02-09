import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static final String LINESEPARATOR = "____________________________________________________________\n";
    public static final int MAX_TASKS = 100;
    public static final Task[] taskList = new Task[MAX_TASKS];
    public static void displayLogo() {
        String logo = "                       ___\n"
                + "                      / ()\\\n"
                + "                    _|_____|_\n"
                + "                   | | === | |\n"
                + " _____  _      _   |_|  0  |_|\n"
                + "| ___| | \\    / |   ||  0  ||\n"
                + "| |__  |  \\  /  |   ||__*__||\n"
                + "|  __| |   \\/   |  |~ \\___/ ~| \n"
                + "| |___ | |\\__/| |  /=\\ /=\\ /=\\ \n"
                + "|_____||_|    |_|__[_]_[_]_[_]______________________________\n";
        String greetings = LINESEPARATOR
                + "Hello! I'm EM\n"
                + "What can I do for you?\n"
                + LINESEPARATOR;
        System.out.println(logo + greetings);
    }

    /*Display goodbye message*/
    public static void displayFarewell() {
        String farewell = LINESEPARATOR
                + "Bye. Hope to see you again soon!\n"
                + LINESEPARATOR;
        System.out.println(farewell);
    }

    public static void displayTaskList(Task[] taskList) {
        System.out.println(LINESEPARATOR);
        System.out.println("Here are the tasks in your list:");
        if (Task.getTaskCount() == 0) {
            System.out.println("List is Empty!");
        }

        for (int i = 0; i < Task.getTaskCount(); i++) {
            System.out.println((i + 1) + ". " + taskList[i].toString());
        }
        System.out.println(LINESEPARATOR);
    }

    public static void addTask(String userInput, Task[] taskList) {
        String[] taskDescription = userInput.split(" ", 2);
        Task newTask = new ToDo(taskDescription[1]);
        taskList[Task.getTaskCount() - 1] = newTask;
        System.out.println(LINESEPARATOR + "Got it. I've added this task:");
        System.out.println(taskList[Task.getTaskCount()-1].toString());
        System.out.println("Now you have " + Task.getTaskCount() + " tasks in the list.\n" + LINESEPARATOR);
    }

    public static void addTaskAndTime(String userInput, Task[] taskList) {
        String[] arrayOfUserInput = userInput.split(" ", 2);
        arrayOfUserInput = userInput.split("/", 2);
        String taskLongDesc = arrayOfUserInput[0];
        String[] taskDescription = taskLongDesc.split(" ",2);
        String timeWithTaskType = arrayOfUserInput[1];
        String[] timing = timeWithTaskType.split(" ", 2);

        Task newTask;
        if (timing[0].equals("by")) {
            newTask = new Deadline(taskDescription[1], timing[1]);
        } else{
            newTask = new Event(taskDescription[1], timing[1]);
        }
        taskList[Task.getTaskCount() - 1] = newTask;
        System.out.println(LINESEPARATOR + "Got it. I've added this task:");
        System.out.println(taskList[Task.getTaskCount()-1].toString());
        System.out.println("Now you have " + Task.getTaskCount() + " tasks in the list.\n" + LINESEPARATOR);
    }

    public static boolean isTaskValid(int taskNumber) {
        if (taskNumber > Task.getTaskCount() || taskNumber <= 0) {
            System.out.println(LINESEPARATOR + "Task does not exist!\n" + LINESEPARATOR);
            return false;
        }
        return true;
    }

    public static void processAction() {
        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        while (!userInput.equalsIgnoreCase("Bye")) {
            String action = userInput.split(" ")[0];
            switch (action.toLowerCase()) {
            case "list":
                displayTaskList(taskList);
                break;
            case "mark":
                int markTaskNumber = Integer.parseInt(userInput.split(" ")[1]);
                if (isTaskValid(markTaskNumber)) {
                    taskList[markTaskNumber - 1].markAsDone(markTaskNumber, taskList);
                }
                break;
            case "unmark":
                int unmarkTaskNumber = Integer.parseInt(userInput.split(" ")[1]);
                if (isTaskValid(unmarkTaskNumber)) {
                    taskList[unmarkTaskNumber - 1].markAsUndone(unmarkTaskNumber, taskList);
                }
                break;
            case "todo":
                addTask(userInput, taskList);
                break;
            case "event":
            case "deadline":
                addTaskAndTime(userInput, taskList);
                break;
            default:
                System.out.println("Invalid");
            }
            userInput = in.nextLine();
        }
    }

    public static void main(String[] args) {
        displayLogo();
        processAction();
        displayFarewell();
    }
}