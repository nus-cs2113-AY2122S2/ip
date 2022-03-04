package duke;

public class unknownCommand extends Command {

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        System.out.println("I don't understand");
    }
}
