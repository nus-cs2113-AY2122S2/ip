package main.java.duke.command;

import main.java.duke.ui.Ui;

public class commandsCommand extends Command {

    public commandsCommand() {

    }

    public void execute() {
        Ui.printCommand();
    }
}