package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private int taskCount;

    public TaskList(){
        tasks = new ArrayList<>();
        taskCount = 0;
    }

    public void incrementTasks(){
        this.taskCount++;
    }

    public void decrementTasks(){
        this.taskCount--;
    }

    public int getTaskCount(){
        return this.taskCount;
    }

    public ArrayList<Task> getTasks(){
        return this.tasks;
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public void deleteTask(Task task){
        tasks.remove(task);
    }
}
