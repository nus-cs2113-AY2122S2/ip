public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markDone(){
        System.out.println("Nice! I've marked this task as done:");
        this.isDone = true;
    }

    public void unmarkDone(){
        System.out.println("OK, I've marked this task as not done yet:");
        this.isDone = false;
    }

    public String getTask(){
       return "[" + this.getStatusIcon() + "] " + this.description; 
    }
}
