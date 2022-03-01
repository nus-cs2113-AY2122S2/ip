package main.java.duke.command;

import main.java.duke.ui.Ui;

public class InvalidCommand implements Command {

    public void execute() {
        Ui.printInvalid();
    }
}