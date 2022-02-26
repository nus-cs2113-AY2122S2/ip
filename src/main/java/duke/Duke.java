package duke;

import java.io.FileNotFoundException;
import java.text.ParseException;

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
        } catch (ParseException e) {
            System.out.println("Hmm...There's something wrong with Deadlines in your data file.");
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
