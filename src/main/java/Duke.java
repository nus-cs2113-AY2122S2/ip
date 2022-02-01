import java.util.Locale;
import java.util.Scanner;

public class Duke {

    public static final int MAX_TASKS = 100;

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
        Task[] taskLists = new Task[MAX_TASKS];

        String border = "_____________________________________________________";
        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();

        while (!userInput.equalsIgnoreCase("bye")) {
            System.out.println(border);

            if (userInput.equalsIgnoreCase("list")) {
                for (int i = 0; i < Task.getNumberOfTasks(); i++) {
                    System.out.println(i + 1 + "."+ taskLists[i].toString());
                }
            } else if (userInput.toLowerCase().startsWith("todo")) {
                String[] arrayOfTaskDescriptions = userInput.split(" ");
                String extractTaskDescription = "";
                for (int i = 1; i < arrayOfTaskDescriptions.length; i++) {
                    extractTaskDescription += arrayOfTaskDescriptions[i] + " ";
                }

                Task addNewTask = new Todo(extractTaskDescription);
                taskLists[Task.getNumberOfTasks() - 1] = addNewTask;
                System.out.println("Task added:\n\t" + addNewTask.toString());
                System.out.println("Now you have " + Task.getNumberOfTasks() + " tasks in your list!");

            } else if (userInput.toLowerCase().startsWith("deadline")) {
                String[] arrayOfTaskDescriptions = userInput.split(" ");
                String extractStrings = "";
                for (int i = 1; i < arrayOfTaskDescriptions.length; i++) {
                    extractStrings += arrayOfTaskDescriptions[i] + " ";
                }

                String extractTaskDescription = extractStrings.split("/by ")[0];
                String extractDeadlineTime = extractStrings.split("/by ")[1];

                Task addNewTask = new Deadline(extractTaskDescription, extractDeadlineTime);
                taskLists[Task.getNumberOfTasks() - 1] = addNewTask;
                System.out.println("Task added:\n\t" + addNewTask.toString());
                System.out.println("Now you have " + Task.getNumberOfTasks() + " tasks in your list!");

            } else if (userInput.toLowerCase().startsWith("event")) {
                String[] arrayOfTaskDescriptions = userInput.split(" ");
                String extractStrings = "";
                for (int i = 1; i < arrayOfTaskDescriptions.length; i++) {
                    extractStrings += arrayOfTaskDescriptions[i] + " ";
                }

                String extractTaskDescription = extractStrings.split("/at ")[0];
                String extractDeadlineTime = extractStrings.split("/at ")[1];

                Task addNewTask = new Event(extractTaskDescription, extractDeadlineTime);
                taskLists[Task.getNumberOfTasks() - 1] = addNewTask;
                System.out.println("Task added:\n\t" + addNewTask.toString());
                System.out.println("Now you have " + Task.getNumberOfTasks() + " tasks in your list!");

            } else if (userInput.toLowerCase().startsWith("unmark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);

                if (taskNumber > Task.getNumberOfTasks() || taskNumber <= 0) {
                    System.out.println("Task does not exist!");
                } else {
                    taskLists[taskNumber - 1].markAsUndone();
                    System.out.println("Uh oh! This task is undone:");
                    System.out.println(taskLists[taskNumber - 1].toString());
                }
            } else if (userInput.toLowerCase().startsWith("mark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);

                if (taskNumber > Task.getNumberOfTasks() || taskNumber <= 0) {
                    System.out.println("Task does not exist!");
                } else {
                    taskLists[taskNumber - 1].markAsDone();
                    System.out.println("Fantastic! This task is done:");
                    System.out.println(taskLists[taskNumber - 1].toString());
                }
            } else {
                Task addNewTask = new Todo(userInput);
                taskLists[Task.getNumberOfTasks() - 1] = addNewTask;
                System.out.println("Task added:\n\t" + addNewTask.toString());
                System.out.println("Now you have " + Task.getNumberOfTasks() + " tasks in your list!");
            }
            System.out.println(border);
            userInput = in.nextLine();
        }

        System.out.println(border);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(border);
    }
}
