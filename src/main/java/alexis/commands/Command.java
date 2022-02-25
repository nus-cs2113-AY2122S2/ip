package alexis.commands;

import alexis.storage.Storage;
import alexis.taskList.TaskList;

public class Command {
    protected boolean isExit;

    public Command() {
        isExit = false;
    }

    public void execute(TaskList taskList, Storage storage) {
        storage.save(taskList.getListSize(), taskList.taskArrayList);
    }

    public boolean isExit() {
        return isExit;
    }
}
