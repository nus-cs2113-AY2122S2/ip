package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
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
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
//        // Start chat session
//        ChatSession currChat = new ChatSession();
//        currChat.startSession();
//
//        // Get user input
//        Scanner sc = new Scanner(System.in);
//        String userInput;
//
//        while (true) {
//            userInput = sc.nextLine();
//
//            // Terminate chat session
//            if (userInput.startsWith("bye")) {
//                currChat.endSession();
//                break;
//            }
//
//            // Try to execute commands. If unrecognised commands, print error.
//            try {
//                executeCommands(tasks, ui, storage);
//            } catch (DukeException e) {
//                currChat.printInvalidTask(e);
//            }
//        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
