public class TaskManager {
    private final Task[] tasks = new Task[100];
    private int tasksCount = 0;

    public TaskManager() {
    }

    public void addTask(String description) {
        this.tasks[this.tasksCount] = new Task(description);
        System.out.println("\t____________________________________________________________");
        System.out.println("\tadded: " + this.tasks[this.tasksCount].description);
        System.out.println("\t____________________________________________________________");
        ++this.tasksCount;
    }

    public void printTaskList() {
        System.out.println("\t____________________________________________________________");
        for (int i=0; i<tasksCount; i++) {
            System.out.println("\t" + (i+1) + "." + this.tasks[i].getStatusIcon() + " " + this.tasks[i].getDescription());
        }
        System.out.println("\t____________________________________________________________");
    }

    public void markTask (int taskIndex) {
        this.tasks[taskIndex].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.tasks[taskIndex].getStatusIcon() + " " + this.tasks[taskIndex].getDescription());
    }

    public void unmarkTask (int taskIndex) {
        this.tasks[taskIndex].unmarkAsDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.tasks[taskIndex].getStatusIcon() + " " + this.tasks[taskIndex].getDescription());
    }

    public void showWelcomeMessage() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello I'm Brave");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
    }

}
