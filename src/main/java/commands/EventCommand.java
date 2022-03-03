package commands;

import exception.DukeException;
import taskmanager.TaskManager;
import storage.FileEditor;
import ui.Ui;
import tasks.Event;
import tasks.Task;
import java.io.IOException;

public class EventCommand extends Command {

    protected String taskDescription = "";
    protected String duration = "";
    public static final String COMMAND_WORD = "event";

    private static String extractDuration(String s) throws DukeException {
        if (s.contains("/at")) {
            int startIndex = s.indexOf("/at");
            int endIndex = s.length() - 1;
            if (endIndex - startIndex <= 2) {
                throw new DukeException();
            }
            return s.substring(startIndex+4);
        } else {
            throw new DukeException();
        }
    }

    public EventCommand(String userInput) {
        try {
            taskDescription = extractDescription(userInput);
        } catch (DukeException e) {
            System.out.println(Ui.EMPTY_EVENT_DESCRIPTION_MESSAGE);
            Ui.showLine();
        }
        try {
            duration = extractDuration(userInput);
        } catch (DukeException e) {
            System.out.println(Ui.EMPTY_DURATION_MESSAGE);
            Ui.showLine();
        }
    }

    @Override
    public void execute(TaskManager taskManager, FileEditor fileEditor) {
        if (taskDescription.equals("") || duration.equals("")) {
            return;
        }
        Task t = new Event(taskDescription, duration);
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
