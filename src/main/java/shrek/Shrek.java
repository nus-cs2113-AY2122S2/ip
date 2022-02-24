package shrek;

import shrek.commands.Ui;
import shrek.storage.Initialise;

public class Shrek {
    /**
     * Initialises Shrek, prints the greeting and takes in commands.
     * When there are no more commands, print the exit message.
     *
     * @param args
     */
    public static void main(String[] args) {
        Initialise.initialiseShrek();
        Ui.printGreeting();
        Ui.readCommand();
        Ui.printGoodbye();
    }
}
