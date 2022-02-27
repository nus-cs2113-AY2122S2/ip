import commands.Command;
import common.DukeException;
import data.TaskManager;
import parser.Parser;
import storage.FileManager;
import ui.Ui;

public class Duke {
    private Ui ui;
    private TaskManager taskManager;
    private FileManager fileManager;

    public Duke(String filePath) {
        fileManager = new FileManager();
        taskManager = new TaskManager();
        ui = new Ui(taskManager);
    }

    public void run() {
        ui.showGreetingMessage();

        String fullCommand = ui.getUserCommand();
        Command c = Parser.parse(fullCommand);
        c.execute(taskManager, fileManager, ui);
        
        ui.showByeMessage();
    }

    public static void main(String[] args) {
        new Duke("data/tasks/txt").run();
    }



}
