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

    // Takes in a string. Executed commands will print related statements.
//    public static void executeCommands (TaskList tasks, Ui ui, Storage storage) throws DukeException {
//        String[] userInputArr;
//        String userCommand;
//        String[] userArguments;
//        String description;
//
//        userInputArr = userInput.split(" ", 2);
//        userCommand = userInputArr[0];
//
//        switch (userCommand) {
//        case "list":
//            currChat.printTaskList();
//            break;
//        case "mark":
//            // Additional argument provided by user is the task no. to mark
//            currChat.markTaskIndex(Integer.parseInt(userInputArr[1]));
//            break;
//        case "unmark":
//            currChat.unmarkTaskIndex(Integer.parseInt(userInputArr[1]));
//            break;
//        case "todo":
//            // Detect missing arguments
//            if (userInputArr.length == 1) {
//                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
//            }
//            currChat.addTask(new Todo(userInputArr[1]));
//            break;
//        case "deadline":
//            // eg. return book /by Sunday
//            userArguments = userInputArr[1].split(" /by ", 2);
//            description = userArguments[0]; // eg. return book
//            String by = userArguments[1]; // eg. Sunday
//            tasks.addTask(new Deadline(description, by));
//            break;
//        case "event":
//            // eg. project meeting /at Mon 2-4pm
//            userArguments = userInputArr[1].split(" /at ", 2);
//            description = userArguments[0]; // eg. project meeting
//            String eventTime = userArguments[1]; // eg. Mon 2-4pm
//            currChat.addTask(new Event(description, eventTime));
//            break;
//        case "delete":
//            currChat.deleteTaskIndex(Integer.parseInt(userInputArr[1]));
//            break;
//        default:
//            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
//        }
//    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
