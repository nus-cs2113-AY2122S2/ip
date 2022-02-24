package duke.TaskList;

import duke.Ui.CommandLineOutputUtil;
import duke.TaskList.task.Task;

import java.util.ArrayList;

public class TaskListInit {
    private static final int MAX_SIZE = 100;
    public static final ArrayList<Task> list = new ArrayList<>(MAX_SIZE);

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
}
