package duke;
import duke.tasks.TaskList;
import java.io.IOException;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {

    private Storage storage;
    private static TaskList taskList;
    private Ui ui;

    public Duke(String filePath) throws IOException {
        storage = new Storage(filePath);
        ui = new Ui();
        taskList = new TaskList(storage);
        taskList.loadTaskList();
    }

    public void run() throws IOException {
        Ui.greeting();
        startBot();
        Ui.farewell();
    }

    private static void startBot() throws IOException {
        taskList.processTasks();
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt").run();
    }
}
