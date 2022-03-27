package task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon(){
        return (isDone ? "X" : "0");
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone(){
        this.isDone=true;
    }

    public void markAsUndone(){
        this.isDone=false;
    }

    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }

    public String getTypeIcon(){return null;}

    public String getTime(){return null;}


}
