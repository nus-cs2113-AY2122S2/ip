public class Task {
    protected String description;
    protected boolean isDone;
    protected String symbol;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        System.out.println("Got it. I've added this task:");
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    public String getSymbol(){
        return symbol;
    }

    public String getDescription(){
        return this.description;
    }

    public void markAsDone(){
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
    }

    public void markAsUndone(){
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
    }

    public String printTask(){
        return "\t" + getSymbol() + getStatusIcon() + getDescription();
    }


}
