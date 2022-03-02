import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.ui.Ui;
import duke.task.TaskManager;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {

    //fields
    private TaskManager taskManager;
    private Ui ui;
    private Storage storage;

    //methods

    /**
     * Creates a Duke Object
     * @param filePath filePath of the input/output file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskManager = new TaskManager();
            taskManager.setTasks(storage.readFile());
        }
        catch (IOException e) {

        }
    }

    /**
     * Runs the main function
     * @throws IOException
     */
    public void run() throws IOException {
        ui.hello();
        boolean isExit = false;
        while(!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskManager, ui, storage);
                isExit = c.isExit();
            }
            catch(DukeException e) {
                ui.showError(e);
            }
        }
        storage.writeFile(taskManager.toString());
        Ui.printWithDivider("Bye. Hope to see you again soon!");

    }

    public static void main(String[] args) throws IOException {
      new Duke ("./IOfile.txt").run();
    }
}
