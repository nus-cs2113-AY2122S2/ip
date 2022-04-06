package yae;

import yae.exception.MissingDateException;
import yae.exception.MissingDescriptionException;
import yae.parser.Parser;
import yae.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Yae {
    private final Storage storage;

    public Yae() {
        storage = new Storage();
    }

    public void start() {
        Ui.printWelcomeMessage();
        try {
            storage.readSaveData();
        } catch (FileNotFoundException e) {
            System.out.println(("Save File not found."));
        }
    }

    public void end() {
        try {
            storage.saveData();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        System.out.println("Goodbye, see you next time!");
    }

    public void run() {
        start();
        String userInput;
        do {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            try {
                Parser.parseInput(userInput);
            } catch (MissingDescriptionException e) {
                Ui.printMissingDescriptionErrorMessage();
            } catch (MissingDateException e) {
                Ui.printMissingDateErrorMessage();
            }
        } while (!userInput.equals("bye"));
        end();
    }

    public static void main(String[] args) {
        new Yae().run();
    }
}
