import task.Deadline;
import task.Event;
import task.Task;
import errors.Errors;
import task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

import static errors.Errors.INVALID_TASK_DETAILS_ERROR;

public class Duke {

    public static ArrayList<Task> taskList;
    public static int taskCounter = 0;

    public static String getFirstWordOfCommand(String s) {
        int spaceIndex = s.indexOf(" ");
        if (spaceIndex == -1) {
            return s;
        }
        String firstWord = s.substring(0, spaceIndex);
        return firstWord;
    }

    public static void addDeadlineTask(String input){
        try {
            Deadline newDeadline = new Deadline(Deadline.getDeadlineTask(input), Deadline.getDeadlineDate(input));
            taskList.add(newDeadline);
            taskCounter++;
            System.out.println("Got it. I've added this task:");
            Task.printTask(newDeadline);
            Task.printNumberOfTasksInList(taskCounter);
        } catch (StringIndexOutOfBoundsException se){
            System.out.println(INVALID_TASK_DETAILS_ERROR);
        }
    }

    public static int getTaskNumberArgument(String input) {
        int spaceIndex = input.indexOf(" ");
        String taskNum = input.substring(spaceIndex + 1);
        System.out.println(taskNum);
        return Integer.parseInt(taskNum.trim());
    }

    public static void addToDoTask(String input){
        try{
            String taskDescription = Todo.getToDoTask(input);

            Todo newTodo = new Todo(taskDescription);
           taskList.add(newTodo);
            taskCounter++;
            System.out.println("Got it. I've added this task:");
            Task.printTask(newTodo);
            Task.printNumberOfTasksInList(taskCounter);
        } catch (IndexOutOfBoundsException iobe){
            System.out.println(Errors.INVALID_TASK_DETAILS_ERROR);
        }
    }

    public static void addEventTask(String input){
        try {
            Event newEvent = new Event(Event.getEventTask(input), Event.getEventDateTime(input));
            taskList.add(newEvent);
            taskCounter++;
            System.out.println("Got it. I've added this task:");
            Task.printTask(newEvent);
            Task.printNumberOfTasksInList(taskCounter);
        } catch(StringIndexOutOfBoundsException se){
            System.out.println(Errors.INVALID_TASK_DETAILS_ERROR);
        }
    }
    public static void markTaskAsComplete(String input){
        try {
            int taskNum = getTaskNumberArgument(input);
            //System.out.println(taskNum);
            taskList.get(taskNum-1).setDone(true);
            //System.out.println(taskList[taskNum-1].isDone());
            Task.printTask(taskList.get(taskNum-1));
            System.out.println("Nice! I've marked this task as done:\n");
        } catch (NumberFormatException nfe){
            System.out.println(Errors.INVALID_TASK_MARK_ERROR);
        }
    }

    public static void unmarkTaskAsIncomplete(String input){
        int taskNum = getTaskNumberArgument(input);
        taskList.get(taskNum-1).setDone(false);
        Task.printTask(taskList.get(taskNum-1));
        System.out.println("Ok I have marked this task as incomplete:\n");

    }

    public static void main(String[] args) {
        printWelcomeLogo();
        takeInputAndProcess();
    }

    private static void takeInputAndProcess() {
        String input;
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();

        while (!input.equalsIgnoreCase("bye")) {
            String command = getFirstWordOfCommand(input);
            switch (command) {
            case "list":
                Task.printList(taskList, taskCounter);
                break;
            case "mark":
                markTaskAsComplete(input);
                break;
            case "unmark":
                unmarkTaskAsIncomplete(input);
                break;
            case "todo":
                addToDoTask(input);
                break;
            case "deadline":
                addDeadlineTask(input);
                break;
            case "event":
                addEventTask(input);
                break;
            default:
                System.out.println(Errors. INPUT_ERROR);
                break;
            }
            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printWelcomeLogo() {
        String customNameLogo = " .______.                .__    .__                                           \n" +
                "|   \\_ |______________  |  |__ |__| _________________    _____   ____  ______\n" +
                "|   || __ \\_  __ \\__  \\ |  |  \\|  |/     \\_  __ \\__  \\  /     \\ /  _ \\/  ___/\n" +
                "|   || \\_\\ \\  | \\// __ \\|   Y  \\  |  Y Y  \\  | \\// __ \\|  Y Y  (  <_> )___ \\ \n" +
                "|___||___  /__|  (____  /___|  /__|__|_|  /__|  (____  /__|_|  /\\____/____  >\n" +
                "         \\/           \\/     \\/         \\/           \\/      \\/           \\/";
        System.out.println("Hello from\n" + customNameLogo);
        System.out.println("Hello! I'm Ibrahimramos, your friendly multi-racial bot\nWhat can I do for you?");
    }
}
