package duke;

import exceptions.DukeException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main class for Duke Task Manager
 */
public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();

    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private Parser parser;


    /**
     * Initializes the different components of Duke such as User Interface, TaskList, Storage
     * and Parser classes.
     */
    public Duke() {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage();
        parser = new Parser();
    }

    /**
     * Starts the Duke Task Manager Application.
     */
    public void run() {
        ui.greetUser("hi");
        startChatbot();
        ui.greetUser("bye");
    }

    public static void main(String[] args) {
        new Duke().run();
    }


    /**
     * Starts the chatbot where the user can type in commands to interact with Duke.
     */
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
