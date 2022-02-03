import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        welcomeMessage();
        greet();
        converse(tasks, taskCount);
        exit();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
    }

    public static void greet() {
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void exit() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }


    public static void converse(Task[] tasks, int taskCount) {
        Scanner sc = new Scanner(System.in);
        String response = sc.nextLine();

        boolean isNotBye = !response.equals("bye");

        while (isNotBye) {
            String[] words = response.split(" ", 2);
            String command = words[0];
            String detail = (words.length > 1) ? words[1] : null;

            switch (command) {
            case "list":
                listTasks();
                break;
            case "mark":
                markTask(response);
                break;
            case "unmark":
                unmarkTask(response);
                break;
            case "todo":
                addTask(new Todo(detail));
                break;
            case "deadline":
                int byIndex = detail.indexOf("/by");
                boolean isByPresent = byIndex != -1;
                if (isByPresent) {
                    String by = detail.substring(byIndex + 3).trim();
                    String deadlineDesc = detail.substring(0, byIndex).trim();
                    addTask(new Deadline(deadlineDesc, by));
                } else {
                    System.out.println("Sorry, missing /by argument...");
                }
                break;
            case "event":
                int atIndex = detail.indexOf("/at");
                boolean isAtPresent = atIndex != -1;
                if (isAtPresent) {
                    String at = detail.substring(atIndex + 3).trim();
                    String eventDesc = detail.substring(0, atIndex).trim();
                    addTask(new Event(eventDesc, at));
                } else {
                    System.out.println("Sorry, missing /at argument...");
                }
                break;
            default:
                System.out.println("no such command");
            }
            response = sc.nextLine();
            isNotBye = !response.equals("bye");
        }
    }

    public static void listTasks() {
        printLine();
        for (int i = 0; i < taskCount; i++) {
            System.out.print(i + 1);
            System.out.println("." + tasks[i]);
        }
        printLine();
    }

    public static void addTask(Task task) {
        printLine();
        tasks[taskCount] = task;
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        taskCount++;
        System.out.println("Now you have " + taskCount + " task(s) in the list.");
        printLine();
    }

    public static void markTask(String response) {
        printLine();
        String[] words = response.split(" ");
        int taskIndex = Integer.parseInt(words[1]);
        boolean isNotIndex = taskIndex > taskCount || taskIndex == 0;
        if (isNotIndex) {
            System.out.println("Sorry, seems like there's no such task with that index.");
            printLine();
            return;
        } else {
            Task t = tasks[taskIndex - 1];
            boolean isNotDone = !t.isDone;

            if (isNotDone) {
                t.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(t);
            } else {
                System.out.println("Hmm, you've already marked this task.");
            }
        }

        printLine();
    }

    public static void unmarkTask(String response) {
        printLine();

        String[] words = response.split(" ");
        int taskIndex = Integer.parseInt(words[1]);
        boolean isNotIndex = taskIndex > taskCount || taskIndex == 0;

        if (isNotIndex) {
            System.out.println("Sorry, seems like there's no such task with that index.");
            printLine();
            return;
        } else {
            Task t = tasks[taskIndex - 1];
            boolean isDone = t.isDone;

            if (isDone) {
                t.markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(t);
            } else {
                System.out.println("Hmm, this task is already unmarked.");
            }
        }

        printLine();
    }
}
