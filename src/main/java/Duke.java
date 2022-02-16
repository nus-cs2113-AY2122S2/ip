import java.util.ArrayList;
import java.util.Scanner;

import com.sun.source.util.TaskListener;
import em.exception.EmptyTaskDescriptionException;
import em.exception.IllegalInputException;
import em.exception.TaskOutOfRangeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;
import java.util.ArrayList;


public class Duke {
    public static final String LINESEPARATOR = "____________________________________________________________\n";
    //public static final int MAX_TASKS = 100;
    //public static final Task[] taskList = new Task[MAX_TASKS];
    public static ArrayList<Task> taskList = new ArrayList<>();

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
                + "Hello! I'm EM\n" + "What can I do for you?\n" + LINESEPARATOR;
        System.out.println(logo + greetings);
    }

    /*Display goodbye message*/
    public static void displayFarewell() {
        String farewell = LINESEPARATOR
                + "Bye. Hope to see you again soon!\n" + LINESEPARATOR;
        System.out.println(farewell);
    }

    public static void displayTaskList(ArrayList<Task> taskList) {
        System.out.println(LINESEPARATOR);
        System.out.println("Here are the tasks in your list:");
        //if (Task.getTaskCount() == 0) {
        if (taskList.size() == 0) {
            System.out.println("List is Empty!");
        }
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i).toString());
        }
        System.out.println(LINESEPARATOR);
    }

    //public static void addTask(String userInput, Task[] taskList) {
    public static void addTask(String userInput, ArrayList<Task> taskList) {
        String[] taskDescription = userInput.split(" ", 2);
        Task newTask = new ToDo(taskDescription[1]);
        taskList.add(newTask);
        //taskList[Task.getTaskCount() - 1] = newTask;
        System.out.println(LINESEPARATOR + "Got it. I've added this task:");
        System.out.println(taskList.get(taskList.size() - 1).toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.\n" + LINESEPARATOR);
    }

    public static void addTaskAndTime(String userInput, ArrayList<Task> taskList) {
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
        taskList.add(newTask);
        //taskList[Task.getTaskCount() - 1] = newTask;
        System.out.println(LINESEPARATOR + "Got it. I've added this task:");
        //System.out.println(taskList[Task.getTaskCount() - 1].toString());
        //System.out.println(taskList.get(Task.getTaskCount() - 1).toString());
        System.out.println(taskList.get(taskList.size()-1).toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.\n" + LINESEPARATOR);
    }

    public static boolean isTaskValid(int taskNumber) throws TaskOutOfRangeException {
        if (taskNumber > taskList.size() || taskNumber <= 0) {
            throw new TaskOutOfRangeException();
        }
        return true;
    }
    public static void deleteTask(String userInput, ArrayList<Task> taskList) {

    }

    public static String checkValidityOfInput(String userInput) throws EmptyTaskDescriptionException, IllegalInputException {
        String[] arrayOfUserInput = userInput.split(" ");
        int lengthOfArrayOfUserInput = arrayOfUserInput.length;
        if (arrayOfUserInput.length == 1 && arrayOfUserInput[0].equalsIgnoreCase("list")) {
            return userInput; //list
        } else if (arrayOfUserInput.length > 1 && arrayOfUserInput[0].equalsIgnoreCase("list")) {
            throw new IllegalInputException(); //list and more input
        } else if (arrayOfUserInput.length <= 1 && (arrayOfUserInput[0].equalsIgnoreCase("todo")
                || arrayOfUserInput[0].equalsIgnoreCase("event") || arrayOfUserInput[0].equalsIgnoreCase("deadline")
                || arrayOfUserInput[0].equalsIgnoreCase("delete") || arrayOfUserInput[0].equalsIgnoreCase("mark")
                || arrayOfUserInput[0].equalsIgnoreCase("unmark"))) { //todo, deadline, event with no parameters
            throw new EmptyTaskDescriptionException(); //task with no description
        } else if ((arrayOfUserInput.length <= 1) || !(arrayOfUserInput[0].equalsIgnoreCase("todo")
                || arrayOfUserInput[0].equalsIgnoreCase("event") || arrayOfUserInput[0].equalsIgnoreCase("deadline")
                || arrayOfUserInput[0].equalsIgnoreCase("delete") || arrayOfUserInput[0].equalsIgnoreCase("mark")
                || arrayOfUserInput[0].equalsIgnoreCase("unmark"))) {
            throw new IllegalInputException(); //not valid task action
        } else {
            return userInput;
        }
    }

    public static void processAction() {
        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        while (!userInput.equalsIgnoreCase("Bye")) {
            try {
                String validUserInput = checkValidityOfInput(userInput);
                String action = userInput.split(" ")[0];
                switch (action.toLowerCase()) {
                case "list":
                    displayTaskList(taskList);
                    break;
                case "mark":
                    int markTaskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    if (isTaskValid(markTaskNumber)) {
                        taskList.get(markTaskNumber - 1).markAsDone(markTaskNumber, taskList);
                    }
                    break;
                case "unmark":
                    int unmarkTaskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    if (isTaskValid(unmarkTaskNumber)) {
                        taskList.get(unmarkTaskNumber - 1).markAsUndone(unmarkTaskNumber, taskList);
                    }
                    break;
                case "todo":
                    addTask(userInput, taskList);
                    break;
                case "event":
                case "deadline":
                    addTaskAndTime(userInput, taskList);
                    break;
                case "delete":
                    int deleteTaskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    if (isTaskValid(deleteTaskNumber)) {
                        taskList.get(deleteTaskNumber - 1).deleteTask(deleteTaskNumber, taskList);
                    }
                    break;
                default:
                    System.out.println("Invalid");
                }
            } catch (TaskOutOfRangeException e) {
                System.out.println(LINESEPARATOR + "The task you are looking for is not available.");
                System.out.print("☹ OOPS!!! Please enter a valid task number.\n" + LINESEPARATOR);
            } catch (IllegalInputException e) {
                System.out.print(LINESEPARATOR + "☹ OOPS!!! I'm sorry, but I don't know what that means :-( \n" + LINESEPARATOR);
            } catch (EmptyTaskDescriptionException e) {
                System.out.print(LINESEPARATOR + "☹ OOPS!!! The task description cannot be empty.\n" + LINESEPARATOR);
            } catch (IndexOutOfBoundsException e) {
                System.out.print(LINESEPARATOR + "Did not include time or description.\n" + LINESEPARATOR);
            } catch (NumberFormatException e) {
                System.out.print(LINESEPARATOR + "The task number entered is not a number\n" + LINESEPARATOR);
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