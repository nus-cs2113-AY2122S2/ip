package duke.task;

public class Task {
    private String name;
    private boolean isCompleted;

    /**
     * Creates a task
     * @param name name of task
     */
    public Task(String name)  {
        this.name = name;
        this.isCompleted = false;
    }

    /**
     * Returns name of task
     * @return task name
     */
    public String getName() {
        return this.name;
    }


    /**
     * Mark task as complete/incomplete
     * @param completion completion status of task, true as completed, false as not completed
     */
    public void setCompleted(boolean completion) {
        this.isCompleted = completion;
    }

    /**
     * Returns a string representation of the task
     * @return task in the format "[x] task name" (x for completed, " " for incomplete)
     */
    public String toString() {
        return String.format("[%s] %s", (this.isCompleted? "X" : " "), this.name);
    }

}
