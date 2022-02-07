import java.util.Locale;
import java.util.Scanner;

public class Duke {

    public static final int MAX_TASKS = 100;
    public static final String BORDER_DECORATION = "_____________________________________________________";
    private static Task[] taskLists = new Task[MAX_TASKS];

    public static void displayGreeting() {
        String logo = " _______     __   __  \n"
                + "|   _   |[x]| | / / [x]  \n"
                + "|  | |  || ||  / /  | |\n"
                + "|  | |  || ||  <    | |\n"
                + "|__/ |__||_||_| |_| |_|\n";
        System.out.println("Hello from\n" + logo +"\n" + BORDER_DECORATION);
        System.out.println("Hello! I'm Niki\nWhat can I do for you?\n" + BORDER_DECORATION);
    }

    public static void printList() {
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            System.out.println((i + 1) + "."+ taskLists[i].toString());
        }
    }

    public static void addTask(String userInput) throws DukeEmptyDescriptionException, DukeMaxTaskException {

        if (Task.getNumberOfTasks() >= MAX_TASKS) {
            throw new DukeMaxTaskException();
        }

        String[] arrayOfTaskStrings = userInput.split(" ");
        if (arrayOfTaskStrings.length <= 1) {
            throw new DukeEmptyDescriptionException();
        }

        String extractTaskDescription = "";
        for (int i = 1; i < arrayOfTaskStrings.length; i++) {
            extractTaskDescription += arrayOfTaskStrings[i] + " ";
        }

        Task newTask = new Todo(extractTaskDescription);
        taskLists[Task.getNumberOfTasks() - 1] = newTask;
        System.out.println("Task added:\n\t" + newTask.toString());
        System.out.println("Now you have " + Task.getNumberOfTasks() + " tasks in your list!");
    }

    public static void addTaskWithTime(String userInput, String stringSeparator) throws DukeEmptyDescriptionException, DukeMaxTaskException {
        if (Task.getNumberOfTasks() >= MAX_TASKS) {
            throw new DukeMaxTaskException();
        }

        String[] arrayOfTaskStrings = userInput.split(" ");
        if (arrayOfTaskStrings.length <= 1) {
            throw new DukeEmptyDescriptionException();
        }

        String extractStringsWithoutCommand = "";
        for (int i = 1; i < arrayOfTaskStrings.length; i++) {
            extractStringsWithoutCommand += arrayOfTaskStrings[i] + " ";
        }

        String extractTaskDescription = extractStringsWithoutCommand.split(stringSeparator)[0];
        String extractTaskDeadlineTime = extractStringsWithoutCommand.split(stringSeparator)[1];
        Task newTask;
        if (stringSeparator.equals("/by ")) {
            newTask = new Deadline(extractTaskDescription, extractTaskDeadlineTime);
        } else {
            newTask = new Event(extractTaskDescription, extractTaskDeadlineTime);
        }

        taskLists[Task.getNumberOfTasks() - 1] = newTask;
        System.out.println("Task added:\n\t" + newTask.toString());
        System.out.println("Now you have " + Task.getNumberOfTasks() + " tasks in your list!");
    }

    public static boolean isWithinTaskRange(int taskNumber) {
        if (taskNumber > Task.getNumberOfTasks() || taskNumber <= 0) {
            System.out.println("Task does not exist!");
            return false;
        }
        return true;
    }

    public static void markTask(boolean isMarked, String userInput) throws DukeEmptyDescriptionException, NumberFormatException {
        if ((userInput.split(" ")).length <= 1) {
            throw new DukeEmptyDescriptionException();
        }

        int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
        if (isWithinTaskRange(taskNumber)) {
            if (isMarked) {
                taskLists[taskNumber - 1].markAsDone();
                System.out.println("Fantastic! This task is done:");
            } else {
                taskLists[taskNumber - 1].markAsUndone();
                System.out.println("Uh oh! This task is undone:");
            }
            System.out.println(taskLists[taskNumber - 1].toString());
        }
    }

    private static void printExit() {
        System.out.println(BORDER_DECORATION);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(BORDER_DECORATION);
    }

    public static void executeCommand() {
        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();

        while (!userInput.equalsIgnoreCase("bye")) {
            System.out.println(BORDER_DECORATION);
            String command = userInput.split(" ")[0];

            switch (command) {
            case "list":
                printList();
                break;
            case "todo":
                try {
                    addTask(userInput);
                } catch (DukeEmptyDescriptionException e) {
                    System.out.println("OOPS! The description of a todo cannot be empty!");
                } catch (DukeMaxTaskException e) {
                    System.out.println("OOPS! You have reached the max number of tasks!");
                }
                break;
            case "deadline":
                try {
                    addTaskWithTime(userInput, "/by ");
                } catch (DukeEmptyDescriptionException e) {
                    System.out.println("OOPS! The description of a deadline cannot be empty!");
                } catch (DukeMaxTaskException e) {
                    System.out.println("OOPS! You have reached the max number of tasks!");
                }
                break;
            case "event":
                try {
                    addTaskWithTime(userInput,"/at ");
                } catch (DukeEmptyDescriptionException e) {
                    System.out.println("OOPS! The description of a event cannot be empty!");
                } catch (DukeMaxTaskException e) {
                    System.out.println("OOPS! You have reached the max number of tasks!");
                }
                break;
            case "unmark":
                try {
                    markTask(false, userInput);
                } catch (DukeEmptyDescriptionException e) {
                    System.out.println("OOPS! Please add the list number you want to unmark!");
                } catch (NumberFormatException e) {
                    System.out.println("OOPS! Specify a number for the list to unmark!");
                }
                break;
            case "mark":
                try {
                    markTask(true, userInput);
                } catch (DukeEmptyDescriptionException e) {
                    System.out.println("OOPS! Please add the list number you want to mark!");
                } catch (NumberFormatException e) {
                    System.out.println("OOPS! Specify a number for the list to mark!");
                }
                break;
            default:
                System.out.println("I'm sorry, but I don't know what that means :(");
            }
            System.out.println(BORDER_DECORATION);
            userInput = in.nextLine();
        }
    }

    public static void main(String[] args) {
        displayGreeting();
        executeCommand();
        printExit();
    }
}
