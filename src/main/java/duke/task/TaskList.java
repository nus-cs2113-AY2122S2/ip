package duke.task;
import java.util.ArrayList;

/**
 * Represents a list which stores objects of type <code>Task</code> or its subclasses.
 * A <code>TaskList</code> object corresponds to a
 * data structure represented by an <code>ArrayList</code> object that would contain the tasks recorded thus far
 * and an integer <code>taskCount</code> that tracks the number of tasks recorded.
 * e.g., <code>{task1, task2, task3},3</code>
 */


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

    public void add(Task task) {
        taskList.add(task);
        taskCount++;
    }

    public void remove(int index) {
        taskList.remove(index);
        taskCount--;
    }

    public Task get(int index){
        return taskList.get(index);
    }

}
