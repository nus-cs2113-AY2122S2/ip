package duke;

import java.io.FileWriter;
import java.io.IOException;

public abstract class Command {

    public Command() {
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    // Default returns false unless exitCommand()
    public boolean isExit() {
        return false;
    }

}
