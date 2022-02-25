package duke.task;

public abstract class Task {

    public String description;
    public Boolean isDone = false;
    public String mark = " ";

    public Task(String description) {
        this.description = description;
    }

    public void setDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String markIsDone() {
        if (this.isDone()) {
            this.mark = "X";
        } else {
            this.mark = " ";
        }
        return this.mark;
    }

    public abstract String getType();

    public abstract String getDate();

    @Override
    public String toString() {
        return String.format("[%s] %s", this.markIsDone(), this.description);
    }
}