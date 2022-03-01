package duke;

import ui.DukeUi;

import java.io.FileNotFoundException;
import static ui.DukeUi.takeInputAndProcess;

/**
 * Duke is a CLI app to keep track of todos, deadlines and events
 *
 * @author ibrahimisramos
 * @version 0.2
 */

public class Duke {

    /**
     * Main method of Duke app, creates UI object to show welcome message and populates from taskFile, read's input till user types exit
     * @param args for runtime arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        DukeUi ui = new DukeUi();
        Storage storage = new Storage();
        TaskList tasklist = new TaskList(storage.readFromFile());
        takeInputAndProcess();
    }

}
