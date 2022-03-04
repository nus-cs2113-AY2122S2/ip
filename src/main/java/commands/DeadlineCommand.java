package commands;

import exception.DukeException;
import taskmanager.TaskManager;
import storage.FileEditor;
import ui.Ui;
import tasks.Deadline;
import tasks.Task;
import java.io.IOException;
import java.time.LocalDateTime;
import parser.Parser;

/**
 * Represents the user's command to create a new deadline task
 */
public class DeadlineCommand extends Command {

    protected String taskDescription = "";
    protected LocalDateTime dateTime = null;
    public static final String COMMAND_WORD = "deadline";

    /**
     * Extracts deadlines of the user's deadline tasks
     *
     * @param s User's input string
     * @return LocalDateTime representing the deadline of the user's deadline task
     * @throws DukeException If no deadline is detected
     */
    private static LocalDateTime extractDateTime(String s) throws DukeException {
        if (s.contains("/by")) {
            int startIndex = s.indexOf("/by");
            int endIndex = s.length() - 1;
            if (endIndex - startIndex <= 2) {
                throw new DukeException();
            }
            return Parser.parseDateTime(s.substring(startIndex+4).trim());
        } else {
            throw new DukeException();
        }
    }

    /**
     * Prepares the deadline task for creation by extracting the task description and deadline
     *
     * @param userInput User's input string
     */
    public DeadlineCommand(String userInput) {
        try {
            taskDescription = extractDescription(userInput);
        } catch (DukeException e) {
            System.out.println(Ui.EMPTY_DEADLINE_DESCRIPTION_MESSAGE);
            Ui.showLine();
        }
        try {
            dateTime = extractDateTime(userInput);
        } catch (DukeException e) {
            System.out.println(Ui.EMPTY_DEADLINE_MESSAGE);
            Ui.showLine();
        }
    }

    /**
     * Adds a deadline task to TaskManager.tasks
     * Writes the new deadline task to the user's task list file in the user's hard disk
     *
     * @param taskManager Manages the user's task list
     * @param fileEditor Reads and writes from and to the user's task list file in the user's hard disk
     */
    @Override
    public void execute(TaskManager taskManager, FileEditor fileEditor) {
        if (taskDescription.equals("")||dateTime == null) {
            return;
        }
        Task t = new Deadline(taskDescription, dateTime);
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

