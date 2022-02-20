package shrek;

import shrek.commands.Ui;
import shrek.storage.Initialise;

public class Shrek {

    public static void main(String[] args) {
        Initialise.initialiseShrek();
        Ui.printGreeting();
        Ui.readCommand();
        Ui.printGoodbye();
    }
}
