package Eliz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Paths;

public class Eliz {

    private Storage storage;
    private static TaskList tasks;
    private Ui ui;
    private static Parser parserHere;
    public static String filePath;

    public Eliz(String filePath) {
        this.filePath = filePath;
        ui = new Ui();
        ui.botIntroduction();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void run() throws ElizException, IOException {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.equalsIgnoreCase("bye")) {
            Parser parserHere = new Parser(line, tasks, filePath);
            parserHere.getInput();
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) throws ElizException, IOException {
        String filePath = "src/main/java/listOfTasks.txt";
        File newFile = new File(filePath);
        try{
            if (newFile.exists()) {
                new Eliz(filePath).run();
            }
        } catch (FileNotFoundException e) {
            newFile.createNewFile();
            System.out.println("File is not created, please try again!");
        }
    }
}

