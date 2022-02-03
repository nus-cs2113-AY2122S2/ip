import java.util.Scanner;

public class Duke {

    public static Task[] tasks = new Task[100];
    public static int taskCount = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        String lowerCaseUserInput = userInput.toLowerCase();
        while (!lowerCaseUserInput.equals("bye")) {
            String[] splitUserInput = lowerCaseUserInput.split(" ");
            if (lowerCaseUserInput.equals("list")) {
                list();
            } else if (lowerCaseUserInput.startsWith("mark")) {
                int taskNumber = Integer.parseInt(splitUserInput[1]);
                tasks[taskNumber - 1].markTaskAsDone();
                System.out.println(tasks[taskNumber - 1]);
                System.out.println("____________________________________________________________");
            } else if (lowerCaseUserInput.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(splitUserInput[1]);
                tasks[taskNumber - 1].markTaskAsUndone();
                System.out.println(tasks[taskNumber - 1]);
                System.out.println("____________________________________________________________");
            } else {
                switch (splitUserInput[0]) {
                case "todo":
                    addTask(new Todo(userInput));
                    break;
                case "deadline":
                    String taskDescription = extractDescription(userInput);
                    String date = extractDeadline(userInput);
                    addTask(new Deadline(taskDescription, date));
                    break;
                case "event":
                    taskDescription = extractDescription(userInput);
                    String duration = extractDuration(userInput);
                    addTask(new Event(taskDescription, duration));
                    break;
                }
                printTaskCount();
            }
            userInput = in.nextLine();
            lowerCaseUserInput = userInput.toLowerCase();
        }
        exit();
    }

    public static void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void addTask(Task t){
        tasks[taskCount] = t;
        taskCount++;
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
    }

    public static void printTaskCount() {
        System.out.println("Now you have " + taskCount + " remaining task(s) in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void list() {
        if (taskCount == 0) {
            System.out.println("You have not entered any tasks yet!");
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.print((i+1) + ".");
                System.out.println(tasks[i]);
            }
        }
    }

    public static String extractDeadline(String s) {
        int index = s.indexOf("/by");
        return s.substring(index+4);
    }

    public static String extractDuration(String s) {
        int index = s.indexOf("/at");
        return s.substring(index+4);
    }

    public static String extractDescription(String s) {
        int startIndex = s. indexOf(' ');
        int endIndex = s.indexOf('/');
        return s.substring(startIndex+1, endIndex-1);
    }

    public static void exit() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

}
