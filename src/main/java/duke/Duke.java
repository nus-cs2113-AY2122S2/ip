package duke;


import java.io.IOException;
import java.util.Scanner;

import exceptions.UnknownCommandException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            //ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.startProgram();
        converse();
        ui.exit();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public void converse() {
        Scanner sc = new Scanner(System.in);
        String response = sc.nextLine();

        boolean isNotBye = !response.equals("bye");

        while (isNotBye) {
            try {
                runCommand(response, tasks, storage);
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
