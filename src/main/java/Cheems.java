import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Cheems {

    private static final String SEPARATOR = "-------------------------------------------";
    private static final String filePath = "./data/data.txt";
    private static Ui ui;
    private static Storage storage;
    private static Parser parser;

    /**
     * Keep asking user for input until it is bye.
     */
    public static void askInput() {
        storage = new Storage();
        ui = new Ui();
        parser = new Parser(storage.loadData(), storage);
        String command = ui.getCommand();
        do {
            parser.parseCommand(command);
            command = ui.getCommand();
        } while (!command.equals("bye"));
    }

    public static void main(String[] args) {
        ui.greet();
        askInput();
        ui.farewell();
    }
}
