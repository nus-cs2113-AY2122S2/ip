package brave;

import brave.data.TaskManager;
import brave.parser.Parser;
import brave.ui.Ui;
import brave.storage.Storage;

import java.io.IOException;
import java.lang.String;
import java.nio.file.Path;


/**
 * Entry point for Brave-Bot Task Helper
 * Initialize the UI, TaskManager, and Storage component.
 */
public class Brave {

    private final Ui ui;
    private TaskManager tasks;
    private final Storage storage;

    /**
     * Initialize the UI, loading the file data from storage
     *
     * @param filePath filepath for the program to read from
     */
    public Brave(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskManager(storage.load());
        } catch (IOException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskManager();
        }
    }

    /**
     * Read the user input command,
     * and keep iterating until stop condition is reached
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            String fullCommand = ui.getUserCommand();
            Parser p = new Parser(ui, storage);
            p.parse(fullCommand, tasks);
            isExit = p.getExit();
        }

        ui.showFarewellMessage();
    }

    public static void main(String[] args) {
        String DIR_PATH = String.valueOf(Path.of(System.getProperty("user.dir"), "data"));
        new Brave(DIR_PATH + "/brave.txt").run();
    }
}
