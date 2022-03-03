package brave;

import brave.data.TaskManager;
import brave.parser.Parser;
import brave.ui.Ui;
import brave.storage.Storage;

import java.io.IOException;
import java.lang.String;

public class Brave {

    private final Ui ui;
    private TaskManager tasks;

    public Brave(String filePath) {
        ui = new Ui();
        Storage storage = new Storage(filePath);
        try {
            tasks = new TaskManager(storage.load());
        } catch (IOException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskManager();
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit;
        isExit = false;

        while (!isExit) {
            String fullCommand = ui.getUserCommand();
            Parser p = new Parser(ui);
            p.parse(fullCommand, tasks);
            isExit = p.getExit();
        }

        ui.showFarewellMessage();
    }

    public static void main(String[] args) {
        new Brave("data/brave.txt").run();
    }
}
