package duke;
import duke.task.Task;
import java.util.ArrayList;

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

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(Task task) {
        taskList.remove(task);
    }

    public void doneTask(Task task, boolean isDone) {
        task.setDone(isDone);
    }
}
