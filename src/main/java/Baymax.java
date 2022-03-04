import java.lang.String;

import java.io.IOException;

import baymax.data.TaskManager;
import baymax.ui.Ui;
import baymax.parse.Parser;
import baymax.storage.Storage;

/**
 * Enter point for Baymax task management bot
 * Initialize the task manager
 */
public class Baymax {

    /**
     * initialize UI, taskmanager, and storage.
     * @param filePath the txt file location where
     *                 the application read from and store in
     */
    private final Ui ui;
    private TaskManager tManager;

    public Baymax(String filePath) {
        ui = new Ui();
        Storage storage = new Storage(filePath);
        try {
            tManager = new TaskManager(storage.load());
        } catch (IOException e) {
            ui.showLoadingError(e.getMessage());
            tManager = new TaskManager();
        }
    }

    /**
     * Read user input
     * keep iteration until exit signal is sent
     * @throws IOException
     */
    public void run() throws IOException {
        ui.displayWelcomeMessage();
        boolean isBye;
        isBye = false;
        while (!isBye) {
            String fullCommand = ui.getUserCommand();
            Parser parse = new Parser(ui);
            parse.parse(fullCommand, tManager);
            isBye = parse.getExit();
        }
        ui.showByeMessage();
    }

    public static void main(String[] args) throws IOException {
        new Baymax("data/Baymax.txt").run();
    }
}


