package commands;

import taskmanager.TaskManager;
import storage.FileEditor;
import ui.Ui;

/**
 * Represents the user's command to list all tasks in his/her existing task list
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public ListCommand() {

    }


    /**
     * Displays all tasks in the user's task list as well as the count of the number of tasks
     *
     * @param taskManager Manages the user's task list
     * @param fileEditor Reads and writes from and to the user's task list file in the user's hard disk
     */
    @Override
    public void execute(TaskManager taskManager, FileEditor fileEditor) {
        if (taskManager.getTaskCount() == 0) {
            System.out.println(Ui.EMPTY_TASK_LIST_MESSAGE);
        } else {
            System.out.println(Ui.DISPLAY_TASKS_MESSAGE);
            for (int i = 0; i < taskManager.getTaskCount(); i++) {
                System.out.print((i+1) + ".");
                System.out.println(taskManager.getTasks().get(i));
            }
            Ui.showTaskCount(taskManager);
        }
        Ui.showLine();
    }
}
