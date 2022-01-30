import java.util.Scanner;

public class Duke {
    private static void parseInput(Scanner in, Task[] tasks) {
        String line;
        System.out.println("Storing Up to 100 Tasks");
        do {
            System.out.println("Waiting for your input");
            line = in.nextLine();
            String[] words = line.toLowerCase().split(" ", 2);
            String command = words[0];
            int taskNumber = 0;
            int numOfTasks = Task.getNumOfTasks();

            switch (command) {
            case "list":
                printTasks(tasks);
                break;
            case "mark":
                markTask(tasks, words, taskNumber);
                break;
            case "unmark":
                unmarkTask(tasks, words, taskNumber);
                break;
            case "todo":
                try {
                    tasks[numOfTasks] = new ToDo(words[1]);
                    System.out.println("Todo added!");
                    printTasks(tasks);
                } catch (ArrayIndexOutOfBoundsException oobe) {
                    System.out.println("Please use the correct format. e.g. todo <description>");
                }
                break;
            case "deadline":
                try {
                    tasks[numOfTasks] = new Deadline(extractDescription(words[1]), extractDate(words[1]));
                    System.out.println("Deadline added!");
                    printTasks(tasks);
                } catch (ArrayIndexOutOfBoundsException oobe) {
                    System.out.println("Please use the correct format. e.g. deadline <description> /by <date>");
                }
                break;
            case "event":
                try {
                    tasks[numOfTasks] = new Event(extractDescription(words[1]), extractDate(words[1]));
                    System.out.println("Event added!");
                    printTasks(tasks);
                } catch (ArrayIndexOutOfBoundsException oobe) {
                    System.out.println("Please use the correct format. e.g. event <description> /at <date>");
                }
                break;
            default:
                if (!line.equalsIgnoreCase("bye")) {
                    tasks[numOfTasks] = new Task(line);
                    System.out.println("Task added!");
                    printTasks(tasks);
                }
                break;
            }
        } while ((!line.equalsIgnoreCase("bye")) && Task.getNumOfTasks() < 100);
    }

    private static void unmarkTask(Task[] tasks, String[] words, int taskNumber) {
        try {
            if (words.length == 2) {
                taskNumber = Integer.parseInt(words[1]);
            }
            if (taskNumber > Task.getNumOfTasks()) {
                System.out.println("You don't have that many tasks ><!");
                return;
            }
            tasks[taskNumber - 1].setUndone();
            System.out.println("I have marked the task as not done!");
            System.out.println(tasks[taskNumber - 1].getStatusIcon() + tasks[taskNumber - 1].getTaskDescription() + "\n");
        } catch (NumberFormatException nfe) {
            System.out.println("Please enter a number after unmark");
        }
    }

    private static void markTask(Task[] tasks, String[] words, int taskNumber) {
        try {
            if (words.length == 2) {
                taskNumber = Integer.parseInt(words[1]);
            }
            if (taskNumber > Task.getNumOfTasks()) {
                System.out.println("You don't have that many tasks ><!");
                return;
            }
            tasks[taskNumber - 1].setDone();
            System.out.println("I have marked the task as done!");
            System.out.println(tasks[taskNumber - 1].getStatusIcon() + tasks[taskNumber - 1].getTaskDescription() + "\n");
        } catch (NumberFormatException nfe) {
            System.out.println("Please enter a number after mark");
        }
    }

    private static void printTasks(Task[] tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.getNumOfTasks(); i += 1) {
            System.out.println(i + 1 + ". " + tasks[i]);
        }
        System.out.println("You have " + Task.getNumOfTasks() + " task(s) on the list.\n");
    }

    private static void printWelcomeMessage() {
        System.out.println("____________________________________________________________\n"
                + "Hello, nice to meet you. I'm Yae! (*^▽^*)\n"
                + "What can I do for you?\n"
                + "____________________________________________________________");
    }

    private static String extractDescription(String input) {
        String[] words = input.split("/", 2);
        return words[0];
    }

    private static String extractDate(String input) {
        String[] words = input.split("/", 2);
        words = words[1].split(" ", 2);
        return words[1];
    }

    public static void main(String[] args) {
        printWelcomeMessage();
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
        parseInput(in, tasks);
        System.out.println("Goodbye, see you next time!");
    }

}
