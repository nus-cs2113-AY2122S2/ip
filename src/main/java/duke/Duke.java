package duke;

import command.Command;
import command.ExitCommand;
import duke.exception.DukeException;

import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static final Ui ui = new Ui();
    private static final Parser parser = new Parser();
    private static TaskList tasklist = new TaskList();
    private static Command command;

    public static void main(String[] args) throws DukeException {
        System.out.println(ui.printLogo());
        System.out.println(ui.greet());
        String fullCommand = parser.getCommand();
        Storage storage;
        try {
            storage = new Storage();
            tasklist = storage.loadTaskList();
        } catch (DukeException e) {
            throw new DukeException("Cannot load file.\n");
        }
        while (!fullCommand.equals("bye")) {
            try {
                    Command c = parser.parse(fullCommand);
                    System.out.println(c.execute(tasklist, ui, storage));
                    fullCommand = parser.getCommand();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                fullCommand = parser.getCommand();
                continue;
            }
        }
        ExitCommand exitCommand = new ExitCommand();
        System.out.println(exitCommand.execute(tasklist, ui, storage));
    }

}
