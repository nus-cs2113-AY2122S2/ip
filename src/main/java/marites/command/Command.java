package marites.command;

import marites.Storage;
import marites.TaskList;
import marites.Ui;
import marites.exception.MaritesException;

abstract public class Command {
    public boolean isExit() {
        return false;
    }
    abstract public void execute(Storage storage, Ui ui, TaskList taskList) throws MaritesException;
}
