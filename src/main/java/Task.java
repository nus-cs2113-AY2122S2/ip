public class Task {
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

    public String getDescription() {
        return description;
    }

    public static void setNoOfItems(int noOfItems) {
        Task.noOfItems = noOfItems;
    }



    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void setDone() {
        isDone = true;
    }
    public void setNotDone() {
        isDone = false;
    }
}
