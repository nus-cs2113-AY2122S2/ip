import java.util.Scanner;

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
                unmarkTask(list, words);
                break;
            case("mark"):
                markTask(list, words);
                break;
            case("todo"):
                addTodoTask(list, words);
                break;
            case("deadline"):
                addDeadlineTask(list, words);
                break;
            case("event"):
                addEventTask(list, words);
                break;
            default:
                Task t = new Task(response);
                addToList(list, t);
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

    private static void addEventTask(Task[] list, String[] words) {
        String[] eventDate = words[1].split("/at", 2);
        Task e = new Event(eventDate[0], eventDate[1]);
        System.out.println("Task added!");
        System.out.println(e);
        addToList(list, e);
    }

    private static void addDeadlineTask(Task[] list, String[] words) {
        String[] dueDate = words[1].split("/by", 2);
        Task d = new Deadline(dueDate[0], dueDate[1]);
        System.out.println("Task added!");
        System.out.println(d);
        addToList(list, d);
    }

    private static void addTodoTask(Task[] list, String[] words) {
        Task t = new Todo(words[1]);
        System.out.println("Task added!");
        System.out.println(t);
        addToList(list, t);
    }

    private static void markTask(Task[] list, String[] words) {
        int index;
        index = Integer.parseInt(words[1]);
        list[index - 1].setDone();
        System.out.println("Congrats on completing this task!:");
        System.out.println(list[index - 1]);
    }

    private static void unmarkTask(Task[] list, String[] words) {
        int index = Integer.parseInt(words[1]);
        list[index - 1].setNotDone();
        System.out.println("Alright, marked as undone!:\n");
        System.out.println(list[index - 1]);
    }

    private static void printListOfTasks(Task[] list) {
        for (int index = 0; index < Task.getNoOfItems(); index++) {
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
