package boba.task;

import boba.exception.BobaException;
import java.util.ArrayList;
import java.util.Iterator;

public class TaskList implements Iterable<Task> {

    /** The limit on how many tasks can be in the list */
    private static final int TASK_LIMIT = 100;
    /** Keeps track on how many tasks are in the list*/
    private int taskCount;
    /** The list of tasks. Limited to 100 */
    private ArrayList<Task> taskList;

    /**
     * Default Constructor
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
        taskCount = 0;
    }

    /**
     * Constructor for saved file
     * @param taskList
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        taskCount = taskList.size();
    }

    /**
     * Marks a task as complete or incomplete.
     * @param isDone Whether task is completed
     * @param index Index of the task we want to mark
     * @throws BobaException Index out of bounds
     */
    public Task markTask(boolean isDone, int index) throws BobaException{
        // The task list is 1 base indexing while the array itself is 0 base indexing
        if (index < 0 || index >= taskCount) {
            // Marking outside the range is not allowed
            throw new BobaException();
        }
        Task selectedTask = taskList.get(index);
        if (isDone) {
            selectedTask.markAsDone();
        } else {
            selectedTask.markAsNotDone();
        }
        return selectedTask;
    }

    /**
     * Add a task to the list of tasks.
     * Does not add a task if we are at the limit.
     * @param task The new task to be added to the list
     */
    public void addTask(Task task) throws BobaException {
        if (taskCount == TASK_LIMIT) {
            throw new BobaException();
        } else {
            taskList.add(task);
            taskCount++;
        }
    }

    /**
     * Removes the Task at a given index
     * @param index Index of task we want to remove
     * @throws BobaException Index out of bounds
     */
    public Task removeTask(int index) throws BobaException {
        if (index < 0 || index >= taskCount) {
            // deleting outside the range is not allowed
            throw new BobaException();
        }
        taskCount--;
        return taskList.remove(index);
    }

    /**
     * @param index Index of task
     * @return Task of given index
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * @return the size of the taskList
     */
    public int size() {
        return taskCount;
    }

    /**
     * Fina all tasks description that contains the keyword
     * @param keyword String to find
     * @return list of tasks
     */
    public TaskList findTask(String keyword) {
        TaskList result = new TaskList();
        for (Task task : taskList) {
            if (task.getDescription().contains(keyword)) {
                try {
                    result.addTask(task);
                } catch (BobaException e) {
                    System.out.println("Code shouldn't reach here, can't be more 100 items");
                }
            }
        }
        return result;
    }

    /**
     * Iterator method for taskList
     * @return
     */
    @Override
    public Iterator<Task> iterator() {
        return taskList.iterator();
    }
}
