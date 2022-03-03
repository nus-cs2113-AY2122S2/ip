package duke;

import exceptions.DukeException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();

    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private Parser parser;
    public Duke() {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage();
        parser = new Parser();
    }

    public void run() {
        ui.greetUser("hi");
        startChatbot();
        ui.greetUser("bye");
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    private void startChatbot() {
        storage.readTaskFile();
        Scanner input = new Scanner(System.in);
        String task = "";
        boolean isChatbotRunning = true;
        while(isChatbotRunning) {
            try {
                task = input.nextLine();
                ui.printLine();
                switch(parser.parseCommand(task)) {
                case "bye":
                    isChatbotRunning = false;
                    break;
                case "list":
                    tasks.listTasks();
                    break;
                case "mark":
                    tasks.markTask(task);
                    break;
                case "unmark":
                    tasks.unmarkTask(task);
                    break;
                case "add":
                    tasks.addTask(task);
                    break;
                case "delete":
                    tasks.deleteTask(task);
                    break;
                case "find":
                    tasks.findTasks(task);
                    break;
                case "error":
                    throw new DukeException();
                case "default":
                    System.out.println("    Sorry I do not know what that means!");
                    break;
                }
                ui.printLine();
            } catch (DukeException e) {
                System.out.println("    Sorry I do not know what that means!");
            }

        }
    }

}
