package data;

import common.DukeException;

import java.util.ArrayList;

/**
 * Contains and manages the entire list of tasks.
 */
public class TaskManager {
    private ArrayList<Task> tasks;

    /**
     * Class constructor that creates a new task list.
     */
    public TaskManager() {
        tasks = new ArrayList<>();
    }

    /**
     * Class constructor that creates the task list with the given data.
     *
     * @param tasks the given tasks
     */
    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add a new task to the task list.
     *
     * @param task the new task
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Get all tasks in the task list.
     *
     * @return all tasks in the task list.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Get the total number of tasks stored in the task list.
     *
     * @return the total number of tasks.
     */
    public int getNoOfTasks () {
        return tasks.size();
    }

    /**
     * Get the task with a specific index.
     *
     * @param idx the index of the desired task
     * @return the task at specified index
     * @throws DukeException if the index is out of bound.
     */
    public Task getTask(int idx) throws DukeException {
        if(idx <= 0 || idx > tasks.size()) {
            throw new DukeException("Index out of bound. Failed to get task " + idx + ".");
        }

        return tasks.get(idx - 1);
    }

    /**
     * Delete the task with a specific index.
     *
     * @param idx the index of the task to be deleted
     * @throws DukeException if the index is out of bound.
     */
    public void deleteTask(int idx) throws DukeException{
        if(idx <= 0 || idx > tasks.size()){
            throw new DukeException("Task index out of bound. I cannot delete task " + idx + ".");
        }

        tasks.remove(idx - 1);
    }

    /**
     * Mark the task as done with a specific index.
     *
     * @param idx the index of the task to be marked as done
     * @throws DukeException if the index is out of bound.
     */
    public void markTask(int idx) throws DukeException {
        if(idx <= 0 || idx > tasks.size()){
            throw new DukeException("Task index out of bound. I cannot mark task " + idx + ".");
        }
        tasks.get(idx-1).markAsDone();
    }

    /**
     * Unmark the task as not done with a specific index.
     *
     * @param idx the index of the task
     * @throws DukeException if the index is out of bound.
     */
    public void unmarkTask(int idx) throws DukeException {
        if(idx <= 0 || idx > tasks.size()){
            throw new DukeException("Task index out of bound. I cannot unmark task " + idx + ".");
        }
        tasks.get(idx-1).unmark();
    }

    public ArrayList<Integer> findTask(String keyword) {
        ArrayList<Integer> results = new ArrayList<>();

        for(int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getDescription().contains(keyword)) {
                results.add(i + 1);
            }
        }

        return results;
    }
}
