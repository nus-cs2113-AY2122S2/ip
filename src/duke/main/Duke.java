package duke.main;

import duke.helper.Storage;
import duke.helper.TaskList;
import duke.helper.Ui;
import duke.helper.Parser;

public class Duke {
    private static final Storage storage = new Storage();
    private static final TaskList tasks = new TaskList();
    private static final Ui ui = new Ui();
    private static final Parser parser = new Parser();

    public static void waitForInput() {
        while (true) {
            String line = parser.getLine();
            if (parser.isExitCommand()) {
                break;
            }else if (parser.isListCommand()) {
                tasks.listTasks();
            }else if (parser.isMarkCommand()) {
                int index = parser.parseIndex(ui, tasks.getTaskCount());
                tasks.updateMarkTask(index, true, ui, storage);
            }else if (parser.isUnmarkCommand()) {
                int index = parser.parseIndex(ui, tasks.getTaskCount());
                tasks.updateMarkTask(index, false, ui, storage);
            }else if (parser.isDeleteCommand()) {
                int index = parser.parseIndex(ui, tasks.getTaskCount());
                tasks.deleteTask(index, ui, storage);
            }else {
                tasks.addNewTask(line, ui, storage);
            }
        }
    }

    public static void run() {
        storage.loadSavedTasks(ui, tasks);
        ui.printStartUpMessage(storage.isLoaded());

        waitForInput();
        ui.printExitMessage();
    }

    public static void main(String[] args){
        run();
    }
}
