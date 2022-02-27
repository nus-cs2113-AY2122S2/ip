package duke;

import duke.task.TaskList;

import java.io.IOException;

public class Duke {
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

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
        }
        ui.executeCommand(tasks);
        ui.printExit();
    }

    public static void main(String[] args) {
        Duke.run();
    }
}
