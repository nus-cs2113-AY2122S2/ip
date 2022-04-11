package duke;

import java.io.IOException;

/**
 * The main class of all the other objects for this program.
 * Starts of the program and initiates other classes.
 */
public class Duke {

    private static Storage storage;
    private static Ui ui;
    private static TaskList tasks;

    /**
     * Creates a new instance of the program and loads the saved file stored locally.
     *
     * @throws DukeException If there is an error loading task from saved file
     * @throws IOException If there is an error creating the file
     */
    public Duke() throws DukeException, IOException {
        this.ui = new Ui();
        storage = new Storage("data/Hage.txt");
        storage.createFile();
        tasks = new TaskList(storage.loadData(), storage.getItemNumber());
    }

    /**
     * Prints the welcome message and starts the program loop
     * which continuously accept users input until they terminate
     * the session and saves the list into a local file.
     *
     * @throws DukeException If there is an error executing the command
     * @throws IOException If there is an error opening or closing the file
     */
    public void run() throws DukeException, IOException {
        ui.printWelcomeMessage();
        while (true) {
            tasks.executeCommand(ui.getNewInput());
            storage.saveTaskList();
        }
    }

    public static void main(String[] args) throws DukeException, IOException {
        new Duke().run();
    }
}
