package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import task.Deadlines;
import task.Events;
import task.Task;

public class AddCommand extends Command{
    private final String taskType;

    private final String description;

    private final String time;

    public AddCommand(String taskType, String description, String time) {
        this.taskType = taskType;
        this.description = description;
        this.time = time;
    }

    /**
     *
     * @param taskList The given Duke TaskList
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if(taskType.contains("deadlines")) {
            Deadlines deadline = new Deadlines(description, time);
            taskList.add(deadline);
        }
        else if(taskType.contains("events")) {
            Events event = new Events(description, time);
            taskList.add(event);
        }
        else {
            Task task = new Task(description);
            taskList.add(task);
        }
        return ui.generateResponse(String.format("%s\nNow you have %d tasks in you list.\n", taskList.getTask(taskList.size()), taskList.size()));
    }
}
