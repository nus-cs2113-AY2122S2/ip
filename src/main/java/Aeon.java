import java.util.Scanner;
import aeon.task.Task;
import aeon.task.Event;
import aeon.task.Todo;
import aeon.task.Deadline;

public class Aeon {

    public static void main(String[] args) {
        Task[] list = new Task[100];
        printWelcomeMessage();
        Scanner in = new Scanner(System.in);
        String response = in.nextLine();
        while (!response.equals("bye")) {
            String[] words = response.split(" ", 2);
            switch(words[0]) {
            case("list"):
                printListOfTasks(list);
                break;
            case("unmark"):
                try {
                    unmarkTask(list, words);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Task not found! Perhaps try listing out the available tasks first...");
                }
                break;
            case("mark"):
                try {
                    markTask(list, words);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Task not found! Perhaps try listing out the available tasks first...");
                }
                break;
            case("todo"):
                try {
                    addTodoTask(list, words);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Description of TODO cannot be empty!!!");
                }
                break;
            case("deadline"):
                try {
                    addDeadlineTask(list, words);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please try again in this format: deadline /by <date>");
                }
                break;
            case("event"):
                try {
                    addEventTask(list, words);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please try again in this format: event /at <date>");
                }
                break;
            default:
                try {
                    throw new AeonException();
                } catch (AeonException e) {
                    System.out.println("Not sure what you were trying to do...");
                }
                break;
            }
            response = in.nextLine();
        }
        printGoodbyeMessage();
    }

    public static void addToList(Task[] list, Task t) {
        list[Task.getNoOfItems()] = t;
        Task.setNoOfItems(Task.getNoOfItems() + 1);
        System.out.println("Total: " + Task.getNoOfItems() + " task(s) in the list!");
    }

    private static void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________\n");
    }

    private static void addEventTask(Task[] list, String[] words) throws IndexOutOfBoundsException{
        String[] eventDate = words[1].split(" /at ", 2);
        Task e = new Event(eventDate[0].trim(), eventDate[1].trim());
        System.out.println("Task added!");
        System.out.println(e);
        addToList(list, e);
    }

    private static void addDeadlineTask(Task[] list, String[] words) throws IndexOutOfBoundsException {
        String[] dueDate = words[1].split(" /by ", 2);
        Task d = new Deadline(dueDate[0].trim(), dueDate[1].trim());
        System.out.println("Task added!");
        System.out.println(d);
        addToList(list, d);
    }

    private static void addTodoTask(Task[] list, String[] words) throws IndexOutOfBoundsException {
        Task t = new Todo(words[1]);
        System.out.println("Task added!");
        System.out.println(t);
        addToList(list, t);
    }

    private static void markTask(Task[] list, String[] words) throws IndexOutOfBoundsException {
        int index;
        index = Integer.parseInt(words[1]);
        list[index - 1].setDoneStatus(true);
        System.out.println("Congrats on completing this task!:");
        System.out.println(list[index - 1]);
    }

    private static void unmarkTask(Task[] list, String[] words) throws IndexOutOfBoundsException{
        int index = Integer.parseInt(words[1]);
        list[index - 1].setDoneStatus(false);
        System.out.println("Alright, marked as undone!:");
        System.out.println(list[index - 1]);
    }

    private static void printListOfTasks(Task[] list) {
        Integer noOfItems = Task.getNoOfItems();
        if (noOfItems == 0) {
            System.out.println("No tasks!");
        }
        for (int index = 0; index < noOfItems; index++) {
            System.out.println(index + 1 + ". " + list[index]);
        }
    }

    private static void printWelcomeMessage() {
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
