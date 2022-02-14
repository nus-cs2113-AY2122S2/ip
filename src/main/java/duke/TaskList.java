package duke;
import duke.task.Task;
import java.util.ArrayList;

/**
 * The controller for ArrayList of tasks used in Duke
 */
public class TaskList {
    private ArrayList<Task> taskList;
    /**
     * Constructs the class with the tasks ArrayList
     *
     * @param taskList
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    /**
     * Getter for a specific task
     *
     * @param index
     *            Index of task
     * @return Task object
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }
    /**
     * Getter for task list
     *
     * @return ArrayList of tasks
     */

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public int getSize() {
        return taskList.size();
    }
    /**
     * Control adding a new task to the list
     *
     * @param task
     */
    public void addTask(Task task) {
        taskList.add(task);
    }
    /**
     * Control removing of tasks from the list
     *
     * @param task
     */
    public void removeTask(Task task) {
        taskList.remove(task);
    }
    /**
     * Marks a task as done or not
     *
     * @param task
     *            Task to be marked
     * @param isDone
     *            Status of task
     */
    public void doneTask(Task task, boolean isDone) {
        task.setDone(isDone);
    }
}
