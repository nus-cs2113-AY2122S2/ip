package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Todo;

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_SUCCESS = "Got it. I've added this task:";
    private final Todo todo;

    public TodoCommand(String userInput) {
        super();
        this.todo = new Todo(userInput);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(this.todo);
        storage.writeTasksToStorage(tasks);

        ui.showToUser(
                MESSAGE_SUCCESS,
                String.format("  %s", this.todo),
                tasks.getNumRemainingTasksUi());
    }
}
