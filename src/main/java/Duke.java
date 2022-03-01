import commands.Command;
import common.DukeException;
import data.TaskManager;
import parser.Parser;
import storage.FileManager;
import ui.Ui;

import java.io.IOException;

public class Duke {
    private Ui ui;
    private TaskManager taskManager;
    private FileManager fileManager;

    public Duke() {
        fileManager = new FileManager();

        try {
            taskManager = new TaskManager(fileManager.loadData());
        } catch (IOException e) {
            ui.showLoadingError(e.getMessage());
            taskManager = new TaskManager();
        }

        ui = new Ui(taskManager);
    }

    public void run() {
        ui.showGreetingMessage();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getUserCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(taskManager, fileManager, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

        ui.showExitMessage();
    }

    public static void main(String[] args) {
        new Duke().run();
    }


}
