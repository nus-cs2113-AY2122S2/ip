package Duke;

import Duke.Exception.EmptyDescriptionException;
import Duke.Storage.Storage;
import Duke.Task.TaskList;
import Duke.Ui.Ui;

import java.io.IOException;

/**
 *  Represents the entry-point of Duke.Duke Program.
 */
public class Duke {

    private TaskList tasks;
    private final Ui ui;
    private final Storage storage;

    /**
     *
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();

    }

    public void run() throws IOException, EmptyDescriptionException {
        storage.loadTaskList();
        ui.printWelcomeMessage();

        while (true){
            String input = ui.readInput();
            Command c = new Command(input);
            c.executeCommand();
            if (c.getCommand().equals("bye")){
                ui.printGoodByeMessage();
                storage.storeFile();
                break;
            }

        }
    }

    public static void main(String[] args) throws IOException, EmptyDescriptionException {
        new Duke().run();

    }
}
