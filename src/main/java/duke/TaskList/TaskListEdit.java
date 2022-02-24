package duke.TaskList;

import duke.Ui.CommandLineOutputUtil;
import duke.DukeException;
import duke.TaskList.task.Task;

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
            String stringOfTaskNum = line.split(" ", 0)[1];
            int taskInd = Integer.parseInt(stringOfTaskNum) - 1;
            if (taskInd > TaskListInit.list.size()) {
                throw new DukeException("Please mark / unmark with a number that's in the list :')");
            }
            curr = TaskListInit.list.get(taskInd);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            // NumberFormatException caught in IntelliJ runtime env but not in testing...
            CommandLineOutputUtil.printFormat("Please mark / unmark with a valid number :')");
            return null;
        } catch (DukeException e) {
            CommandLineOutputUtil.printFormat(e.msg);
            return null;
        }
        return curr;
    }
}
