public class Task {
    private String taskInfo;
    private boolean isDone;

    public Task(String s, boolean isDone){
        this.taskInfo = s;
        this.isDone = isDone;
    }

    public Task(String s){
        this(s, false);
    }

    public String getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(String taskInfo) {
        this.taskInfo = taskInfo;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        String completion = " ";
        if (isDone){
            completion = "X";
        }
        return "["+completion+"] "+taskInfo;
    }
}
