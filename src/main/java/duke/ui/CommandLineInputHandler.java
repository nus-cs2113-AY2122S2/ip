package duke.ui;

import duke.tasklist.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

import static duke.storage.ReadTaskList.readFile;
import static duke.storage.WriteTaskList.writeList;
import static duke.tasklist.TaskListAddRemove.addTask;
import static duke.tasklist.TaskListAddRemove.removeTask;
import static duke.tasklist.TaskListEdit.markStatus;
import static duke.tasklist.TaskListUtil.find;
import static duke.tasklist.TaskListUtil.list;
import static duke.ui.CommandLineOutputUtil.bye;
import static duke.ui.CommandLineOutputUtil.greet;

/**
 * Methods for obtaining and parsing user input.
 */
public class CommandLineInputHandler {
    private static Boolean willExit = false;

    private static void parseCommands(String line) {
        if (line.equals("bye")) {
            willExit = true;
            bye();
        } else if (line.equals("list")) {
            list();
        } else if (line.startsWith("delete")) {
            removeTask(line);
        } else if (line.startsWith("find")) {
            find(line);
        } else if (line.startsWith("mark")) {
            markStatus(true, line);
        } else if (line.startsWith("unmark")) {
            markStatus(false, line);
        } else {
            addTask(line);
        }
    }

    /**
     * Runs the Duke chat-bot.
     * Reads data file, receives user input and calls parsing method, and writes to data file.
     */
    public static void runDuke() {
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
