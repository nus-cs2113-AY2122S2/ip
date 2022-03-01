package duke;

import java.util.ArrayList;

/**
 * Represents the list of tasks created by user
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int taskCount;

    /**
     * Creates an empty TaskList
     */
    public TaskList(){
        tasks = new ArrayList<>();
        taskCount = 0;
    }

    /**
     * Increment the count of tasks
     */
    public void incrementTasks(){
        this.taskCount++;
    }

    /**
     * Decrement the count of tasks
     */
    public void decrementTasks(){
        this.taskCount--;
    }

    /**
     * Getter for the taskCount
     * @return number of tasks in list
     */
    public int getTaskCount(){
        return this.taskCount;
    }

    /**
     * Getter for ArrayList of tasks
     * @return ArrayList of tasks
     */
    public ArrayList<Task> getTasks(){
        return this.tasks;
    }

    /**
     * Add a new Task into the ArrayList of tasks
     * @param task new Task to be added
     */
    public void addTask(Task task){
        tasks.add(task);
        incrementTasks();
    }

    /**
     * Delete a Task in the current ArrayList of tasks
     * @param taskIndex index of Task to be deleted
     */
    public void deleteTask(int taskIndex){
        tasks.remove(taskIndex);
        decrementTasks();
    }
}
