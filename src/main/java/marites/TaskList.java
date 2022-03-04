package marites;

import java.util.ArrayList;
import java.util.stream.Collectors;

import marites.exception.TaskListOutOfBoundsException;
import marites.task.Task;

/**
 * Class for managing the task list.
 */
public class TaskList implements java.io.Serializable {
    private final ArrayList<Task> taskList;

    /**
     * Returns an ArrayList<Task> representing the task list.
     * @return The task list.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Return the number of tasks in the list.
     * @return Task count
     */
    public int getTaskCount() {
        return taskList.size();
    }

    /**
     * Adds a task to the list.
     * @param task The task to add.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Obtains the task at a specific index.
     * @param taskIndex Task index to locate
     * @return The task at the specified index.
     * @throws TaskListOutOfBoundsException if the index is out of bounds.
     */
    public Task getTaskByIndex(int taskIndex) throws TaskListOutOfBoundsException {
        try {
            return taskList.get(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskListOutOfBoundsException();
        }
    }

    /**
     * Sets the status of a task using its index.
     * @param taskIndex Index of task to modify
     * @param isDone New task status
     * @throws TaskListOutOfBoundsException if the index is out of bounds.
     */
    public void setTaskStatus(int taskIndex, boolean isDone) throws TaskListOutOfBoundsException {
        Task task = getTaskByIndex(taskIndex);
        task.setDone(isDone);
    }

    /**
     * Delete a task using its index.
     * @param taskIndex Index of task to delete
     * @throws TaskListOutOfBoundsException if the index is out of bounds.
     */
    public void deleteTask(int taskIndex) throws TaskListOutOfBoundsException {
        try {
            taskList.remove(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskListOutOfBoundsException();
        }
    }

    /**
     * Finds tasks whose descriptions contain the given query string.
     * @param query The string to look for.
     * @return An ArrayList<Task> representing a list of matching tasks.
     */
    public ArrayList<Task> findTasks(String query) {
        return taskList.stream()
                .filter(task -> task.isMatch(query))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Finds the index of a task.
     * @param task Task to locate
     * @return The index of the task.
     */
    public int getTaskIndex(Task task) {
        return taskList.indexOf(task);
    }
}
