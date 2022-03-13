package Duke;

import Duke.Exception.EmptyDescriptionException;
import Duke.Storage.Storage;
import Duke.Task.TaskList;
import Duke.Ui.Ui;

import java.io.IOException;

/**
 * Represents the entry-point of Duke, task list manager bot
 */
public class Duke {

    private TaskList tasks;
    private final Ui ui;
    private final Storage storage;

    /**
     * initializes Duke interface and stored list
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();

    }

    /**
     * 
     * @throws IOException fpr failed or interrupted I/O operations.
     * 
     */
    public void run() throws IOException {
        storage.loadTaskList();
        ui.printWelcomeMessage();

        while (true) {
            String input = ui.readInput();
            Command c = new Command(input);
            c.executeCommand();
            if (c.getCommand().equals("bye")) {
                ui.printGoodByeMessage();
                storage.storeFile();
                break;
            }

        }
    }

    public static void main(String[] args) throws IOException {
        new Duke().run();

    }
}
