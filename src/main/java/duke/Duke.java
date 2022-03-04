package duke;


import java.io.IOException;
import java.util.Scanner;

import exceptions.UnknownCommandException;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Creates an instance of Duke Programme, with data available in file path.
     * <p>
     * Based on storage created through the file path, an error message would be shown if the file does not exist
     * </p>
     *
     * @param filePath the file path of the text document that would be used for reading and writing
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError(filePath);
            tasks = new TaskList();
        }
        parser = new Parser(ui, tasks, storage);
    }

    /**
     * Runs the instance of Duke Programme.
     */
    public void run() {
        ui.startProgram();
        converse();
        ui.exit();
    }

    /**
     * This is the main method which creates a new Duke instance to be run.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Looks out for output using a Scanner object and carries the conversation with the user.
     * <p>
     * Based on the first input of the user, this method will check if it is exactly "bye",
     * which is the exit command for this programme.
     * If it is not, it will try to interpret the command through a runCommand() method.
     * <p>
     * This method will continue a while loop so long as the user does not give an input "bye".
     * </p>
     */
    public void converse() {
        Scanner sc = new Scanner(System.in);
        String response = sc.nextLine();

        boolean isNotBye = !response.equals("bye");

        while (isNotBye) {
            try {
                parser.runCommand(response, tasks, storage);
            } catch (UnknownCommandException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (NullPointerException e) {
                System.out.println("☹ OOPS!!! Description cannot be empty!");
            }
            response = sc.nextLine();
            isNotBye = !response.equals("bye");
        }
    }
}
