package jarvis;

import jarvis.display.DisplayMessages;
import jarvis.load.Storage;
import jarvis.exceptions.JarvisNoSavedData;

import java.util.Scanner;


public class Jarvis {
    private static Storage storage;

    /**
     * Main driver function of the Jarvis bot
     */
    public static void main(String[] args) {
        DisplayMessages.startingMessage();
        storage = new Storage();
        try {
            storage.load();
        } catch (JarvisNoSavedData e) {
            DisplayMessages.noFileDetected();
        } finally {
            Scanner in = new Scanner(System.in);
            while (true) {
                Formatter.inputHandler(in);
            }
        }
    }
}
