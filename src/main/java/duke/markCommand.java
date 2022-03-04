package duke;

public class markCommand extends Command {
    private final int taskIndex;

    public markCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String output = tasks.getTask(this.taskIndex - 1).markTask();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(output);
    }
}
