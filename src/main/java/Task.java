public class Task {
    protected String description;
    protected boolean isDone;
    protected static int currentIndex = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public static int getCurrentIndex() {
        return currentIndex;
    }

    public boolean isDone() {
        return isDone;
    }

    public static void increaseIndex() {
        if (currentIndex < 100) {
            currentIndex++;
        } else {
            System.out.println("Sorry! You've reached the"
                    + "maximum amount of tasks"
                    + "allowed on your task list");

        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }
}
