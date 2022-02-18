package brave;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getStatusIcon() {
        return "[T]" + super.getStatusIcon();
    }

    @Override
    public String getSaveFormat() {
        if (!isDone) {
            return "T , 0 , " + description;
        } else {
            return "T , 1 , " + description;
        }
    }
}