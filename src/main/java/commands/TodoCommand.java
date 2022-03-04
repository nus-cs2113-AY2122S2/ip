package commands;

import exception.DukeException;
import taskmanager.TaskManager;
import storage.FileEditor;
import ui.Ui;
import tasks.Todo;
import tasks.Task;
import java.io.IOException;

/**
 * Represents the user's command to create a new todo task
 */
public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    protected String taskDescription = "";

    /**
     * Prepares the todo task for creation by extracting the task description
     *
     * @param userInput User's input string
     */
    public TodoCommand(String userInput) {
        try {
            taskDescription = extractDescription(userInput);
        } catch (DukeException e) {
            System.out.println(Ui.EMPTY_TODO_DESCRIPTION_MESSAGE);
            Ui.showLine();
        }
    }

    /**
     * Adds a todo task to TaskManager.tasks
     * Writes the new todo task to the user's task list file in the user's hard disk
     * Displays the updated number of tasks in the user's task list
     *
     * @param taskManager Manages the user's task list
     * @param fileEditor Reads and writes from and to the user's task list file in the user's hard disk
     */
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
