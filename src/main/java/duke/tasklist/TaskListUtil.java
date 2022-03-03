package duke.tasklist;

import duke.ui.CommandLineOutputUtil;
import duke.tasklist.task.Task;

import java.util.ArrayList;

public class TaskListUtil {
    private static final int MAX_SIZE = 100;
    public static final ArrayList<Task> list = new ArrayList<>(MAX_SIZE);

    static boolean indInTaskList (int taskInd) {
        if (taskInd >= TaskListUtil.list.size()) {
            return false;
        } else {
            return true;
        }
    }

    public static void list() {
        if (list.size() == 0) {
            CommandLineOutputUtil.printFormat("You haven't added any tasks to your list yet!");
            return;
        }

        String listAsString = "";
        for (int i = 0; i < list.size(); i++) {
            Task curr = list.get(i);
            listAsString = listAsString.concat(String.format(" %d. %s\n", i + 1, curr));
        }
        CommandLineOutputUtil.printFormat("Here are the tasks in your list:\n" + listAsString);
    }

    public static void find(String line) {
        String[] commands = line.split(" ");
        if (commands.length > 2) {
            CommandLineOutputUtil.printFormat("Please only enter one keyword!");
            return;
        }
        String keyword = commands[1];

        String listAsString = "";
        int taskNum = 1;
        for (int i = 0; i < list.size(); i++) {
            Task curr = list.get(i);
            String taskDescription = curr.getDescription();
            if (taskDescription.contains(keyword)) {
                listAsString = listAsString.concat(String.format(" %d. %s\n", taskNum, curr));
                taskNum++;
            }
        }
        CommandLineOutputUtil.printFormat("Here are the matching tasks in your list:\n" + listAsString);
    }
}
