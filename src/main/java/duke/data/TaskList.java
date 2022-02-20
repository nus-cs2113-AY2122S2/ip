package duke.data;

import duke.data.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Add a task to the task list.
     * @param t the task to be added.
     */
    public void addTask(Task t) {
        list.add(t);
    }

    /**
     * Remove a task from the task list.
     * @param index the index of the task being removed (zero-indexed).
     * @return the removed task.
     */
    public Task removeTask(int index) {
        Task t = list.get(index);
        list.remove(index);
        return t;
    }

    public Task getTask(int index) {
        return list.get(index);
    }

    public ArrayList<Task> getTaskList() {
        return list;
    }

    /**
     * Counts the total number of tasks in the task list.
     * @return size of the task list.
     */
    public int getNumTasks() {
        return list.size();
    }

    /**
     * Counts the number of tasks associated with a given date.
     * @param date the date to be matched.
     * @return the number of tasks in the task list matching the date.
     */
    public int getNumMatchingTasks(LocalDate date) {
        if (date == null) {
            return getNumTasks();
        }
        int numTasks = 0;
        for (Task t : list) {
            if (t.isMatchingDate(date)) {
                numTasks++;
            }
        }
        return numTasks;
    }

    public int getNumMatchingTasks(String searchString) {
        int numTasks = 0;
        for (Task t : list) {
            if (t.getDescription().contains(searchString)) {
                numTasks++;
            }
        }
        return numTasks;
    }
}
