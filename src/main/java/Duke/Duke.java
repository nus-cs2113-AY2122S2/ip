package Duke;

import Duke.Commands.Command;
import Duke.Parser.Parser;
import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Ui.Ui;

public class Duke {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;


    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (DukeException e) {
            Ui.displayInvalidLoadmessage();
            tasks = new TaskList();
        }
    }


    public void run() {
        ui.displayGreetMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String message = ui.readCommand();
                String messageLowerCase = message.toLowerCase();
                Command c = Parser.parse(messageLowerCase, tasks);
                c.executeCommand(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
