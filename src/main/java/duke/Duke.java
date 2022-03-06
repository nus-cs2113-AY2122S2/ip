package duke;
import duke.command.Command;
import duke.task.*;

public class Duke {

    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public Duke() {
        storage = new Storage("list.txt");
        tasks = new TaskList(storage.convertFileToList());
        ui = new Ui();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.showLine();
                String command = ui.getUserInput();
                Command newCommand = Parser.parse(command);
                newCommand.execute(tasks, ui, storage);
                isExit = newCommand.getIsExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}