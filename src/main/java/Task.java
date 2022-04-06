import java.io.Serializable;

/**
 * Represents a task that a user needs to do
 * Task has 2 parameters - description that describes the task
 *                       - isDone to indicate if the task has been marked as done
 * Task is serializable so that it can be saved as an object when the programme ends
 * Task is the parent class of ToDo, Deadline, Event
 */
public class Task implements Serializable {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * If the task has not been marked as done yet, mark it as done
     */
    public void markAsDone() {
        if (isDone) {
            System.out.println("This task has already been marked as done:");
        } else {
            isDone = true;
            System.out.println("Nice! I've marked this task as done:");
        }
        System.out.println(this + System.lineSeparator());
    }

    /**
     * If a task has been marked as done, marked it as not done yet
     */
    public void markAsUndone() {
        if (!isDone) {
            System.out.println("This task has not been marked as done yet:");
        } else{
            isDone = false;
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(this + System.lineSeparator());
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
