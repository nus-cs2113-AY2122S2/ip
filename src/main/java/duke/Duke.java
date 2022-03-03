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

public class Duke {

    private static Scanner sc = new Scanner(System.in);
    public static ArrayList<Task> tasks = new ArrayList<Task>();
    public static int taskCounter = 0;
    private final Parser parser = new Parser();

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
        try {
            Storage.writeToFile();
        } catch (IOException e) {
            System.out.println("ERROR IN WRITING FILE");
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}