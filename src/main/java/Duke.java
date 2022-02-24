import data.TaskManager;
import storage.FileManager;
import ui.Ui;

public class Duke {
    private Ui ui;
    private TaskManager taskManager;
    private FileManager fileManager;

    public Duke(String filePath) {
        ui = new Ui();
        fileManager = new FileManager();
        taskManager = new TaskManager();
    }

    public void run() {
        ui.showGreetingMessage();
        taskManager.start();
        ui.showByeMessage();
    }

    public static void main(String[] args) {
        new Duke("data/tasks/txt").run();
    }



}
