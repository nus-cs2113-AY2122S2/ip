package duke;

import duke.command.Command;
import duke.exception.DukeException;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath,ui);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    public void run() {
        boolean shouldExitProgram = false;
        ui.showHorizontalLine();
        ChatBot bigBob;
        bigBob = new ChatBot(ui,tasks);
        ui.showHorizontalLine();
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
            if (inputCommand.getType() == Command.CommandType.EXITPROGRAM) {
                shouldExitProgram = true;
                ui.echoFarewellGreeting();
                ui.showHorizontalLine();
                continue;
            }
            writeListToFile = bigBob.executeCommand(inputCommand);
            ui.showHorizontalLine();
            if (!writeListToFile) {
                continue;
            }
            try {
                storage.writeArrayListToFile(tasks.getListOfTasks(),ui);
            } catch (IOException io) {
                ui.showFileWritingError();
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        new Duke("data/duke.txt").run();
    }
}
