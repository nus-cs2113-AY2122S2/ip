package duke.entity;

public class Task {
    protected String description;
    protected boolean isTaskDone;
    protected int taskId;

    private static int taskRunningCount = 1;
    //constructors
    public Task(String description) {
        this.taskId = taskRunningCount;
        this.description = description;
        this.isTaskDone = false; //default when a task is created it is not done
        taskRunningCount++;
    }

    public Task(String description, boolean isTaskDone) {
        this.taskId = taskRunningCount;
        this.description = description;
        this.isTaskDone = isTaskDone; //create task based on user inputted boolean, not default
        taskRunningCount++;
    }

    //mutator methods
    public void markAsDone() {
        this.isTaskDone = true;
        System.out.println("Olivia thanks you for completing the task! :)");
    }
    public void markAsNotDone() {
        this.isTaskDone = false;
        System.out.println("Hey!!! Please get the task done.");
    }

    //accessor methods
    public String getDescription() {
        return description;
    }
    public String getTaskStatus() {
        return (isTaskDone ? "[X] " : "[ ] "); //if task done mark it with X
    }

    public boolean getIsTaskDone() {
        return isTaskDone;
    }



    @Override
    public String toString() {
        return this.getTaskStatus() + this.getDescription();
    }
}


