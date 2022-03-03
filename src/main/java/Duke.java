import java.io.IOException;

import commands.Command;
import parser.Parser;
import storage.FileEditor;
import ui.Ui;
import taskmanager.TaskManager;

public class Duke {

    private FileEditor fileEditor;
    private TaskManager taskManager;
    private final Ui ui;

    public Duke(String fileName, String directoryName) {
        ui = new Ui();
        fileEditor = new FileEditor(fileName, directoryName);
        try {
            taskManager = new TaskManager(fileEditor.readFileContents());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        ui.showGreetingMessage();
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