public class Task {

    protected String taskName;
    protected boolean isDone;
    protected String type = "Y";

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Task(String taskName){
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getStatusIcon(){
        if (isDone){
            return "X";
        }
        else{
            return " ";
        }
    }
    @Override
    public String toString() {

        return "[" + this.type + "][" + this.getStatusIcon() + "] " + this.taskName;
    }
}
