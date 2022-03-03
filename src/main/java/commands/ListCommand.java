package commands;

import taskmanager.TaskManager;
import storage.FileEditor;
import ui.Ui;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public ListCommand() {

    }
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
