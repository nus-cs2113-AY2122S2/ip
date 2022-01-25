public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        System.out.println("\nSuccessfully added to list:\n" + addIndentation() + getTask());
    }

    public void markAsDone() {
        isDone = true;
        System.out.println("Congrats! You've completed:\n" + addIndentation() + getTask());
    }

    public void markAsNotDone() {
        isDone = false;
        System.out.println("Awww, you've marked this as undone:\n" + addIndentation() + getTask());
    }

    public String getTask() {
        return getStatusIcon() + " " + getDescription(); // get status of task as well as description
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "☑" : "☐"); // mark done task with tick
    }

    public static String addIndentation() {
        return "    "; // adds an indentation of 4 spaces
    }
}