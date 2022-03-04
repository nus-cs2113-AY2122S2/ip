package duke.tasklist;

import duke.DukeException;
import duke.tasklist.task.Task;

import static duke.parser.TaskString.parseTask;
import static duke.parser.TaskString.parseTaskNum;
import static duke.tasklist.TaskListUtil.indInTaskList;
import static duke.ui.CommandLineOutputUtil.printFormat;

/**
 * Methods for adding and removing from task list.
 */
public class TaskListAddRemove {
    /**
     * Removes a task from the task list.
     *
     * @param line String of user input to parse.
     */
    public static void removeTask(String line) {
        Task curr;
        try {
            int taskInd = parseTaskNum(line);
            if (!indInTaskList(taskInd)) {
                throw new DukeException("Please delete a task number that's in the list :')");
            }
            curr = TaskListUtil.list.get(taskInd);
            TaskListUtil.list.remove(taskInd);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            printFormat("You can only delete with a valid task number :')");
            return;
        } catch (DukeException e) {
            printFormat(e.getMessage());
            return;
        }
        printFormat("Noted. I've removed this task:\n  " + curr +
                String.format("\nNow you have %d tasks in the list.", TaskListUtil.list.size()));
    }

    /**
     * Adds a task to the task list.
     *
     * @param line String of user input to parse.
     */
    public static void addTask(String line) {
        try {
            String[] commands = line.split(" ", 2);
            String type = commands[0];
            String description = "";
            if (commands.length > 1) {
                description = commands[1];
            }

            Task task = parseTask(type, description);
            TaskListUtil.list.add(task);
            printFormat("Got it. I've added this task:\n  " + task +
                    String.format("\nNow you have %d tasks in the list.", TaskListUtil.list.size()));
        } catch (DukeException e) {
            printFormat(e.getMessage());
        }
    }
}
