public class Task {
    private String description;
    private boolean isDone;

    public Task(String t){
        description = t;
        isDone = false;
    }

    @Override
    public String toString(){
        return ("[T][" + getStatusIcon() + "] " + getDescription() + "\n");
    }

    public String getStatusIcon(){
        return isDone() ? "X" : " ";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
