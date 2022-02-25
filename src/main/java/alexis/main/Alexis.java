package alexis.main;

import alexis.parser.Parser;
import alexis.taskList.TaskList;
import alexis.commands.Command;
import alexis.storage.Storage;
import alexis.ui.Ui;
import alexis.exceptions.AlexisException;

public class Alexis {

    public static TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    public Alexis(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (AlexisException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }

    }

    public void run() {
        Ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine();  // show the divider line ("---------")
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, storage);
            isExit = c.isExit();
            ui.showLine();
        }
    }

    public static void main(String[] args) {
        new Alexis("./data/tasks.txt").run();
    }

}
