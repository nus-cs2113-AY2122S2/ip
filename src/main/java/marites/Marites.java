package marites;

import marites.command.Command;
import marites.exception.EmptyTaskDescriptionException;
import marites.exception.MaritesException;
import marites.exception.MissingParameterException;
import marites.exception.UnknownTaskTypeException;
import marites.task.Task;

import static java.lang.Integer.parseInt;

/**
 * The main class.
 */
public class Marites {

    private static final String SAVE_FILENAME = "tasklist.ser";

    private final Storage storage;
    private final Ui ui;

    public static void main(String[] args) {
        new Marites(SAVE_FILENAME).run();
    }

    public Marites(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui(System.in, System.out);
    }

    /**
     * Runs the main loop of the chatbot.
     */
    public void run() {
        TaskList tasks = storage.load();
        ui.showIntroduction();
        while (true) {
            String inputCommand = ui.readCommand();
            try {
                Command command = Parser.parse(inputCommand);
                command.execute(storage, ui, tasks);
                if (command.isExit()) {
                    break;
                }
            } catch (MaritesException e) {
                ui.showError(e);
            } finally {
                ui.showSeparator();
            }
        }
    }
}
