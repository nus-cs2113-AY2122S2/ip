package commands;

import exception.DukeException;
import taskmanager.TaskManager;
import storage.FileEditor;
import ui.Ui;
import tasks.Todo;
import tasks.Task;
import java.io.IOException;

public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    protected String taskDescription = "";

    public TodoCommand(String userInput) {
        try {
            taskDescription = extractDescription(userInput);
        } catch (DukeException e) {
            System.out.println(Ui.EMPTY_TODO_DESCRIPTION_MESSAGE);
            Ui.showLine();
        }
    }

    @Override
    public void execute(TaskManager taskManager, FileEditor fileEditor) {
        if (taskDescription.equals("")) {
            return;
        }
        Task t = new Todo(taskDescription);
        taskManager.addTask(t);
        System.out.println(Ui.ADDED_TASK_MESSAGE);
        System.out.println(t);
        Ui.showTaskCount(taskManager);
        Ui.showLine();
        try {
            fileEditor.updateFile(taskManager.getTasks());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
