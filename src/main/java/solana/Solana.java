package solana;

import solana.command.Command;
import solana.task.TaskList;

/**
 * Represents the entry point of the Solana program. Initializes the program and starts interaction with the
 * user.
 */
public class Solana {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    public Solana() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
    }

    /**
     * Loads saved tasks from saveTask.txt if file exists, and runs the program till termination.
     */
    public void run() {
        storage.loadTasks();
        ui.printIntro();

        while (true) {
            String input = ui.readInput();
            Parser p = new Parser(input);
            Command c = p.parseCommand();
            c.executeCommand();
        }
    }

    public static void main(String[] args) {
        new Solana().run();
    }
}
