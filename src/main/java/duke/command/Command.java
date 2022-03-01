package main.java.duke.command;

import main.java.duke.exception.DukeException;

public interface Command {

    public void execute() throws DukeException;

}