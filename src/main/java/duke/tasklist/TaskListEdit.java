package duke.tasklist;

import duke.DukeException;
import duke.parser.TaskString;
import duke.tasklist.task.Task;
import duke.ui.CommandLineOutputUtil;

/**
 * Methods for making edits to the task list.
 */
public class TaskListEdit {
    private static Task getTask(String line) {
        Task curr;
        try {
            int taskInd = TaskString.parseTaskNum(line);
            if (!TaskListUtil.indInTaskList(taskInd)) {
                throw new DukeException("Please mark / unmark with a number that's in the list :')");
            }
            curr = TaskListUtil.list.get(taskInd);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            CommandLineOutputUtil.printFormat("Please mark / unmark with a valid number :')");
            return null;
        } catch (DukeException e) {
            CommandLineOutputUtil.printFormat(e.getMessage());
            return null;
        }
        return curr;
    }

    /**
     * Marks a task as "done" or "not done".
     *
     * @param shouldMark Boolean for whether a task should be marked as "done".
     * @param line String of user input.
     */
    public static void markStatus(Boolean shouldMark, String line) {
        Task curr = getTask(line);
        if (curr == null) return;

        if (shouldMark) {
            curr.setDone(true);
            CommandLineOutputUtil.printFormat("Nice! I've marked this task as done:\n  " + curr);
        } else {
            curr.setDone(false);
            CommandLineOutputUtil.printFormat("OK, I've marked this task as not done yet:\n  " + curr);
        }
    }
}
