package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DoneCommand extends Command {
    private final int taskIndex;
    private final String type;

    public DoneCommand(int taskIndex, String type) {
        this.taskIndex = taskIndex;
        this.type = type;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if(this.type.contains("unmark")) {
            taskList.getTask(taskIndex).markAsNotDone();
            return ui.generateResponse("Nice! I've marked this task as not done yet:\n" +
                    taskList.getTask(taskIndex) + "\n");
        }
        else {
            taskList.getTask(taskIndex).markAsDone();
            return ui.generateResponse("Nice! I've marked this task as done yet:\n" +
                    taskList.getTask(taskIndex) + "\n");
        }
    }
}
