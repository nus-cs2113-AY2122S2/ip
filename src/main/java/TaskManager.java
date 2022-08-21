import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private static List<Task> tasks = new ArrayList<Task>();

    public void addNewTask(String taskName) {
        tasks.add(new Task(taskName));
        System.out.println("Added new task: " + taskName);
        System.out.println("____________________");
    }

    public void listTasks() {
        System.out.println("TASKS TO BE COMPLETED");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).getTaskName());
        }
        System.out.println("____________________");
    }
}