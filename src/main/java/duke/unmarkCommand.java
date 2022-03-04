package duke;

public class unmarkCommand extends Command {
    private final int taskIndex;

    public unmarkCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String output = tasks.getTask(this.taskIndex - 1).unmarkTask();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(output);
    }
}
