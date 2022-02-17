package jarvis;

import jarvis.commands.UserList;
import jarvis.display.DisplayMessages;

import java.util.Scanner;
import java.io.File;

public class Jarvis {
    protected static boolean hasLoadedFile (File savedFile) {
        return savedFile.exists();
    }

    public static void main(String[] args) {
        DisplayMessages.startingMessage();
        File savedFile = new File("data/Jarvis.txt");
        if (hasLoadedFile(savedFile)) {
            DisplayMessages.savedFileDetected();
            UserList.loadFile(savedFile);
            DisplayMessages.fileLoaded();
        } else {
            DisplayMessages.noFileDetected();
        }
        Scanner in = new Scanner(System.in);
        while (true) {
            Formatter.inputHandler(in);
        }
    }
}
