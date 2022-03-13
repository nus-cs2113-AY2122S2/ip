package Eliz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Paths;

/** Executes the main program Eliz. Contains the main function to be run and calls different methods from the
 *  stored data into the current program.
 */
public class Eliz {

    public static Storage storage;
    private static TaskList tasks;
    public static Ui ui;
    private static Parser parserHere;
    public static String filePath;

    public Eliz(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads in inputs from user and does the necessary actions.
     *
     * @throws ElizException If incorrect inputs are given by the user.
     * @throws IOException If the input is not correctly read.
     */
    public static void run() throws ElizException, IOException {
        ui = new Ui();
        ui.botIntroduction();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.equalsIgnoreCase("bye")) {
            Parser parserHere = new Parser(line, tasks, filePath);
            parserHere.getInput();
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Calls the main function to run after checking if a file for storage exists.
     * If file does not exist, create a new file
     *
     * @param args An array of string that stores arguments passed by the command line while starting a program
     * @throws ElizException If incorrect inputs are given by the user.
     * @throws IOException If the input is not correctly read.
     */
    public static void main(String[] args) throws ElizException, IOException {
        filePath = "listOfTasks.txt";
        File newFile = new File(filePath);
        if (!newFile.exists()) {
            newFile.createNewFile();
            System.out.println("File is not created, please try again!");
        }
        run();

    }
}

