package main.java.duke.command;

import main.java.duke.ui.Ui;

public class ListCommand implements Command {

    public ListCommand() {

    }

    public void execute() {
        Ui.printList();
    }
}