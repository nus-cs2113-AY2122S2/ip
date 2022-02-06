public class Task {
    private Boolean isMarked = false;
    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void setMark() {
        isMarked = true;
    }

    public void unMark() {
        isMarked = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + taskName;
    }

    public String getStatusIcon() {
        if (isMarked) {
            return "X";
        } else {
            return " ";
        }
    }
}
