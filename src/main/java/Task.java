public class Task {
    protected String command;
    protected boolean isDone;

    public Task(String command) {
        this.command = command;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone(String command){
        isDone = true;
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + getStatusIcon() + "]" + command);
    }
    public void markAsNotDone(String command){
        isDone = false;
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println("[" + getStatusIcon() + "]" + command);
    }
}
