public class Task {

    protected String description;
    protected String deadline;
    protected String event;
    protected boolean isDone;
    protected char taskType;
    protected  boolean isDeadline;
    protected  boolean isEvent;

    public char getTaskType() {
        return taskType;
    }



    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = 'N';
        this.isDeadline = false;
        this.isEvent = false;
    }

    public String getStatusIcon() {
        if (isDone == true) {
            return "X";
        }
        else {
            return " ";
        }
    }
}
