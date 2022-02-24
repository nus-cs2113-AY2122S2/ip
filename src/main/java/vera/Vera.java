package vera;

import vera.command.Command;
import vera.command.ExitCommand;

import java.io.IOException;
import static vera.constant.Messages.ERROR_IO_FAILURE_MESSAGE;

public class Vera {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Vera(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showToUser(ERROR_IO_FAILURE_MESSAGE);
            System.exit(1);
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine();
            Command c = Parser.parseCommand(fullCommand, taskList);
            if (c != null) {
                c.execute(taskList, ui, storage);
                isExit = ExitCommand.isExit(c);
            }
            ui.showLine();
        }
    }

    public static void main(String[] args) {
        new Vera("data/vera.txt").run();
    }

}
