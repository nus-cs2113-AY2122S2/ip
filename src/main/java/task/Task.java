package task;
import java.io.Serializable;

public class Task implements Serializable {

    protected String taskName;
    protected boolean isDone;
    protected String type = "Y";

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        if (isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    @Override
    public String toString() {

        return "[" + this.type + "][" + this.getStatusIcon() + "] " + this.taskName;
    }

    public String getTaskDescription(String input) {
        return " ";
    }

    /**
     * Print task in param neatly
     *
     * @param task task object to print out details
     *
     */
    public static void printTask(Task task) {
        System.out.println(task.toString());
    }

}

