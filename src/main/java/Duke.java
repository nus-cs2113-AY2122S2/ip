import java.io.IOException;

import commands.Command;
import parser.Parser;
import storage.FileEditor;
import ui.Ui;
import taskmanager.TaskManager;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user
 */
public class Duke {

    private FileEditor fileEditor;
    private TaskManager taskManager;
    private final Ui ui;

    /**
     * Sets up the required objects, loads the user's task list file from the user's hard disk
     *
     * @param fileName File name of the user's task list file
     * @param directoryName Directory where the user's task list file resides
     * */
    public Duke(String fileName, String directoryName) {
        ui = new Ui();
        fileEditor = new FileEditor(fileName, directoryName);
        try {
            taskManager = new TaskManager(fileEditor.readFileContents());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Greets the user and processes the user's inputs until the user issues the exit command
     * */
    public void run() {
        ui.showGreetingMessage();
        System.out.println(ui.COMMAND_MENU);
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine(); // show the divider line ("_______")
            Command c = Parser.parse(fullCommand);
            c.execute(taskManager, fileEditor);
            isExit = c.isExit();
        }
    }

    public static void main(String[] args) {
        new Duke("tasks.txt", "data").run();
    }
}