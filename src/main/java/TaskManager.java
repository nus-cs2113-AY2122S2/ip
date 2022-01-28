public class TaskManager {
    private Task[] tasks = new Task[100];
    private int tasksCount = 0;

    public void addTask(String description) {
        tasks[tasksCount] = new Task(description);
        tasksCount++;
        System.out.println(" added: " + description);
    }

    public void markAsDone(int taskIndex) {
        if (tasks[taskIndex].getStatus() == false) {
            tasks[taskIndex].markAsDone();
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("    [" + tasks[taskIndex].getStatusIcon() + "] " + tasks[taskIndex].getDescription());
        }
    }

    public void unmarkAsNotDone(int taskIndex) {
        if (tasks[taskIndex].getStatus() == true) {
            tasks[taskIndex].unmarkAsNotDone();
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("    [" + tasks[taskIndex].getStatusIcon() + "] " + tasks[taskIndex].getDescription());
        }
    }

    public void listTasks() {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasksCount; i++) {
            System.out.println(" " + (i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
        }
    }
}
