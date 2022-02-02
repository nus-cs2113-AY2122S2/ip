public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString(boolean isDone) {
        return "[T]" + super.toString(isDone);
    }
}