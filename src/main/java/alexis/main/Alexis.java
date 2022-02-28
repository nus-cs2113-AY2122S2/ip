package alexis.main;

import alexis.parser.Parser;
import alexis.taskList.TaskList;
import alexis.commands.Command;
import alexis.storage.Storage;
import alexis.ui.Ui;
import alexis.exceptions.AlexisException;

import java.io.IOException;

/**
 * Contains the root of the program, and contains the "main" method
 */
public class Alexis {

    public static TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Sets up the Alexis class, creating new Ui, Storage and TaskList objects.
     *
     * @param filePath Filepath of the file used to save and load the task list
     */
    public Alexis(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (AlexisException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            System.out.println("Failed to load new file");
        }
    }

    /**
     * Runs the program -- from welcoming the user, reading in input from the user, and finally exiting the program.
     */
    public void run() {
        Ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine();  // show the divider line ("---------")
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, storage);
            isExit = c.isExit();
            ui.showLine();
        }
    }

    public static void main(String[] args) {
        new Alexis("./data/tasks.txt").run();
    }

}
