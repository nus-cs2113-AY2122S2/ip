package brave;

public class TaskManager {
    public static final int MAX_TASK = 100;
    public static final String LINE_SPLIT = "\t____________________________________________________________";
    private final Task[] tasks = new Task[MAX_TASK];
    private int tasksCount = 0;

    public TaskManager() {
    }

    public void addTask(Task description) {
        this.tasks[this.tasksCount] = description;
        System.out.println(LINE_SPLIT);
        System.out.println("\tYepp, I have added task below!");
        System.out.println("\t" + tasks[tasksCount].description);
        System.out.println("\tYou currenly have " + (tasksCount + 1) + " task in the list");
        System.out.println(LINE_SPLIT);
        this.tasksCount++;
    }

    public void printTaskList() {
        System.out.println(LINE_SPLIT);
        for (int i = 0; i < tasksCount; i++) {
            System.out.println(String.format("\t%d.%s %s", i + 1, tasks[i].getStatusIcon(), tasks[i].getDescription()));
        }
        System.out.println(LINE_SPLIT);
    }

    public void markTask(int taskIndex) {
        this.tasks[taskIndex].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[taskIndex].getStatusIcon() + " " + tasks[taskIndex].getDescription());
    }

    public void unmarkTask(int taskIndex) {
        this.tasks[taskIndex].unmarkAsDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.tasks[taskIndex].getStatusIcon() + " " + this.tasks[taskIndex].getDescription());
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
