public class Task {
    private String taskName;
    private Boolean isMarked = false;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isMarked = false;
    }

    public void setMark() {
        this.isMarked = true;
    }

    public void unMark() {
        this.isMarked = false;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " +
                this.taskName;
    }

    public String getStatusIcon() {
        if (this.isMarked) {
            return "X";
        } else {
            return " ";
        }
    }
}
