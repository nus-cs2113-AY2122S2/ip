package aeon.controller;
import java.util.Scanner;
import aeon.task.Task;
import aeon.task.Event;
import aeon.task.Todo;
import aeon.task.Deadline;
import java.util.ArrayList;

public class Command {

    public static final String TASK_NOT_FOUND = "Task not found! Perhaps try listing out the available tasks first...";
    public static final String INVALID_INTEGER_MSG = "Please type in a valid integer!";
    public static final String TODO_DESC_ERROR = "Description of TODO cannot be empty!!!";
    public static final String DEADLINE_FORMAT_ERR = "Please try again in this format: deadline /by <date>";
    public static final String EVENT_FORMAT_ERR = "Please try again in this format: event /at <date>";
    public static final String INVALID_COMMAND = "Not sure what you were trying to do...";
    public static final String TASK_ADDED = "Task added!";
    public static final String CONGRATULATIONS_MSG = "Congrats on completing this task!:";
    public static final String MARK_UNDONE = "Alright, marked as undone!:";
    public static final String NO_TASKS = "No tasks!";
    public static final String TASK_DELETED = "Task deleted!";

    public static void CommandProcessor() {
        ArrayList<Task> list = new ArrayList<>();
        printWelcomeMessage();
        Scanner in = new Scanner(System.in);
        String response = in.nextLine();
        while (!response.equals("bye")) {
            String[] words = response.split(" ", 2);
            switch (words[0]) {
            case ("list"):
                printListOfTasks(list);
                break;
            case ("unmark"):
                try {
                    unmarkTask(list, words);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(TASK_NOT_FOUND);
                } catch (NumberFormatException e) {
                    System.out.println(INVALID_INTEGER_MSG);
                }
                break;
            case ("mark"):
                try {
                    markTask(list, words);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(TASK_NOT_FOUND);
                } catch (NumberFormatException e) {
                    System.out.println(INVALID_INTEGER_MSG);
                }
                break;
            case ("todo"):
                try {
                    addTodoTask(list, words);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(TODO_DESC_ERROR);
                }
                break;
            case ("deadline"):
                try {
                    addDeadlineTask(list, words);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(DEADLINE_FORMAT_ERR);
                }
                break;
            case ("event"):
                try {
                    addEventTask(list, words);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(EVENT_FORMAT_ERR);
                }
                break;
            case("delete"):
                try {
                    deleteTask(list, words);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(TASK_NOT_FOUND);
                } catch (NumberFormatException e) {
                    System.out.println(INVALID_INTEGER_MSG);
                }
                break;
            default:
                System.out.println(INVALID_COMMAND);
                break;
            }
            response = in.nextLine();
        }
        printGoodbyeMessage();
    }

    public static void addToList(ArrayList<Task> list, Task t) {
        list.add(t);
        //list[Task.getNoOfItems()] = t;
        Task.setNoOfItems(Task.getNoOfItems() + 1);
        System.out.println("Total: " + Task.getNoOfItems() + " task(s) in the list!");
    }

    public static void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________\n");
    }

    private static void addEventTask(ArrayList<Task> list, String[] words) throws IndexOutOfBoundsException {
        String[] eventDate = words[1].split(" /at ", 2);
        Task e = new Event(eventDate[0].trim(), eventDate[1].trim());
        System.out.println(TASK_ADDED);
        System.out.println(e);
        addToList(list, e);
    }

    private static void addDeadlineTask(ArrayList<Task> list, String[] words) throws IndexOutOfBoundsException {
        String[] dueDate = words[1].split(" /by ", 2);
        Task d = new Deadline(dueDate[0].trim(), dueDate[1].trim());
        System.out.println(TASK_ADDED);
        System.out.println(d);
        addToList(list, d);
    }

    private static void addTodoTask(ArrayList<Task> list, String[] words) throws IndexOutOfBoundsException {
        Task t = new Todo(words[1]);
        System.out.println(TASK_ADDED);
        System.out.println(t);
        addToList(list, t);
    }

    private static void markTask(ArrayList<Task> list, String[] words) throws IndexOutOfBoundsException,
            NumberFormatException {
        int index;
        index = Integer.parseInt(words[1]);
        list.get(index - 1).setDoneStatus(true);
        System.out.println(CONGRATULATIONS_MSG);
        System.out.println(list.get(index - 1));
    }

    private static void unmarkTask(ArrayList<Task> list, String[] words)
            throws IndexOutOfBoundsException, NumberFormatException {
        int index = Integer.parseInt(words[1]);
        list.get(index - 1).setDoneStatus(false);
        System.out.println(MARK_UNDONE);
        System.out.println(list.get(index - 1));
    }

    private static void printListOfTasks(ArrayList<Task> list) {
        Integer noOfItems = Task.getNoOfItems();
        if (noOfItems == 0) {
            System.out.println(NO_TASKS);
        }
        for (int index = 0; index < noOfItems; index++) {
            System.out.println(index + 1 + ". " + list.get(index));
        }
    }

    private static void deleteTask(ArrayList<Task> list, String[] words)
            throws IndexOutOfBoundsException, NumberFormatException {
        int index = Integer.parseInt(words[1]);
        System.out.println(TASK_DELETED);
        System.out.println(list.get(index - 1));
        list.remove(index - 1);
        Task.setNoOfItems(Task.getNoOfItems() - 1);
        System.out.println("Total: " + Task.getNoOfItems() + " task(s) in the list!");
    }

    public static void printWelcomeMessage() {
        String logo = "     /\\   |  ____/ __ \\| \\ | |\n"
                + "    /  \\  | |__ | |  | |  \\| |\n"
                + "   / /\\ \\ |  __|| |  | | . ` |\n"
                + "  / ____ \\| |___| |__| | |\\  |\n"
                + " /_/    \\_\\______\\____/|_| \\_|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm AEON, your personal TO-DO list bot!\nWhat can I do for you?");
    }
}
