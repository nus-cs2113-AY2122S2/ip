package brave;

import java.util.ArrayList;

public class TaskManager {
//    public static final int MAX_TASK = 100;
    public static final String LINE_SPLIT = "\t____________________________________________________________";
    private final ArrayList<Task> tasks = new ArrayList<>();
//    private final Task[] tasks = new Task[MAX_TASK];
//    private int tasksCount = 0;

    public TaskManager() {
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println(LINE_SPLIT);
        System.out.println("\tYepp, I have added task below!");
        System.out.println("\t" + task.description);
        System.out.println("\tYou currenly have " + tasks.size() + " task in the list");
        System.out.println(LINE_SPLIT);
    }

    public void printTaskList() {
        System.out.println(LINE_SPLIT);
        for (Task task : tasks) {
            System.out.println(String.format("\t%d.%s %s", tasks.indexOf(task) + 1, task.getStatusIcon(), task.getDescription()));
        }
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

    public void showWelcomeMessage() {
        System.out.println(LINE_SPLIT);
        System.out.println("\tHello I'm Brave");
        System.out.println("\tWhat can I do for you?");
        System.out.println(LINE_SPLIT);
    }

    public void showFarewellMessage() {
        System.out.println(LINE_SPLIT);
        System.out.println("\tBye, Hope to see you again soon!");
        System.out.println(LINE_SPLIT);
    }
}
