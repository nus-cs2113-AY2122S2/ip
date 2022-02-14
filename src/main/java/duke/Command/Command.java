package duke.Command;
import duke.save;
import duke.TaskList;
import duke.UI;

public abstract class Command {
    private boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public static String split(String todo){
        return todo;
    }


    public void setIsExit(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean getIsExit() {
        return this.isExit;
    }

    public abstract void execute(TaskList tasks, UI ui, save save);
}