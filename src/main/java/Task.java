public class Task {
    protected String description;
    protected boolean isDone;

    public String returnDescription() {
        return this.description;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        System.out.println("-----------------------------------------------------");
        System.out.println("added: " + description);
        System.out.println("-----------------------------------------------------");
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("-----------------------------------------------------");
        System.out.println("Great! I've marked this task as done:");
        System.out.println("  [" + this.getStatusIcon() + "] " + this.returnDescription());
        System.out.println("-----------------------------------------------------");
    }

    public void markAsUndone() {
        this.isDone = false;
        System.out.println("-----------------------------------------------------");
        System.out.println("Ok. I've marked this task as not done yet:");
        System.out.println("  [" + this.getStatusIcon() + "] " + this.returnDescription());
        System.out.println("-----------------------------------------------------");
    }

}
