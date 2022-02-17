package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    @Override
    public String saveString() {
        return "T" + super.saveString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
