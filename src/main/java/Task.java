public class Task {
    private String description;
    private boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon(){
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone(){
        isDone = false;
    }

    public String getTaskEntryString(){
        return "[" + getStatusIcon() + "] " + description;
    }
}
