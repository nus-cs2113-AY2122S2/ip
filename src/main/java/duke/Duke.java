package duke;

import duke.command.Command;
import duke.exception.AdditionalException;
import duke.parser.Parser;
import duke.storage.Storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Represents a Personal Assistant Chat bot that helps a person to keep track of various things.
 */
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

    /**
     * Starts accepting user input and runs the command retrieved.
     * The method will stop accepting user inputs when the user inputs "bye" and prints a goodbye message.
     */
    public void run() {
        boolean isBye = false;
        while (!isBye) {
            String fullCommand = ui.readCommand();
            isBye = runFullCommand(fullCommand);
        }
        ui.printGoodbye();
    }
    /**
     * Parses the user input and returns true if the input is "bye".
     * Using the command object returned from the parser, it will the run the execute method.
     *
     * @param fullCommand The full command which is the user input.
     * @return true if user input is "bye".
     */
    private boolean runFullCommand(String fullCommand) {
        boolean isBye = false;
        try {
            Command command = Parser.parse(fullCommand);
            command.execute(tasks, ui, storage);
            isBye = command.isBye();
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

    /**
     * Creates a new Duke object with a filePath and calls the method run.
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Duke("./duke.txt").run();
    }
}
