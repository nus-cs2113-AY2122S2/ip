package duke.ui;

import duke.tasklist.TaskListAddRemove;
import duke.tasklist.TaskListEdit;
import duke.tasklist.TaskListUtil;
import duke.tasklist.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

import static duke.storage.ReadTaskList.readFile;
import static duke.storage.WriteTaskList.writeList;
import static duke.ui.CommandLineOutputUtil.greet;

/**
 * Methods for obtaining and parsing user input.
 */
public class CommandLineInputHandler {
    private static Boolean willExit = false;

    private static void parseCommands(String line) {
        if (line.equals("bye")) {
            willExit = true;
            CommandLineOutputUtil.bye();
        } else if (line.equals("list")) {
            TaskListUtil.list();
        } else if (line.startsWith("delete")) {
            TaskListAddRemove.removeTask(line);
        } else if (line.startsWith("mark")) {
            TaskListEdit.markStatus(true, line);
        } else if (line.startsWith("unmark")) {
            TaskListEdit.markStatus(false, line);
        } else {
            TaskListAddRemove.addTask(line);
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
        TaskListUtil.list.addAll(existingTasks);

        while (!willExit) {
            line = in.nextLine();
            parseCommands(line);
        }

        writeList(TaskListUtil.list);
    }
}
