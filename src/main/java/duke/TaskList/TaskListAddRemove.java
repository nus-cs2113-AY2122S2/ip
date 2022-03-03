package duke.TaskList;

import duke.Ui.CommandLineOutputUtil;
import duke.DukeException;
import duke.TaskList.task.Task;

import static duke.Parser.TaskString.parseTask;

public class TaskListAddRemove {
    public static void removeTask(String line) {
        Task curr;
        try {
            int taskNum = Integer.parseInt(line.split(" ", 0)[1]) - 1;
            if (taskNum > TaskListInit.list.size()) {
                throw new DukeException("Please delete a task number that's in the list :')");
            }
            curr = TaskListInit.list.get(taskNum);
            TaskListInit.list.remove(taskNum);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            CommandLineOutputUtil.printFormat("You can only delete with a valid task number :')");
            return;
        } catch (DukeException e) {
            CommandLineOutputUtil.printFormat(e.msg);
            return;
        }
        CommandLineOutputUtil.printFormat("Noted. I've removed this task:\n  " + curr +
                String.format("\nNow you have %d tasks in the list.", TaskListInit.list.size()));
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
            TaskListInit.list.add(task);
            CommandLineOutputUtil.printFormat("Got it. I've added this task:\n  " + task +
                    String.format("\nNow you have %d tasks in the list.", TaskListInit.list.size()));
        } catch (DukeException e) {
            CommandLineOutputUtil.printFormat(e.msg);
        }
    }
}
