package Managers;

import java.util.ArrayList;

import Components.Task;

import Exceptions.MaxTaskException;

import java.io.IOException;

import static Constants.TaskManagerConstants.MAX_TASKS;

/**
 * Contains task list and methods to make changes to the task list or to tasks in the task list.
 */
public class TaskManager {
    private ArrayList<Task> tasks;
    private int numTasks;
    private Storage storage;

    /**
     * Creates <code>TaskManager</code> object with task list loaded from storage manager.
     *
     * @param storage Data file storage manager.
     */
    public TaskManager(Storage storage) {
        numTasks = 0;
        this.storage = storage;
        try {
            loadTasklist();
        } catch (IOException e) {
            System.out.println("Loading failed. Let's start on a clean slate.");
        }
    }

    /**
     * Adds task to task list.
     *
     * @param task <code>Task</code> object to add to task list.
     * @throws MaxTaskException task list is at maximum capacity of tasks.
     */
    public void addTask(Task task) throws MaxTaskException {
        try {
            if (numTasks == MAX_TASKS) {
                throw new MaxTaskException("Max tasks reached");
            }

            numTasks++;
            tasks.add(task);
            saveTasklist();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Removes task from task list.
     *
     * @param index Index (in task list) of task to delete.
     * @return Removed <code>Task</code> object.
     * @throws NumberFormatException an integer is not given after the command keyword.
     */
    public Task deleteTask(int index) throws NumberFormatException {
        try {
            Task deletedTask = tasks.remove(index);
            numTasks--;
            saveTasklist();
            return deletedTask;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Marks task in task list as done.
     *
     * @param index Index (in task list) of task to mark.
     * @return String of updated task information.
     * @throws NumberFormatException an integer is not given after the command keyword.
     * @throws IndexOutOfBoundsException no task exists with given index.
     */
    public String markTask(int index) throws NumberFormatException, IndexOutOfBoundsException {
        try {
            tasks.get(index).setIsDone(true);
            saveTasklist();
            return tasks.get(index).toString();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Marks task in task list as undone.
     *
     * @param index Index (in task list) of task to unmark.
     * @return String of updated task information.
     * @throws NumberFormatException an integer is not given after the command keyword.
     * @throws IndexOutOfBoundsException no task exists with given index.
     */
    public String unmarkTask(int index) throws NumberFormatException, IndexOutOfBoundsException {
        try {
            tasks.get(index).setIsDone(false);
            saveTasklist();
            return tasks.get(index).toString();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Displays tasks in task list.
     */
    public void listTasks() {
        for (int i = 0; i < numTasks; i++) {
            System.out.println(i+1 + ". " + tasks.get(i).toString());
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * @return Number of tasks in task list.
     */
    public int getNumTasks() {
        return numTasks;
    }

    /**
     * Calls <code>storage</code> method to load task list from data file.
     *
     * @throws IOException data file cannot be read.
     */
    private void loadTasklist() throws IOException {
        try {
            tasks = storage.loadTasklist();
            numTasks = tasks.size();
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Calls <code>storage</code> method to save task list into data file.
     */
    private void saveTasklist() {
        try {
            storage.saveTasklist(tasks);
        } catch (IOException e) {
            System.out.println("Saving failed.");
        }
    }
}
