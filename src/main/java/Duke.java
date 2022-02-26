/**
 * Main driver class of the Duke application
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        tasks = new TaskList(storage, ui);
    }

    public void run() {
        tasks.processTasks();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
        //new Duke("data/duke2.txt").run();
        //new Duke("dataduke.txt").run();
    }
}

