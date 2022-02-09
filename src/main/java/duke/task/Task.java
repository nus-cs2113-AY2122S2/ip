package duke.task;

public class Task {
    private String name;
    private boolean isCompleted;

    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public void setCompleted(boolean completion) {
        this.isCompleted = completion;
    }

    public String toString() {
        return String.format("[%s] %s", (this.isCompleted? "X" : " "), this.name);
    }

}
