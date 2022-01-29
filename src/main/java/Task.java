public class Task {
    private String taskName;
    private Boolean isMarked = false;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isMarked = false;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public void setMark() {
        this.isMarked = true;
    }

    public void unMark() {
        this.isMarked = false;
    }

    public String taskStatus() {
        return "[" + this.getStatusIcon() + "] " +
                this.getTaskName() + "\n";
    }

    public String getStatusIcon() {
        if (this.isMarked) {
            return "X";
        } else {
            return " ";
        }
    }
}
