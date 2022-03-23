package duke;


import duke.parsers.Ui;
import duke.storage.Storage;
import duke.tasks.TaskList;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {

    /** Number of tasks held by Duke */
    public static int taskCounter = 0; //counts number of tasks
    /** Instance of Storage class to write to file */
    private static Storage storage;
    /** Instance of Ui class to converse with user */
    private static Ui ui;
    /** Instance of TaskList class to hold all tasks */
    private static TaskList toDos;


    /**
     * Returns an instance for the Duke class
     *
     * @return An Instance of Duke
     */
    public Duke() {
        storage = new Storage();
        toDos = new TaskList();
        ui = new Ui(toDos);

    }

    /**
     * Runs the Duke program
     *
     * @param filePath The path of the file where tasks are stored to disk
     */
    public void run(String filePath) {
        ui.printGreeting();

        taskCounter = storage.listCreate(filePath, toDos, taskCounter);

        while (true) {
            storage.fileWrite(filePath, toDos);

            ui.parseLine();
        }
    }

    /**
     * The main method of Duke
     * Calls the Duke constructor and run method
     *
     * @param args The command line arguments
     * @throws IllegalArgumentException  If zone is <= 0.
     */
    public static void main(String[] args) {
        Path path = Paths.get("taskList.txt");

        Duke duke = new Duke();
        duke.run(path.toString());

    }
}
