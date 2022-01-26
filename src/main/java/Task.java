public class Task {

    protected String taskName;
    protected boolean isDone;
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
        return isDone;
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
}
