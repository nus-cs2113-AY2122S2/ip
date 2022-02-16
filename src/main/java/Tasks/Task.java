package Tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean getStatus(){return isDone;}

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description1){
        description=description1;
    }

    public void markAsDone(){
        isDone=true;
    }

    public void markAsUndone(){
        isDone=false;
    }
    public String toString(){
        return "["+getStatusIcon()+"] "+description;
    }
    public String getType(){return null;}
}
