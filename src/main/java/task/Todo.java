package task;

public class Todo extends Task {

    private boolean isDone;

    public Todo(String description) {
        super(description);
        this.type = "T";

    }
    public boolean isDone() {
        return this.isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    //need redo
    public String toString() {
        return super.toString();
    }

    /**
     * Get task name of task type todo
     *
     * @param taskDetails string to retrieve task name from
     *
     */
    public static String getToDoTask(String taskDetails) {
        int spaceIndex = taskDetails.indexOf(" ");
        String taskName = taskDetails.substring(spaceIndex).trim();
        if (taskName.length() == 0){
            throw new StringIndexOutOfBoundsException();
        }
        return taskName.trim();
    }
}
