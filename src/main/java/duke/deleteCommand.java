package duke;

import duke.task.Task;

public class deleteCommand extends Command {
    private final int taskIndex;

    public deleteCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.removeTask(this.taskIndex);
        tasks.writeTasksToFile();

        System.out.println("Noted. I've removed this task");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.getSize()));
    }
}
