package duke.Ui;

import duke.TaskList.TaskListAddRemove;
import duke.TaskList.TaskListEdit;
import duke.TaskList.TaskListInit;
import duke.TaskList.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

import static duke.Storage.ReadTaskList.readFile;
import static duke.Storage.WriteTaskList.writeList;

public class CommandLineInputHandler {
    static Boolean willExit = false;

    private static void parseCommands(String line) {
        if (line.equals("bye")) {
            willExit = true;
            CommandLineOutputUtil.bye();
        } else if (line.equals("list")) {
            TaskListInit.list();
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

    public static void runDuke() {
        String line;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> existingTasks = readFile();
        TaskListInit.list.addAll(existingTasks);

        while (!willExit) {
            line = in.nextLine();
            parseCommands(line);
        }

        writeList(TaskListInit.list);
    }
}
