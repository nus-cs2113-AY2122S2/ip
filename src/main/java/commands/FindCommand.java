package commands;

import common.DukeException;
import data.Task;
import data.TaskManager;
import storage.FileManager;
import ui.Ui;

import java.util.ArrayList;

/**
 * Command to find and list all tasks whose description contains the specified keyword.
 */
public class FindCommand extends Command{
    public static final String COMMAND_WORD = "find";
    private String keyword;

    public FindCommand (String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskManager taskManager, FileManager fileManager, Ui ui) {
        try {
            ArrayList<Integer> results = taskManager.findTask(keyword);
            ui.showFoundResults(results);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}
