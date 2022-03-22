package commands;

import exception.DukeException;
import taskmanager.TaskManager;
import storage.FileEditor;
import ui.Ui;
import tasks.Event;
import tasks.Task;
import java.io.IOException;

/**
 * Represents the user's command to create a new event task
 */
public class EventCommand extends Command {

    protected String taskDescription = "";
    protected String duration = "";
    public static final String COMMAND_WORD = "event";

    /**
     * Extracts duration of the user's event task
     *
     * @param s User's input string
     * @return Substring representing the duration of the user's event task
     * @throws DukeException If no duration is detected
     */
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

    /**
     * Prepares the event task for creation by extracting the task description and duration
     *
     * @param userInput User's input string
     */
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

    /**
     * Adds an event task to TaskManager.tasks
     * Writes the new event task to the user's task list file in the user's hard disk
     *
     * @param taskManager Manages the user's task list
     * @param fileEditor Reads and writes from and to the user's task list file in the user's hard disk
     */
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
