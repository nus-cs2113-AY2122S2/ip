package duke.task;
import java.util.ArrayList;

public class TaskList {

    private int taskCount = 0;
    private ArrayList<Task> taskList = new ArrayList<Task>(0);

    public TaskList() {
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount=taskCount;
    }

    public void add(Task task){
        taskList.add(task);
        taskCount++;
    }

    public void remove(int index){
        taskList.remove(index);
        taskCount--;
    }

    public Task get(int index){
        return taskList.get(index);
    }

}
