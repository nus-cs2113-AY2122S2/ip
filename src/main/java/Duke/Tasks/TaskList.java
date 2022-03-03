package Duke.Tasks;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

/**
 * Represents the list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> listArray) {
        this.tasks = listArray;
    }

    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Adds a task to the list.
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Represents the size of the task list.
     *
     * @return size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Marks the task as done.
     *
     * @param index Index of the task to be marked.
     */
    public void markItem(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * Unmarks the task as done.
     *
     * @param index Index of the task to be unmarked.
     */
    public void unMarkItem(int index) {
        tasks.get(index).unMark();
    }

    /**
     * Represents the task.
     *
     * @param index Index of the task.
     * @return The task.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Deletes the task from the list.
     *
     * @param index Index of the task to delete.
     */
    public void delete(int index) {
        tasks.remove(index);
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if list is empty, false otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public TaskList findItem (String description) {
        ArrayList<Task> filteredTask = (ArrayList<Task>) tasks.stream()
                .filter((t) -> t.getDescription().contains(description))
                .collect(toList());
        TaskList newTaskList = new TaskList(filteredTask);
        return newTaskList;
    }
}
