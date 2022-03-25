package duke.tasks;
/**
 * Represents a Task object on a person's list of tasks that they must complete.
 * A <code>Task</code> corresponds to a Task represented by a description of the
 * task as well as a flag for whether it has been completed.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public boolean getStatus() { return isDone; }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void markAsNotDone(){
        this.isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String saveTasks() {
        int isMarked = 0;
        if(this.isDone) {
            isMarked = 1;
        }
        return "| " + isMarked + " | " + this.description ;
    }
}