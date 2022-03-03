package brave.data;

import brave.ui.Ui;

import java.util.ArrayList;

/**
 * This class is used to manage the entire list of task
 */
public class TaskManager {
    private final ArrayList<Task> tasks;
    private Ui ui = new Ui();

    /**
     * Class Constructor, initialise an empty array list if no file found
     */
    public TaskManager() {
        tasks = new ArrayList<>();
    }
    /**
     * Class Constructor, initialise an array of list from the file
     */
    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Add a new task to the task list.
     *
     * @param task the new task
     */
    public void addTask(Task task) {
        tasks.add(task);
        ui.showAddTaskMessage(task, tasks.size());
    }

    /**
     * Mark the task as finished
     *
     * @param taskIndex index of the task
     */
    public void markTask(int taskIndex) {
        Task selected_task = tasks.get(taskIndex);
        selected_task.markAsDone();
        ui.showMarkTaskMessage(selected_task);
    }

    /**
     * Mark the task as unfinished
     *
     * @param taskIndex index of the task
     */
    public void unmarkTask(int taskIndex) {
        Task selected_task = tasks.get(taskIndex);
        selected_task.unmarkAsDone();
        ui.showUnmarkTaskMessage(selected_task);
    }

    /**
     * Delete the task with a specific index.
     *
     * @param taskIndex the index of the task to be deleted
     */
    public void deleteTask(int taskIndex)  {
        Task selected_task = tasks.get(taskIndex);
        ui.showDeleteTaskMessage(selected_task, tasks.size());
        tasks.remove(taskIndex);
    }
}
