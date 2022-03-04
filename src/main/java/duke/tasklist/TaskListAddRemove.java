package duke.tasklist;

import duke.parser.TaskString;
import duke.ui.CommandLineOutputUtil;
import duke.DukeException;
import duke.tasklist.task.Task;

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
            int taskInd = TaskString.parseTaskNum(line);
            if (!TaskListUtil.indInTaskList(taskInd)) {
                throw new DukeException("Please delete a task number that's in the list :')");
            }
            curr = TaskListUtil.list.get(taskInd);
            TaskListUtil.list.remove(taskInd);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            CommandLineOutputUtil.printFormat("You can only delete with a valid task number :')");
            return;
        } catch (DukeException e) {
            CommandLineOutputUtil.printFormat(e.getMessage());
            return;
        }
        CommandLineOutputUtil.printFormat("Noted. I've removed this task:\n  " + curr +
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

            Task task = TaskString.parseTask(type, description);
            TaskListUtil.list.add(task);
            CommandLineOutputUtil.printFormat("Got it. I've added this task:\n  " + task +
                    String.format("\nNow you have %d tasks in the list.", TaskListUtil.list.size()));
        } catch (DukeException e) {
            CommandLineOutputUtil.printFormat(e.getMessage());
        }
    }
}
