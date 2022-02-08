import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public int getSize() {
        return tasks.size();
    }

    @Override
    public String toString() {
        String taskListString = "Here are the tasks in your list:\n";
        for (int i = 1; i <= tasks.size(); i++) {
            taskListString += String.format("%d. %s", i, tasks.get(i - 1));
            if (i != tasks.size()) {
                taskListString += "\n";
            }
        }
        return taskListString;
    }
}
