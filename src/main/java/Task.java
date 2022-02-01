public class Task {
    protected String description;
    protected boolean isDone;
    protected String icon = "Null";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void markAsNotDone(){
        this.isDone = false;
    }

    public String getIcon() {
        return icon;
    }
    public String getBy(){
        return "";
    }

    public String getAt() {
        return "";
    }
}
