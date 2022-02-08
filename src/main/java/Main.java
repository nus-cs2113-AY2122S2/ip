import java.util.Scanner;

public class Main {
    //Constant(s) used in multiple classes
    public static final String BORDER_LINE = "-------------------------------------------------------------";

    //Constants used in this Main class
    public static final String GREETING_MESSAGE = "Hello! I'm Alexis, your trusty helper!\n"
            + "What can I do for you?\n\nHint: You may use these commands to navigate around:\n"
            + "[list] [todo] [deadline] [event] [mark] [unmark] [bye]";
    public static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String DISPLAY_TASK_MESSAGE = "Here are the tasks in your list:";
    public static final String EMPTY_LIST_MESSAGE = "Your list is empty. You have no tasks now.";
    public static final String INVALID_INPUT_MESSAGE = "Invalid input\n"
            + "Please use the format: [type_of_task] [task_description]";

    //Constants used in the Task class
    public static final String ADD_NEW_TASK_MESSAGE = "Got it! I've added this task:";
    public static final String MARK_AS_DONE_MESSAGE = "Great! I've marked this task as done:";
    public static final String MARK_AS_UNDONE_MESSAGE = "Ok. I've marked this task as not done yet:";

    public static void greet() {
        System.out.println(BORDER_LINE);
        System.out.println(GREETING_MESSAGE);
        System.out.println(BORDER_LINE);
    }

    public static void exit() {
        System.out.println(BORDER_LINE);
        System.out.println(GOODBYE_MESSAGE);
        System.out.println(BORDER_LINE);
    }

    private static void invalid_input() {
        System.out.println(BORDER_LINE);
        System.out.println(INVALID_INPUT_MESSAGE);
        System.out.println(BORDER_LINE);
    }

    public static void displayList(Task[] tasks, int numOfTasks) {
        System.out.println(BORDER_LINE);
        if (numOfTasks > 0) {
            System.out.println(DISPLAY_TASK_MESSAGE);
            for (int i = 0; i < numOfTasks; i++) {
                System.out.println((i + 1) + ".[" + tasks[i].typeOfTask() + "]["
                        + tasks[i].getStatusIcon() + "] " + tasks[i].getFullDescription());
            }
        } else {
            System.out.println(EMPTY_LIST_MESSAGE);
        }
        System.out.println(BORDER_LINE);
    }

    public static int todo(Task[] tasks, int numOfTasks, String input) {
        tasks[numOfTasks] = new Todo(input.substring(5));
        return numOfTasks + 1;
    }

    public static int deadline(Task[] tasks, int numOfTasks, String input) {
        String[] deadlineDescriptionSplitArr = input.substring(9).split(" /by ");
        tasks[numOfTasks] = new Deadline(deadlineDescriptionSplitArr[0], deadlineDescriptionSplitArr[1]);
        return numOfTasks + 1;
    }

    public static int event(Task[] tasks, int numOfTasks, String input) {
        String[] eventDescriptionSplitArr = input.substring(6).split(" /at ");
        tasks[numOfTasks] = new Event(eventDescriptionSplitArr[0], eventDescriptionSplitArr[1]);
        return numOfTasks + 1;
    }

    public static void mark(Task[] tasks, String[] arrOfInputStrings) {
        int inputTaskNumber = Integer.parseInt(arrOfInputStrings[1]) - 1;
        tasks[inputTaskNumber].markAsDone();
    }

    public static void unmark(Task[] tasks, String[] arrOfInputStrings) {
        int inputTaskNumber = Integer.parseInt(arrOfInputStrings[1]) - 1;
        tasks[inputTaskNumber].markAsUndone();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int taskCounter = 0;
        Task[] tasks = new Task[100];

        greet();
        String input = in.nextLine();

        while (!input.equals("bye")) {

            String[] arrOfInputStrings = input.split(" ");

            switch(arrOfInputStrings[0]) {
            case "list":
                displayList(tasks, taskCounter);
                break;
            case "todo":
                taskCounter = todo(tasks, taskCounter, input);
                break;
            case "deadline":
                taskCounter = deadline(tasks, taskCounter, input);
                break;
            case "event":
                taskCounter = event(tasks, taskCounter, input);
                break;
            case "mark":
                mark(tasks, arrOfInputStrings);
                break;
            case "unmark":
                unmark(tasks, arrOfInputStrings);
                break;
            default:
                invalid_input();
                break;
            }

            input = in.nextLine();
        }
        exit();

    }

}
