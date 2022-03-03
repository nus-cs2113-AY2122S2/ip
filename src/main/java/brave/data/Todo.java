package brave.data;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getSaveFormat() {
        if (!isDone) {
            return "T , 0 , " + description;
        } else {
            return "T , 1 , " + description;
        }
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}