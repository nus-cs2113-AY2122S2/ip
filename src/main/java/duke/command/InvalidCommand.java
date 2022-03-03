package main.java.duke.command;

import main.java.duke.ui.Ui;

public class InvalidCommand extends Command {

    public void execute() {
        Ui.printInvalid();
    }
}