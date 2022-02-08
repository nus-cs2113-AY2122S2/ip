package duke;

public class Task {
    String title;
    boolean isDone;

    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getStatusIcon() {
        return (isDone? "X" : " ");
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString()
    {
        return String.format("[%s] %s",this.getStatusIcon(),this.getTitle());
    }
}
