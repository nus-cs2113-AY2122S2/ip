package alexis.commands;

import alexis.storage.Storage;
import alexis.taskList.TaskList;
import alexis.task.Todo;

/**
 * Creates a new todo task
 */
public class TodoCommand extends Command{

    protected String description;

    /**
     * Set's user's input as the task's description
     *
     * @param description User's input
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds todo task to Alexis.tasks
     *
     * @param taskList Alexis.tasks
     * @param storage Alexis.storage
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        taskList.add(new Todo(description));  // adds the new task into "tasks" arraylist
        int listSize = taskList.getListSize();
        int taskIndex = listSize - 1;
        taskList.getTask(taskIndex).addNewTaskMessage();
        storage.save(listSize, taskList.taskArrayList);
    }
}
