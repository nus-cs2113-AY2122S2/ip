import java.util.Scanner;

public class Duke {
    public static void storeInput() {
        String line;
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];

        do {
            System.out.println("Storing Up to 100 Tasks, type list to show all tasks, bye to exit");
            line = in.nextLine();
            if (line.equalsIgnoreCase("bye")) {
                break;
            } else if (line.equalsIgnoreCase("list")) {
                printTasks(tasks);
            } else {
                int numOfTasks = Task.getNumOfTasks();
                tasks[numOfTasks] = new Task(line);
            }
        } while (Task.getNumOfTasks() <= 100);
    }

    public static void printTasks(Task[] tasks) {
        for (int i = 0; i < Task.getNumOfTasks(); i += 1) {
            System.out.println(i+1 + ". " + tasks[i].getTaskDescription());
        }
    }

    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n"
                + "Hello, nice to meet you. I'm Yae! (*^▽^*)\n"
                + "What can I do for you?\n"
                + "____________________________________________________________");
        storeInput();
        System.out.println("Goodbye, see you next time!");
    }
}
