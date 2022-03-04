import boba.command.Command;
import boba.parser.Parser;
import boba.response.Ui;
import boba.data.Storage;
import boba.exception.BobaException;
import boba.task.TaskList;

/**
 * A chat-bot that manages tasks for the user.
 * Number of tasks are limited to 100.
 */
public class Boba {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Boba(String filePath) {
        ui = new Ui();
        ui.giveIntroduction();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (BobaException e) {
                ui.printInvalidCommand(e.getMessage());
            }
        }
    }

    /**
     * Main method that runs our chat-bot. Handling commands until user exits.
     */
    public static void main(String[] args) {
        new Boba("data/boba.txt").run();
    }

}
