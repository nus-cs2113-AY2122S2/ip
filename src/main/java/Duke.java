import java.io.IOException;
import java.io.FileNotFoundException;

import duke.ui.Ui;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.messages.Messages;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * @param filePath
     */
    public Duke(String filePath) {

        ui = new Ui();
        storage = new Storage(filePath);

        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }


    public void run() {
        String command;

        ui.printIntro();

        do {
            String input = ui.getCommand();
            command = Parser.getCommandFromUserInput(input);
            Parser.runCommand(input, command, taskList);
        } while (!command.equals("bye"));

        ui.printBye();

        storage.writeToFile(taskList);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new Duke(Messages.MESSAGE_FILEPATH).run();
    }



}