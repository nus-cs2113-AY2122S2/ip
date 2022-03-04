package baymax.data;

/**
 * abstract class Task
 * the parent of Todo, Deadline, and Event
 */
public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public String getDescription() {
        return description;
    }
    public void markTaskDone() {
        isDone = true;
    }

    public void unmarkTaskDone() {
        isDone = false;
    }

    public String saveInfo(){
        String sep = " / ";
        String printT = sep + (isDone ? "1" : "0") + sep + this.description;
        return printT;
    }

    public String toString(){
        return getStatusIcon() + " " + getDescription();

    }
}
