package duke.main;

import duke.helper.Storage;
import duke.helper.TaskList;
import duke.helper.Ui;
import duke.helper.Parser;

public class Duke {
    private static final Storage storage = new Storage();
    private static final TaskList tasks = new TaskList();
    private static final Ui ui = new Ui();
    private static final Parser parser = new Parser();

    public static void run() {
        storage.loadSavedTasks(ui, tasks);
        ui.printStartUpMessage(storage.isLoaded());

        parser.waitForInput(ui, storage, tasks);
        ui.printExitMessage();
    }

    public static void main(String[] args){
        run();
    }
}
