public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;

        System.out.println("\t____________________________________________________________");
        System.out.println("\tadded: " + description);
        System.out.println("\t____________________________________________________________");
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void markAsUnDone(){
        this.isDone = false;
    }

    public void printTask(){
        System.out.println("[" + getStatusIcon() + "] " + this.description);
    }
}