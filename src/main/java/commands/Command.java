package commands;

import exception.DukeException;
import taskmanager.TaskManager;
import storage.FileEditor;
import java.io.IOException;

/**
 * Represents the function that the user requested in their input.
 */
public class Command {

    /**
     * Represents whether a command is an exit command
     * */
    protected boolean isExit;

    /**
     * By default, a command initialised as a non-exit command
     */
    public Command() {
        isExit = false;
    }

    /**
     * Returns whether a command is an exit command
     * */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Writes any changes to the user's task list to the user's hard disk
     *
     * @param taskManager Manages the user's task list
     * @param fileEditor Reads and writes from and to the user's task list file in the user's hard disk
     */
    public void execute(TaskManager taskManager, FileEditor fileEditor) {
        try {
            fileEditor.updateFile(taskManager.getTasks());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Extracts either task descriptions or search descriptions from the user's input
     *
     * @param userInput User's input
     * @return Substring representing either a task description or search description
     * @throws DukeException If no description is detected
     */
    protected static String extractDescription(String userInput) throws DukeException {
        int startIndex = userInput.indexOf(" ");
        if (startIndex == -1) {
            throw new DukeException();
        }
        int endIndex;
        if (userInput.contains("/")) {
            endIndex = userInput.indexOf("/");
        } else {
            endIndex = userInput.length();
        }
        String description = userInput.substring(startIndex+1, endIndex).trim();
        if (description.equals("")) {
            throw new DukeException();
        }
        return description;
    }

    protected static boolean checkTaskListSize() {
        if (TaskManager.getTaskCount() == 0) {
            return true;
        } else {
            return false;
        }
    }

}
