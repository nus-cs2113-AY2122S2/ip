import java.util.Arrays;
import java.util.Scanner;

public class Duke {
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
            if (response.equals("list")) {
                for (int i = 0; i < Task.getNoOfItems(); i++) {
                    System.out.println((i + 1) + ". [" + list[i].getStatusIcon() + "] " + list[i].getDescription());
                }
                response = in.nextLine();

            } else if (response.contains("unmark")) {
                String[] words = response.split(" ");
                int index = Integer.parseInt(words[1]);
                list[index - 1].setNotDone();
                System.out.println("Alright, marked as undone!:\n" + "[" + list[index - 1].getStatusIcon() + "] " +
                        list[index - 1].getDescription());
                response = in.nextLine();

            } else if (response.contains("mark")) {
                String[] words = response.split(" ");
                int index = Integer.parseInt(words[1]);
                list[index - 1].setDone();
                String description = list[index - 1].getDescription();

                System.out.println("Congrats on completing this task!:\n" + "[" + list[index - 1].getStatusIcon() + "] " +
                        list[index - 1].getDescription());
                response = in.nextLine();
            }
            else {
                Task t = new Task(response);
                list[Task.getNoOfItems()] = t;
                System.out.println("added: " + response);
                Task.setNoOfItems(Task.getNoOfItems() + 1);

                response = in.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________\n");
    }
}
