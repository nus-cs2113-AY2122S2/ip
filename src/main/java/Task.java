public class Task {
    protected String description;
    protected boolean isDone;
    public static final String LINESEPARATOR = "____________________________________________________________\n";
    private static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskCount++;
    }

    public String getDescription() {
        return description;
    }

    public void setTaskDescription(String description) {
        this.description = description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public void markAsDone(int taskNumber, Task[] taskList) {
        isDone = true;
        System.out.print(LINESEPARATOR);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[" + taskList[taskNumber - 1].getStatusIcon() + "] " + taskList[taskNumber - 1].getDescription());
        System.out.print(LINESEPARATOR);
    }

    public void markAsUndone(int taskNumber, Task[] taskList) {
        isDone = false;
        System.out.print(LINESEPARATOR);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[" + taskList[taskNumber - 1].getStatusIcon() + "] " + taskList[taskNumber - 1].getDescription());
        System.out.print(LINESEPARATOR);
    }

    public String toString() {
        if (isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}
