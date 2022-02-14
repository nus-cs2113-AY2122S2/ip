public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getDescription(){
        return description;
    }
    public String getString(){
        String done = "0";
        if (isDone){
            done = "1";
        }
        return ("T," + done + "," + description);
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone(boolean done) {
        isDone = done;
    }
    public void printTask(){
        System.out.println("[" + this.getStatusIcon() + "] " + this.description);
    }
}