package commands;

import exception.DukeException;
import taskmanager.TaskManager;
import storage.FileEditor;
import ui.Ui;
import tasks.Deadline;
import tasks.Task;
import java.io.IOException;

public class DeadlineCommand extends Command {

    protected String taskDescription = "";
    protected String deadline = "";
    public static final String COMMAND_WORD = "deadline";

    private static String extractDeadline(String s) throws DukeException {
        if (s.contains("/by")) {
            int startIndex = s.indexOf("/by");
            int endIndex = s.length() - 1;
            if (endIndex - startIndex <= 2) {
                throw new DukeException();
            }
            return s.substring(startIndex+4);
        } else {
            throw new DukeException();
        }
    }

    public DeadlineCommand(String userInput) {
        try {
            taskDescription = extractDescription(userInput);
        } catch (DukeException e) {
            System.out.println(Ui.EMPTY_DEADLINE_DESCRIPTION_MESSAGE);
            Ui.showLine();
        }
        try {
            deadline = extractDeadline(userInput);
        } catch (DukeException e) {
            System.out.println(Ui.EMPTY_DEADLINE_MESSAGE);
            Ui.showLine();
        }
    }

    @Override
    public void execute(TaskManager taskManager, FileEditor fileEditor) {
        if (taskDescription.equals("")||deadline.equals("")) {
            return;
        }
        Task t = new Deadline(taskDescription, deadline);
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

