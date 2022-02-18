package aeon.task;

/**
 * An abstract class of a task, as all tasks need to belong in one of the 3 subclasses of Deadline, Event or Todo
 * This class also keeps track of the total number of tasks in the list already
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    private static int noOfItems = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static int getNoOfItems() {
        return noOfItems;
    }

    public static void setNoOfItems(int noOfItems) {
        Task.noOfItems = noOfItems;
    }

    public void setDoneStatus(boolean isDone) {
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
}
