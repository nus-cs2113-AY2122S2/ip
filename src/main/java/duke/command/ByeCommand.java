package main.java.duke.command;

import main.java.duke.ui.Ui;

public class ByeCommand extends Command {

    public ByeCommand() {

    }

    public void execute() {
        Ui.printBye();
    }
}