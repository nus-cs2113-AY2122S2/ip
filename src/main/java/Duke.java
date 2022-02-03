import java.util.Scanner;

public class Duke {
    private static Task[] list = new Task[100];
    private static int taskIndex = 0;
    private static Boolean willExit = false;

    public static void addTask(String line) {
        try {
            String[] commands = line.split(" ", 2);
            String type = commands[0];
            String description = commands[1];
            Task t;

            switch (type) {
            case "todo":
                t = new Todo(description);
                break;
            case "deadline":
                String[] deadlineBreakdown = description.split("/by", 2);
                description = deadlineBreakdown[0];
                String by = deadlineBreakdown[1];
                t = new Deadline(description, by);
                break;
            case "event":
                String[] eventBreakdown = description.split(" /at ", 2);
                description = eventBreakdown[0];
                String at = eventBreakdown[1];
                t = new Event(description, at);
                break;
            default:
                // can set t to null because will catch this exception
                t = null;
            }
            list[taskIndex] = t;
            taskIndex++;
            printFormat("Got it. I've added this task:\n  " + t.toString() +
                    String.format("\nNow you have %d tasks in the list.", taskIndex));
        } catch (Exception e){
            printFormat("I don't understand what you want to do.\n" +
                    "Maybe you could try the following commands:\n" +
                    "  - list: list out existing tasks\n" +
                    "  - etc.");
        }
    }

    public static void parseCommands(String line) {
        switch (line) {
        case "bye":
            willExit = true;
            printFormat(" Aw, are you leaving now?\n" +
                    " Hope to see you again soon!");
            break;
        case "list":
            String listAsString = "";
            if (taskIndex == 0) {
                printFormat("You haven't added any tasks to your list yet!");
            } else {
                for (int i = 0; i < taskIndex; i++) {
                    Task curr = list[i];
                    listAsString += (" " + Integer.toString(i + 1) + ". " + curr.toString() + "\n");
                }
                printFormat("Here are the tasks in your list:\n" + listAsString);
            }
            break;
        default:
            if (line.contains("unmark")) {
                markStatus(false, line, list);
            } else if (line.contains("mark")) {
                markStatus(true, line, list);
            } else {
                addTask(line);
            }
        }
    }

    public static void printFormat(String s) {
        System.out.println("____________________________________________________________\n" +
                s + "\n" +
                "____________________________________________________________");
    }

    public static void markStatus(Boolean shouldMark, String line, Task[] list) {
        try {
            int taskNum = Integer.parseInt(line.split(" ", 0)[1]);
            Task curr = list[taskNum - 1];
            if (shouldMark) {
                curr.setDone(true);
                printFormat("Nice! I've marked this task as done:\n  " + curr.toString());
            } else {
                curr.setDone(false);
                printFormat("OK, I've marked this task as not done yet:\n  " + curr.toString());
            }
        } catch (Exception exception) {
            printFormat("Please mark / unmark a number that's in the list :')");
        }
    }

    public static void main(String[] args) {
        printFormat(" Hey there! I'm Duke\n" +
                " What can I do for you? uwu");

        String line;
        Scanner in = new Scanner(System.in);

        while (!willExit) {
            line = in.nextLine();
            parseCommands(line);
        }
    }
}
