import java.util.Locale;
import java.util.Scanner;

public class Duke {

    public static void displayGreeting() {
        String border = "_____________________________________________________";
        String logo = " _______     __   __  \n"
                + "|   _   |[x]| | / / [x]  \n"
                + "|  | |  || ||  / /  | |\n"
                + "|  | |  || ||  <    | |\n"
                + "|__/ |__||_||_| |_| |_|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(border);
        System.out.println("Hello! I'm Niki");
        System.out.println("What can I do for you?");
        System.out.println(border);
    }

    public static void main(String[] args) {
        displayGreeting();
        Task[] taskLists = new Task[100];

        String border = "_____________________________________________________";
        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();

        while (!userInput.equalsIgnoreCase("bye")) {
            System.out.println(border);

            if (userInput.equalsIgnoreCase("list")) {
                for (int i = 0; i < Task.getNumberOfTasks(); i++) {
                    System.out.println(i + 1 + ".[" + taskLists[i].getStatusIcon()
                            + "] " + taskLists[i].getTaskDescription());
                }
            } else if (userInput.toLowerCase().startsWith("unmark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);

                if (taskNumber > Task.getNumberOfTasks() || taskNumber <= 0) {
                    System.out.println("Task does not exist!");
                } else {
                    taskLists[taskNumber - 1].markAsUndone();
                    System.out.println("Uh oh! This task is undone:");
                    System.out.println("[" + taskLists[taskNumber - 1].getStatusIcon() + "] "
                            + taskLists[taskNumber - 1].getTaskDescription());
                }
            } else if (userInput.toLowerCase().startsWith("mark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);

                if (taskNumber > Task.getNumberOfTasks() || taskNumber <= 0) {
                    System.out.println("Task does not exist!");
                } else {
                    taskLists[taskNumber - 1].markAsDone();
                    System.out.println("Fantastic! This task is done:");
                    System.out.println("[" + taskLists[taskNumber - 1].getStatusIcon() + "] "
                            + taskLists[taskNumber - 1].getTaskDescription());
                }
            } else {
                Task addNewTask = new Task(userInput);
                taskLists[Task.getNumberOfTasks() - 1] = addNewTask;
                System.out.println("added: " + userInput);
            }

            System.out.println(border);
            userInput = in.nextLine();
        }

        System.out.println(border);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(border);
    }
}
