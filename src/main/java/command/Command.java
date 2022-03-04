package command;

import controller.Storage;
import controller.TaskList;
import controller.UI;

public abstract class Command {
   public abstract void execute(TaskList tasks, UI ui, Storage storage);
   public abstract boolean isExit();
}
