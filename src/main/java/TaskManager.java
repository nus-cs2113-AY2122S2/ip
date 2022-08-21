import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private static List<Task> tasks = new ArrayList<Task>();
    private int taskCount = 0;

    public void addNewTask(String taskName) {
        tasks.add(new Task(taskName));
        System.out.println("Added new task: " + taskName);
        System.out.println("____________________");
        taskCount += 1;
    }

    public void listTasks() {
        System.out.println("LIST OF TASKS");
        for (int i = 0; i < tasks.size(); i++) {
            char isDone = tasks.get(i).getIsDone() ? 'X' : ' ';
            System.out.println((i + 1) + ". [" + isDone + "] " + tasks.get(i).getTaskName());
        }
        System.out.println("____________________");
    }

    public void markTasks(Boolean isDone, int taskIndex) {
        if (taskIndex < 1 || taskIndex > taskCount) {
            System.out.println("Invalid task indicated, unable to process request\n____________________");
            return;
        }
        if (isDone) {
            if (tasks.get(taskIndex - 1).getIsDone()) {
                System.out.println("This task has already been marked done!\n____________________");
                return;
            }
            tasks.get(taskIndex - 1).setIsDone();
            System.out.println(tasks.get(taskIndex - 1).getTaskName() + " has been marked as done!\n____________________");
        }
        else {
            if (!tasks.get(taskIndex - 1).getIsDone()) {
                System.out.println("This task has already been unmarked!\n____________________");
                return;
            }
            tasks.get(taskIndex - 1).setIsNotDone();
            System.out.println(tasks.get(taskIndex - 1).getTaskName() + " has been unmarked!\n____________________");
        }
    }
}