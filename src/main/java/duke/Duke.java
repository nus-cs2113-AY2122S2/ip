package duke;

import duke.command.Command;

import duke.exception.DukeException;

import java.io.IOException;

/**
 * Represents the "brain" of the Duke program and acts as a link between the other components of the program such as the
 * User Interface, Input Parser, ChatBot and Storage.
 * The Duke Program implements an application which allows the user to keep track of any upcoming tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath, ui);
        try {
            tasks = new TaskList(storage.loadArrayListFromFile());
        } catch (DukeException de) {
            ui.showLoadingError(de);
            tasks = new TaskList();
        }
    }

    private void run() {
        boolean shouldExitProgram = false;
        ChatBot bigBob = new ChatBot(ui, tasks);
        Command inputCommand;
        boolean writeListToFile;
        while (!shouldExitProgram) {
            String userInput = ui.readUserInput();
            ui.showHorizontalLine();
            try {
                inputCommand = Parser.parseInput(userInput);
            } catch (DukeException de) {
                ui.showParsingError(de);
                ui.showHorizontalLine();
                continue;
            }
            if (inputCommand.getType() == Command.CommandType.ExitProgramCommand) {
                shouldExitProgram = true;
                ui.showFarewellGreeting();
                ui.showHorizontalLine();
                continue;
            }
            writeListToFile = bigBob.executeCommand(inputCommand);
            ui.showHorizontalLine();
            if (!writeListToFile) {
                continue;
            }
            try {
                storage.writeArrayListToFile(tasks.getListOfTasks(), ui);
            } catch (IOException io) {
                ui.showFileWritingError();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
