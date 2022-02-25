import java.io.Serializable;

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

    public void markAsDone() {
        if (isDone) {
            System.out.println("This task has already been marked as done:");
        } else {
            isDone = true;
            System.out.println("Nice! I've marked this task as done:");
        }
        System.out.println(this + System.lineSeparator());
    }

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
