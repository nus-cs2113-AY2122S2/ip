package commands;

import exception.DukeException;
import taskmanager.TaskManager;
import storage.FileEditor;
import java.io.IOException;

public class Command {

    protected boolean isExit;

    public Command() {
        isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    public void execute(TaskManager taskManager, FileEditor fileEditor) {
        try {
            fileEditor.updateFile(taskManager.getTasks());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
