package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    Ui ui;
    Storage storage;
    TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage();
        this.tasks = storage.getTasks();
        tasks = new TaskList();
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Duke("data/tasks.txt").start();
    }

    public void start() {
        ui.printGreeting();
        ui.loopCommandInput();
        try {
            Storage.updateSaveFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
