package command;

import controller.Storage;
import controller.TaskList;
import controller.UI;
import exception.DukeException;
import exception.NoDescriptionException;

public abstract class Command {
   public abstract void execute(TaskList tasks, UI ui, Storage storage) throws NoDescriptionException, DukeException;
   public abstract boolean isExit();
}
