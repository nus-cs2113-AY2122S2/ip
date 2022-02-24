package duke;

import java.util.ArrayList;

import duke.Parser.TaskString;
import duke.task.Task;

import java.util.Scanner;

import static duke.Parser.TaskString.parseTask;
import static duke.Storage.ReadTaskList.readFile;
import static duke.Storage.WriteTaskList.writeList;

/**
 * Runs the Duke chat-bot which takes in user input,
 * and performs certain actions for specific commands.
 */
public class Duke {
    private static final int MAX_SIZE = 100;
    private static final String MESSAGE_BORDER =
            "____________________________________________________________";

    private static final ArrayList<Task> list = new ArrayList<>(MAX_SIZE);
    private static Boolean willExit = false;

    private static void printFormat(String message) {
        System.out.println(MESSAGE_BORDER);
        System.out.println(message);
        System.out.println(MESSAGE_BORDER);
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
        if (list.size() == 0) {
            printFormat("You haven't added any tasks to your list yet!");
            return;
        }

        String listAsString = "";
        for (int i = 0; i < list.size(); i++) {
            Task curr = list.get(i);
            listAsString = listAsString.concat(String.format(" %d. %s\n", i + 1, curr));
        }
        printFormat("Here are the tasks in your list:\n" + listAsString);
    }

    private static void markStatus(Boolean shouldMark, String line) {
        Task curr;
        try {
            String stringOfTaskNum = line.split(" ", 0)[1];
            int taskInd = Integer.parseInt(stringOfTaskNum) - 1;
            if (taskInd > list.size()) {
                throw new DukeException("Please mark / unmark with a number that's in the list :')");
            }
            curr = list.get(taskInd);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            // NumberFormatException caught in IntelliJ runtime env but not in testing...
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

    private static void removeTask(String line) {
        Task curr;
        try {
            int taskNum = Integer.parseInt(line.split(" ", 0)[1]) - 1;
            if (taskNum > list.size()) {
                throw new DukeException("Please delete a task number that's in the list :')");
            }
            curr = list.get(taskNum);
            list.remove(taskNum);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            printFormat("You can only delete with a valid task number :')");
            return;
        } catch (DukeException e) {
            printFormat(e.msg);
            return;
        }

        printFormat("Noted. I've removed this task: \n  " + curr +
                String.format("\nNow you have %d tasks in the list.", list.size()));
    }

    private static void addTask(String line) {
        try {
            String[] commands = line.split(" ", 2);
            String type = commands[0];
            String description = "";
            if (commands.length > 1) {
                description = commands[1];
            }

            Task task = parseTask(type, description);
            list.add(task);
            printFormat("Got it. I've added this task:\n  " + task +
                    String.format("\nNow you have %d tasks in the list.", list.size()));
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
        } else if (line.startsWith("delete")) {
            removeTask(line);
        } else {
            addTask(line);
        }
    }

    public static void main(String[] args) {
        greet();
        String line;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> existingTasks = readFile();
        list.addAll(existingTasks);

        while (!willExit) {
            line = in.nextLine();
            parseCommands(line);
        }

        writeList(list);
    }
}
