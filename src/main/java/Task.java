public class Task {
    private String task;
    private boolean isDone;

    public Task(String t){
        task = t;
        isDone = false;
    }
    public String toString(){
        if(this.isDone()){
            return ("[T][X] " + this.getTask() + "\n");
        }else{
            return ("[T][ ] " + this.getTask() + "\n");
        }
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
