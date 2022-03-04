package duke;

public abstract class Command {

    public Command() {
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    // Default returns false unless exitCommand()
    public boolean isExit() {
        return false;
    }

}
