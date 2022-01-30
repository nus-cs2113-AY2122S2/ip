public class Task {
    private String taskName;
    private Boolean isMarked = false;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isMarked = false;
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
