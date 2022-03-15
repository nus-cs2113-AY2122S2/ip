package duke;

import duke.command.Command;

/**
 * Represents the main control class for the App
 */
public class Duke {

    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public Duke() {
        storage = new Storage("list.txt");
        tasks = new TaskList(storage.convertFileToList());
        ui = new Ui();
    }

    /**
     * Runs the Duke application
     */
    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.printDivider();
                String command = ui.getUserInput();
                Command newCommand = Parser.parse(command);
                newCommand.execute(tasks, ui, storage);
                isExit = newCommand.getIsExit();
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            } finally {
                ui.printDivider();
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
