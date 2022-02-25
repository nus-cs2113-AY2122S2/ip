package alexis.commands;

import alexis.storage.Storage;
import alexis.taskList.TaskList;
import alexis.task.Todo;

public class TodoCommand extends Command{

    protected String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        taskList.add(new Todo(description));  // adds the new task into "tasks" arraylist
        int listSize = taskList.getListSize();
        int taskIndex = listSize - 1;
        taskList.getTask(taskIndex).addNewTaskMessage();
        storage.save(listSize, taskList.taskArrayList);
    }
}
