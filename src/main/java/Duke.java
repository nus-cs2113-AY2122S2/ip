import duke.exceptions.*;
import java.io.IOException;
import java.io.FileNotFoundException;

import duke.ui.Ui;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.messages.Messages;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        String command;

        ui.printIntro();

        do {
            String input = ui.getCommand();
            command = Parser.getCommandFromUserInput(input);
            Parser.runCommand(input, command, tasks);
        } while (!command.equalsIgnoreCase("bye"));

        ui.printBye();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        new Duke(Messages.MESSAGE_FILEPATH).run();
    }



}