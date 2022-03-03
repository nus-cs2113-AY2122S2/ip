package brave.data;

import brave.storage.Storage;
import brave.ui.Ui;

import java.util.ArrayList;

public class TaskManager {
    public static final String LINE_SPLIT = "____________________________________________________________";
    private final ArrayList<Task> tasks;
    private Ui ui;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println(LINE_SPLIT);
        System.out.println("Yepp, I have added task below!");
        System.out.println(task.description);
        System.out.println("You currenly have " + tasks.size() + " task in the list");
        System.out.println(LINE_SPLIT);
    }

    public void markTask(int taskIndex) {
        Task selected_task = tasks.get(taskIndex);
        selected_task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(selected_task.getStatusIcon() + " " + selected_task.getDescription());
    }

    public void unmarkTask(int taskIndex) {
        Task selected_task = tasks.get(taskIndex);
        selected_task.unmarkAsDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(selected_task.getStatusIcon() + " " + selected_task.getDescription());
    }

    public void deleteTask(int taskIndex) {
        Task selected_task = tasks.get(taskIndex);
        System.out.println(LINE_SPLIT);
        System.out.println("Successfully deleted task below");
        System.out.printf("%d.%s %s%n", taskIndex + 1, selected_task.getStatusIcon(),
                selected_task.getDescription());
        tasks.remove(taskIndex);
        System.out.println("You now have " + tasks.size() + " task remaining");
    }
}
