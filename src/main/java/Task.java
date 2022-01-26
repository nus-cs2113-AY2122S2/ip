public class Task {
    protected String description;
    protected boolean isDone;
    private static int taskCount = 0;
    public String lineSeparator = "____________________________________________________________\n";

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

    public void markAsDone(int taskNumber, Task[] taskList) {
            isDone = true;
            System.out.println(lineSeparator);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[" + taskList[taskNumber-1].getStatusIcon() + "] " + taskList[taskNumber-1].getDescription());
            System.out.println(lineSeparator);
    }

    public void markAsUndone(int taskNumber, Task[] taskList) {
        isDone = false;
        System.out.println(lineSeparator);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[" + taskList[taskNumber-1].getStatusIcon() + "] " + taskList[taskNumber-1].getDescription());
        System.out.println(lineSeparator);
    }
}
