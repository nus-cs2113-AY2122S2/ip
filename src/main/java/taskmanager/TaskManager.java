package taskmanager;

import tasks.Task;

import java.util.ArrayList;

/**
 * Represents the user's task list
 */
public class TaskManager {

    protected static int taskCount;
    protected static ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Initialises a task manager that either contains the user's task list from the user's hard disk or a new empty task list
     *
     * @param tasks ArrayList of tasks
     */
    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
        taskCount = tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public static void setTaskCount(int newTaskCount) {
        taskCount = newTaskCount;
    }

    /**
     * Adds a task to the array list of tasks
     * Increases the task count
     *
     * @param t New task to be added
     */
    public static void addTask(Task t){
        tasks.add(t);
        taskCount++;
    }

}
