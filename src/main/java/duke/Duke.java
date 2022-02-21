package duke;

import java.io.FileNotFoundException;

public class Duke {
    private final TaskList tasks;
    private final Ui ui;

    public Duke() {
        tasks = new TaskList();
        ui = new Ui();

        try {
            Storage.readToList(tasks);
        } catch (FileNotFoundException e) {
            System.out.println("Hmm...File creation failed, I cannot write to the data file.");
        }
    }

    public void run() {
        Storage dataFile = new Storage();
        ui.sayHello();

        ui.interact(tasks);

        Storage.writeData(dataFile, tasks);
        ui.sayGoodbye();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}
