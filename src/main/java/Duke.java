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
        String userInput;
        String lowerCaseUserInput;
        boolean isLastInput;
        boolean isListRequest;
        boolean isMarkRequest;
        boolean isUnmarkRequest;
        String taskType;
        do {
            userInput = in.nextLine().trim();
            lowerCaseUserInput = userInput.toLowerCase();
            String[] splitUserInput = lowerCaseUserInput.split(" ");
            isLastInput = detectLastInput(lowerCaseUserInput);
            isListRequest = detectListRequest(lowerCaseUserInput);
            isMarkRequest = detectMarkRequest(lowerCaseUserInput);
            isUnmarkRequest = detectUnmarkRequest(lowerCaseUserInput);
            if (isListRequest) {
                list();
            } else if (isMarkRequest) {
                int taskNumber = Integer.parseInt(splitUserInput[1]);
                tasks[taskNumber - 1].markTaskAsDone();
                System.out.println(tasks[taskNumber - 1]);
                System.out.println("____________________________________________________________");
            } else if (isUnmarkRequest) {
                int taskNumber = Integer.parseInt(splitUserInput[1]);
                tasks[taskNumber - 1].markTaskAsUndone();
                System.out.println(tasks[taskNumber - 1]);
                System.out.println("____________________________________________________________");
            } else {
                taskType = splitUserInput[0];
                try {
                    checkInputValidity(taskType);
                } catch(DukeException e) {
                    System.out.println("____________________________________________________________\n");
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-( Please enter something else!");
                    System.out.println("____________________________________________________________\n");
                    continue;
                }
                String taskDescription;
                try {
                    taskDescription = extractDescription(userInput);
                } catch (DukeException e) {
                    System.out.println("____________________________________________________________");
                    System.out.println("☹ OOPS!!! The description of a " + taskType + " cannot be empty.");
                    System.out.println("____________________________________________________________");
                    continue;
                }
                switch (taskType) {
                case "todo":
                    addTask(new Todo(taskDescription));
                    break;
                case "deadline":
                    String date;
                    try {
                        date = extractDeadline(userInput);
                    } catch (DukeException e) {
                        System.out.println("____________________________________________________________");
                        System.out.println("☹ OOPS!!! Please enter a deadline for your task!");
                        System.out.println("____________________________________________________________");
                        continue;
                    }
                    addTask(new Deadline(taskDescription, date));
                    break;
                case "event":
                    String duration;
                    try {
                        duration = extractDuration(userInput);
                    } catch (DukeException e) {
                        System.out.println("____________________________________________________________");
                        System.out.println("☹ OOPS!!! Please enter a duration for your task!");
                        System.out.println("____________________________________________________________");
                        continue;
                    }
                    addTask(new Event(taskDescription, duration));
                    break;
                }
            }
        } while(!isLastInput);
        exit();
    }

    private static String extractDescription(String userInput) throws DukeException {
        int startIndex = userInput.indexOf(" ");
        if (startIndex == -1) {
            throw new DukeException();
        }
        int endIndex;
        if (userInput.contains("/")) {
            endIndex = userInput.indexOf("/");
        } else {
            endIndex = userInput.length();
        }
        String description = userInput.substring(startIndex+1, endIndex).trim();
        if (description.equals("")) {
            throw new DukeException();
        }
        return description;
    }

    private static void checkInputValidity(String taskType) throws DukeException {
        switch (taskType) {
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            // Fallthrough
            break;
        default:
            throw new DukeException();
        }
    }

    public static boolean detectLastInput(String input) {
        return input.equals("bye");
    }

    public static boolean detectListRequest(String input) {
        return input.equals("list");
    }

    public static boolean detectMarkRequest(String input) {
        return input.startsWith("mark");
    }

    public static boolean detectUnmarkRequest(String input) {
        return input.startsWith("unmark");
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
        System.out.println("Now you have " + taskCount + " task(s) in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void list() {
        if (taskCount == 0) {
            System.out.println("You have not entered any tasks yet!");
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("Here are the task(s) in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.print((i+1) + ".");
                System.out.println(tasks[i]);
            }
        }
    }

    public static String extractDeadline(String s) throws DukeException {
        if (s.contains("/by")) {
            int startIndex = s.indexOf("/by");
            int endIndex = s.length() - 1;
            if (endIndex - startIndex <= 2) {
                throw new DukeException();
            }
            return s.substring(startIndex+4);
        } else {
            throw new DukeException();
        }
    }

    public static String extractDuration(String s) throws DukeException {
        if (s.contains("/at")) {
            int startIndex = s.indexOf("/at");
            int endIndex = s.length() - 1;
            if (endIndex - startIndex <= 2) {
                throw new DukeException();
            }
            return s.substring(startIndex+4);
        } else {
            throw new DukeException();
        }
    }

    public static void exit() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

}
