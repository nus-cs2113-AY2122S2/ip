package duke;

import java.util.ArrayList;
import duke.task.Task;

public class TaskList {
    private ArrayList<Task> taskList;
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public int getSize() {
        return taskList.size();
    }

    public void removeTask(Task task) {
        taskList.remove(task);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void markTask(Task task, boolean isDone) {
        if(isDone){
            task.setMark();
        }
        else{
            task.setUnmark();
        }
    }

}