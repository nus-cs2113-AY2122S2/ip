package Duke.Tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, int status) {
        this.description = description;
        if (status == 1) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public boolean markAsDone() {
        return isDone = true;
    }

    public boolean unMark() {
        return isDone = false;
    }

    public String saveTasks() {
        int isMarked = 0;
        if (this.isDone) {
            isMarked = 1;
        }
        return "| " + isMarked + " | " + this.description ;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

