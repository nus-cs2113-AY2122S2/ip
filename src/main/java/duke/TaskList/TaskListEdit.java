package duke.TaskList;

import duke.DukeException;
import duke.Parser.TaskString;
import duke.TaskList.task.Task;
import duke.Ui.CommandLineOutputUtil;

public class TaskListEdit {
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

    private static Task getTask(String line) {
        Task curr;
        try {
            int taskInd = TaskString.parseTaskNum(line);
            if (taskInd > TaskListUtil.list.size()) {
                throw new DukeException("Please mark / unmark with a number that's in the list :')");
            }
            curr = TaskListUtil.list.get(taskInd);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            CommandLineOutputUtil.printFormat("Please mark / unmark with a valid number :')");
            return null;
        } catch (DukeException e) {
            CommandLineOutputUtil.printFormat(e.msg);
            return null;
        }
        return curr;
    }
}
