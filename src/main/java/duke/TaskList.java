package duke;

import task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * A public constructor to initialize the TaskList by
     * giving the list of tasks
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * A method to delete task from taskList
     * @param taskOrder
     */
    public void delete(int taskOrder) {
        tasks.remove(taskOrder - 1);
    }

    /**
     * A method to add task to taskList
     * @param task
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * A method to return the size of the taskList
     * @return The size of the taskList
     */
    public int amountOfTasks() {
        return tasks.size();
    }

    /**
     * A method to get task from the taskList
     * @param n the index of the task to be gotten
     * @return The gotten task
     */
    public Task getTask(int n) {
        return tasks.get(n - 1);
    }
    /**
     * A method to return the size of the taskList
     * @return The size of the taskList
     */
    public int size() {
        return tasks.size();
    }

    /**
     * A method to convert taskList to String format
     * @return
     */
    @Override
    public String toString() {
        int n = tasks.size();
        String allTasks = "Tasks in your list: \n";
        for(int i = 0; i < n; i++) {
            allTasks += String.format("%d. %s\n", i + 1, tasks.get(i).toString());
        }
        return allTasks;
    }
}