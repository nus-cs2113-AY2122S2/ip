public class Task {
    protected String description;
    protected boolean isDone;
    private static int noOfItems = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatus() {
        return "X";
    }

    public static int getNoOfItems() {
        return noOfItems;
    }

    public static void setNoOfItems(int noOfItems) {
        Task.noOfItems = noOfItems;
    }

    public void setDone() {
        isDone = true;
    }

    public void setNotDone() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
}
