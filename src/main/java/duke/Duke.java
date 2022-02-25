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
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    public void run() {
        boolean shouldExitProgram = false;
        ui.printHorizontalLine();
        ChatBot bigBob;
        bigBob = new ChatBot(ui,tasks);
        ui.printHorizontalLine();
        Command inputCommand;
        boolean writeListToFile;
        while (!shouldExitProgram) {
            String userInput = ui.readUserInput();
            ui.printHorizontalLine();
            try {
                inputCommand = Parser.parseInput(userInput);
            } catch (DukeException de) {
                ui.showParsingError(de);
                ui.printHorizontalLine();
                continue;
            }
            if (inputCommand.getType() == Command.CommandType.EXITPROGRAM) {
                shouldExitProgram = true;
                ui.echoFarewellGreeting();
                ui.printHorizontalLine();
                continue;
            }
            writeListToFile = bigBob.executeCommand(inputCommand);
            ui.printHorizontalLine();
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
