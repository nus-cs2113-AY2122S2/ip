package jarvis;

import jarvis.display.Ui;
import jarvis.load.Storage;
import jarvis.exceptions.JarvisNoSavedData;

import java.util.Scanner;


public class Jarvis {
    private static Storage storage;

    /**
     * Main driver function of the Jarvis bot
     */
    public static void main(String[] args) {
        Ui.startingMessage();
        storage = new Storage();
        try {
            storage.load();
        } catch (JarvisNoSavedData e) {
            Ui.noFileDetected();
        } finally {
            Scanner in = new Scanner(System.in);
            while (true) {
                Parser.inputHandler(in);
            }
        }
    }
}
