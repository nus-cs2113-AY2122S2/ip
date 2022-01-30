import java.util.Arrays;
import java.util.Scanner;



public class Aeon {

    public static void addToList(Task[] list, Task t) {
        list[Task.getNoOfItems()] = t;
        Task.setNoOfItems(Task.getNoOfItems() + 1);
        System.out.println("Total: " + Task.getNoOfItems() + " task(s) in the list!");
    }

    public static void main(String[] args) {
        Task[] list = new Task[100];

        String logo = "     /\\   |  ____/ __ \\| \\ | |\n"
                + "    /  \\  | |__ | |  | |  \\| |\n"
                + "   / /\\ \\ |  __|| |  | | . ` |\n"
                + "  / ____ \\| |___| |__| | |\\  |\n"
                + " /_/    \\_\\______\\____/|_| \\_|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm AEON, your personal TO-DO list bot!\nWhat can I do for you?");
        Scanner in = new Scanner(System.in);
        String response = in.nextLine();
        while (!response.equals("bye")) {
            String[] words = response.split(" ", 2);
            switch(words[0]) {
            case("list"):
                for (int i = 0; i < Task.getNoOfItems(); i++) {
                    System.out.println(i + 1 + ". " + list[i]);
                }
                response = in.nextLine();
                break;
            case("unmark"):

                int index = Integer.parseInt(words[1]);
                list[index - 1].setNotDone();
                String description = list[index - 1].getDescription();
                System.out.println("Alright, marked as undone!:\n");
                System.out.println(list[index - 1]);
                response = in.nextLine();
                break;

            case("mark"):
                index = Integer.parseInt(words[1]);
                list[index - 1].setDone();
                description = list[index - 1].getDescription();
                System.out.println("Congrats on completing this task!:");
                System.out.println(list[index - 1]);
                response = in.nextLine();
                break;

            case("todo"):
                Todo todo = new Todo(words[1]);
                System.out.println("Task added!");
                System.out.println(todo);
                addToList(list, todo);
                response = in.nextLine();
                break;

            case("deadline"):
                String[] dueDate = words[1].split("/by", 2);
                Deadline d = new Deadline(dueDate[0], dueDate[1]);
                System.out.println("Task added!");
                System.out.println(d);
                addToList(list, d);
                response = in.nextLine();
                break;

            case("event"):
                String[] eventDate = words[1].split("/at", 2);
                Event e = new Event(eventDate[0], eventDate[1]);
                System.out.println("Task added!");
                System.out.println(e);
                addToList(list, e);
                response = in.nextLine();
                break;

            default:
                Task t = new Task(response);
                addToList(list, t);
                response = in.nextLine();
                break;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________\n");
    }
}
