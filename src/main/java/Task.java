public class Task {
    protected String description;
    protected String timing;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        addNewTask();
    }

    public Task(String description, String timing) {
        this.description = description;
        this.timing = timing;
        this.isDone = false;
        addNewTask();
    }

    public char typeOfTask() {
        return '@';
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String fullDescription() {
        return description;
    }

    public void addNewTask() {
        System.out.println("-----------------------------------------------------");
        System.out.println("Got it! I've added this task:");
        System.out.println("  [" + typeOfTask() + "][" + getStatusIcon() + "] " + fullDescription());
        System.out.println("-----------------------------------------------------");
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("-----------------------------------------------------");
        System.out.println("Great! I've marked this task as done:");
        System.out.println("  [" + typeOfTask() + "][" + getStatusIcon() + "] " + fullDescription());
        System.out.println("-----------------------------------------------------");
    }

    public void markAsUndone() {
        this.isDone = false;
        System.out.println("-----------------------------------------------------");
        System.out.println("Ok. I've marked this task as not done yet:");
        System.out.println("  [" + typeOfTask() + "][" + getStatusIcon() + "] " + fullDescription());
        System.out.println("-----------------------------------------------------");
    }

}
