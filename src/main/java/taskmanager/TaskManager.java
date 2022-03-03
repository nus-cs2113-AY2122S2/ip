package taskmanager;

import tasks.Task;

import java.util.ArrayList;

public class TaskManager {

    protected static int taskCount;
    protected static ArrayList<Task> tasks = new ArrayList<Task>();

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

    public static void addTask(Task t){
        tasks.add(t);
        taskCount++;
    }

}
