package duke;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the task with the given index from the list.
     *
     * @param index Index of the task.
     * @return Task.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Adds task to the list
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes task from the list.
     * @param index Index of the task.
     */
    public void delTask(int index) {
        tasks.remove(index);
    }

    /**
     * Returns size of the list.
     * @return Integer representing size of the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Checks if the list is empty.
     * @return Boolean representing whether the list is empty.
     */
    public boolean isEmpty() {
        return tasks.size() == 0;
    }

    @Override
    public String toString() {
        String taskListString = "";
        for (int i = 1; i <= tasks.size(); i++) {
            taskListString += String.format("%d. %s", i, tasks.get(i - 1));
            if (i != tasks.size()) {
                taskListString += "\n";
            }
        }
        return taskListString;
    }
}
