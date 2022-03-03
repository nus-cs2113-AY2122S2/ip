package duke.TaskList;

import duke.Ui.CommandLineOutputUtil;
import duke.DukeException;
import duke.TaskList.task.Task;

import static duke.Parser.TaskString.parseTask;
import static duke.Parser.TaskString.parseTaskNum;

public class TaskListAddRemove {
    public static void removeTask(String line) {
        Task curr;
        try {
            int taskInd = parseTaskNum(line);
            if (taskInd > TaskListUtil.list.size()) {
                throw new DukeException("Please delete a task number that's in the list :')");
            }
            curr = TaskListUtil.list.get(taskInd);
            TaskListUtil.list.remove(taskInd);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            CommandLineOutputUtil.printFormat("You can only delete with a valid task number :')");
            return;
        } catch (DukeException e) {
            CommandLineOutputUtil.printFormat(e.msg);
            return;
        }
        CommandLineOutputUtil.printFormat("Noted. I've removed this task:\n  " + curr +
                String.format("\nNow you have %d tasks in the list.", TaskListUtil.list.size()));
    }

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
            CommandLineOutputUtil.printFormat("Got it. I've added this task:\n  " + task +
                    String.format("\nNow you have %d tasks in the list.", TaskListUtil.list.size()));
        } catch (DukeException e) {
            CommandLineOutputUtil.printFormat(e.msg);
        }
    }
}
