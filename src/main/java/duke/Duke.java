package duke;

import duke.command.Command;
import duke.exception.AdditionalException;
import duke.parser.Parser;
import duke.storage.Storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        ui.printGreeting();
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException | AdditionalException error) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        boolean isBye = false;
        while (!isBye) {
            String fullCommand = ui.readCommand();
            isBye = runFullCommand(fullCommand);
        }
        ui.printGoodbye();
    }

    private boolean runFullCommand(String fullCommand) {
        boolean isBye = false;
        try {
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isBye = c.isBye();
        } catch (AdditionalException | IOException e) {
            ui.showError(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            ui.showIndexError();
        } catch (NumberFormatException e) {
            ui.showFormatError();
        } catch (DateTimeParseException e) {
            ui.showDateError();
        } finally {
            ui.showLine();
        }
        return isBye;
    }

    public static void main(String[] args) {
        new Duke("./duke.txt").run();
    }
}
