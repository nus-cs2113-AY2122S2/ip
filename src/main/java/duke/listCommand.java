package duke;

import duke.task.Task;

public class listCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println(String.format("%d. %s", i+1, tasks.getTask(i)));
        }
    }
}
