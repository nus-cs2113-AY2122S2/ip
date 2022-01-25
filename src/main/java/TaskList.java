import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public String addTask(String taskDescription) {
        Task newTask = new Task(taskDescription);
        tasks.add(newTask);
        return String.format("added: %s", newTask.toString());
    }

    public String toString() {
        String taskListString = "";
        for (int i = 1; i <= tasks.size(); i++) {
            taskListString += String.format("%d. %s", i, tasks.get(i - 1));
            if (i != tasks.size()) {
                taskListString += "\n";
            }
        }
        return taskListString;
    }
}
