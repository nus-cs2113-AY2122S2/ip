package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws FileNotFoundException {
        //File saveFile = FileHandler.loadOrCreateSaveFile("data/duke.txt");
        Scanner mainScan = new Scanner(System.in);
        UserInterface ui = null;
        try {
            ui = new UserInterface(mainScan);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        ui.start();
    }
}
