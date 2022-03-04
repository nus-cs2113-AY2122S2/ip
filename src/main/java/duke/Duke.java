package main.java.duke;

import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import main.java.duke.ui.Ui;
import main.java.duke.parser.Parser;
import main.java.duke.command.Command;
import main.java.duke.task.Task;
import main.java.duke.exception.DukeException;
import main.java.duke.storage.Storage;

/**
 * Main class for Duke that runs the bot. The bot takes in user inputs, and has various commands
 * that would perform various tasks
 */

public class Duke {

    private static Scanner sc = new Scanner(System.in);
    public static ArrayList<Task> tasks = new ArrayList<Task>();
    public static int taskCounter = 0;
    private final Parser parser = new Parser();

    /**
     * Main method used to run the bot. It calls the load() method from the Storage class to 
     * load data saved from previous uses, will ask for user inputs, will call the writeToFile()
     * method from Storage to update the latest information.
     */
    private void run() {
        try {
            Storage.load();
        } catch (IOException e) {
            System.out.println("ERROR IN LOADING FILE");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return;
        }
        Ui.printIntro();
        String input = "";
        while (!input.equals("bye")) {
            try {
                input = sc.nextLine();
                Command command = parser.parse(input);
                command.execute();
            } catch (DukeException e) {
                Ui.printError(e);
            }
        }
        
    }

    /**
     * Main method for Duke.
     * 
     * @param args NIL.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}