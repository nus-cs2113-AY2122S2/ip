package tasks;

import java.util.ArrayList;

/**
 * This class handles the storing of tasks using ArrayList while the program is running.
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a Task instance into the TaskList.
     *
     * @param task The Task instance
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the size of the TaskList.
     * @return tasks.size()
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns a Task instance based on a given index.
     *
     * @param index The index of the TaskList
     * @return task.get(index)
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Deletes a Task from the TaskList.
     *
     * @param index Index of task to be deleted from TaskList
     */
    public void remove(int index) {
        tasks.remove(index);
    }
}
