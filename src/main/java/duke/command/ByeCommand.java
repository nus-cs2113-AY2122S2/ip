package main.java.duke.command;

import java.io.IOException;
import main.java.duke.ui.Ui;
import main.java.duke.storage.Storage;

/**
 * Class for ByeCommand. It is created when the user wants to close Duke.
 */

public class ByeCommand extends Command {

    /**
     * Method to carry out the command. It initiates the writing of the task list into
     * the txt file, and prints a goodbye message before closing Duke.
     */
    public void execute() {
        try {
            Storage.writeToFile();
        } catch (IOException e) {
            System.out.println("ERROR IN WRITING FILE");
        }
        Ui.printBye();
    }
}