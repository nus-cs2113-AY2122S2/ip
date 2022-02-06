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

    public void add(Task task) {
        tasks.add(task);
    }

    public int amountOfTasks() {
        return tasks.size();
    }

    public Task getTask(int n) {
        return tasks.get(n - 1);
    }

    public int size() {
        return tasks.size();
    }
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