package duke;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static Storage storage;
    private static Ui ui;
    private static TaskList tasks;

    public Duke() throws DukeException, IOException {
        this.ui = new Ui();
        storage = new Storage("data/Hage.txt");
        storage.createFile();
        tasks = new TaskList(storage.loadData(), storage.getItemNum());
    }

    public void run() throws DukeException, IOException {
        ui.printWelcomeMessage();
        while (true) {
            getNewInput();
            storage.saveTaskList();
        }
    }

    public static void main(String[] args) throws DukeException, IOException {
        new Duke().run();
    }

    private static void getNewInput() throws DukeException, IOException {
        String inputCommand = SCANNER.nextLine();
        tasks.executeCommand(inputCommand);
    }

}
