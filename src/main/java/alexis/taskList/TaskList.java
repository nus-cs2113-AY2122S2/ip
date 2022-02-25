package alexis.taskList;

import alexis.task.Task;

import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> taskArrayList;
    protected static int listSize = 0;

    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
        listSize = taskArrayList.size();
    }

    public TaskList() {
        this.taskArrayList = new ArrayList<>(100);
        listSize = 0;
    }

    public int getListSize() {
        return listSize;
    }

    public Task getTask(int taskNumber) {
        return taskArrayList.get(taskNumber);
    }

    public void add(Task newTask) {
        taskArrayList.add(newTask);
        listSize++;
    }

    public void remove(int taskNumber) {
        taskArrayList.remove(taskNumber);
        listSize--;
    }

}
