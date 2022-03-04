package duke;
import duke.tasklist.*;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTaskList());
        } catch (DukeException e) {
            ui.showLoadingError("Error in loading text file of tasks!");
            tasks = new TaskList();
        }
    }
    public void run() {
        ui.showWelcome();
        boolean endProgram = false;

        while (!endProgram) {
            try {
                String command = ui.readCommand();
                int continueProgram = Parser.parse(command,tasks,storage);
                if (continueProgram == 0) {
                    endProgram = true;
                }
            } catch (DukeException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }

}

