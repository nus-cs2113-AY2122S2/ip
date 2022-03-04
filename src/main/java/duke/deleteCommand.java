package duke;

public class deleteCommand extends Command {
    private final int taskIndex;

    public deleteCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String output = tasks.removeTask(this.taskIndex).toString();
        System.out.println("Noted. I've removed this task");
        System.out.println(output);
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.getSize()));
    }
}
