package duke;

import duke.command.Command;
import duke.data.TaskList;
import duke.exception.*;
import duke.parser.Parser;
import duke.storage.DataStorage;
import duke.ui.TextUi;

import java.io.*;
import java.util.ArrayList;

import static duke.common.Strings.*;

public class Duke {
    private TextUi ui;
    private DataStorage storage;
    private TaskList savedTasks = new TaskList();

    /**
     * Main entry point of the program.
     */
    public static void main(String[] args) {
        new Duke().start();
    }

    public void start() {
        ui = new TextUi();
        storage = new DataStorage();

        if (!dataFileExists()) {
            System.exit(-1);
        }

        try {
            savedTasks = storage.load();
            ui.printCommandResult(storage.getDataLoadFeedback());
        } catch (FileNotFoundException e) {
            ui.printMessage(MESSAGE_IMPOSSIBLE);
            System.exit(-2);
        }

        ui.showWelcomeMessage();
        acceptUserCommands();
    }

    private void acceptUserCommands() {
        ArrayList<String> commandResult;
        do {
            String[] input = ui.getUserInput();
            Command command = Parser.parseCommand(input);
            command.setTaskList(savedTasks);
            commandResult = command.execute();
            try {
                storage.save(savedTasks);
            } catch (IOException e) {
                ui.showDataSaveErrorMessage();
            }
            ui.printCommandResult(commandResult);
        } while (commandResult.get(commandResult.size() - 1) != null);
    }

    /**
     * Checks for existence of the data file and attempts to create it if it doesn't already exist.
     * @return true if data file already exists or could be created in specified location, false otherwise.
     */
    private boolean dataFileExists() {
        if (!storage.checkDirectoryExists()) {
            try {
                storage.createDirectory();
                ui.printMessage(MESSAGE_DIRECTORY_CREATED);
            } catch (DataStorageAccessException e) {
                ui.printMessage(MESSAGE_DIRECTORY_ERROR);
                return false;
            }
        } else {
            ui.printMessage(MESSAGE_DIRECTORY_FOUND);
        }
        if (!storage.checkFileExists()) {
            try {
                storage.createFile();
                ui.printMessage(MESSAGE_DATA_FILE_CREATED);
            } catch (IOException e) {
                ui.printMessage(MESSAGE_DATA_FILE_ERROR);
                return false;
            }
        } else {
            ui.printMessage(MESSAGE_DATA_FILE_FOUND);
        }
        return true;
    }
}
