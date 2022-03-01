package duke;

import duke.exception.DukeInvalidFileContentException;
import duke.task.TaskList;

import java.io.IOException;

public class Duke {
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    /**
     * Where the Duke program is launched. The user interface for user
     * interaction, storage manager for saving data and task list for
     * managing task operations are initialized here. Program will
     * attempt to load the data/duke.txt file upon launch and execute
     * all user command.
     */
    public static void run() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
        ui.printGreeting();
        try {
            storage.loadDukeDataFile(tasks);
            System.out.println("Initializing: Duke file loaded successfully...");
        } catch (IOException e) {
            System.out.println("Unable to load duke file.");
        } catch (DukeInvalidFileContentException e) {
            System.out.println("Unable to load duke file.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Unable to load duke file.");
        } catch (NumberFormatException e) {
            System.out.println("Unable to load duke file.");
        }
        ui.executeCommand(tasks);
        ui.printExit();
    }

    public static void main(String[] args) {
        Duke.run();
    }
}
