package commands;

import exceptions.MissingDescriptionException;
import tasks.TaskList;

public abstract class Command {
    public abstract ExecutedCommandResults executeCommand(TaskList tasks) throws MissingDescriptionException;
}
