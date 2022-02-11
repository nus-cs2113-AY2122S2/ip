package duke;

import duke.ReadTaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

import static duke.ReadTaskList.readList;

/**
 * Runs the Duke chat-bot which takes in user input,
 * and performs certain actions for specific commands.
 */
public class Duke {
    private static final Task[] list = new Task[100];
    private static int taskIndex = 0;
    private static Boolean willExit = false;

    private static void printFormat(String s) {
        System.out.println("____________________________________________________________\n" +
                s + "\n" +
                "____________________________________________________________");
    }

    private static void greet() {
        printFormat(" Hey there! I'm Duke\n" +
                " What can I do for you? uwu");
    }

    private static void bye() {
        willExit = true;
        printFormat(" Aw, are you leaving now?\n" +
                " Hope to see you again soon!");
    }

    private static void list() {
        if (taskIndex == 0) {
            printFormat("You haven't added any tasks to your list yet!");
            return;
        }

        String listAsString = "";
        for (int i = 0; i < taskIndex; i++) {
            Task curr = list[i];
            listAsString = listAsString.concat(String.format(" %d. %s\n", i + 1, curr));
        }
        printFormat("Here are the tasks in your list:\n" + listAsString);
    }

    private static void markStatus(Boolean shouldMark, String line) {
        Task curr;
        try {
            int taskNum = Integer.parseInt(line.split(" ", 0)[1]);
            if (taskNum > taskIndex) {
                throw new DukeException("Please mark / unmark with a number that's in the list :')");
            }
            curr = list[taskNum - 1];
        } catch (IndexOutOfBoundsException e) {
            printFormat("Please mark / unmark with a valid number :')");
            return;
        } catch (DukeException e) {
            printFormat(e.msg);
            return;
        }

        if (shouldMark) {
            curr.setDone(true);
            printFormat("Nice! I've marked this task as done:\n  " + curr);
        } else {
            curr.setDone(false);
            printFormat("OK, I've marked this task as not done yet:\n  " + curr);
        }
    }

    private static Task parseDeadline(String description) throws DukeException {
        String by;
        try {
            String[] deadlineBreakdown = description.split("/by ", 2);
            description = deadlineBreakdown[0];
            by = deadlineBreakdown[1];
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("You need to provide a time for your deadline (e.g. /by 7am)");
        }
        return new Deadline(description, by);
    }

    private static Task parseEvent(String description) throws DukeException {
        String at;
        try {
            String[] eventBreakdown = description.split(" /at ", 2);
            description = eventBreakdown[0];
            at = eventBreakdown[1];
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("You need to provide a time for your event (e.g. /at 2-4pm)");
        }
        return new Event(description, at);
    }

    private static Task parseTask(String type, String description) throws DukeException {
        Task t;
        switch (type) {
        case "todo":
            t = new Todo(description);
            break;
        case "deadline":
            t = parseDeadline(description);
            break;
        case "event":
            t = parseEvent(description);
            break;
        default:
            throw new DukeException("I don't understand what you want to do, big sad :(");
        }

        if (description.equals("")) {
            throw new DukeException("Please provide a task description!");
        }
        return t;
    }

    private static void addTask(String line) {
        try {
            String[] commands = line.split(" ", 2);
            String type = commands[0];
            String description = "";
            if (commands.length > 1) {
                description = commands[1];
            }

            Task t = parseTask(type, description);
            list[taskIndex] = t;
            taskIndex++;
            printFormat("Got it. I've added this task:\n  " + t +
                    String.format("\nNow you have %d tasks in the list.", taskIndex));
        } catch (DukeException e) {
            printFormat(e.msg);
        }
    }

    private static void parseCommands(String line) {
        if (line.equals("bye")) {
            bye();
        } else if (line.equals("list")) {
            list();
        } else if (line.startsWith("mark")) {
            markStatus(true, line);
        } else if (line.startsWith("unmark")) {
            markStatus(false, line);
        } else {
            addTask(line);
        }
    }

    public static void main(String[] args) {
        greet();
        String line;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> existingTasks = readList();
        for (Task t : existingTasks) {
            list[taskIndex] = t;
            taskIndex++;
        }

        while (!willExit) {
            line = in.nextLine();
            parseCommands(line);
        }
    }
}
