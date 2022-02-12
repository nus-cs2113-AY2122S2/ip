package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String formatAsData(String FS) {
        return "T" + FS + super.formatAsData(FS);
    }

    @Override
    public String toString() {
        return " TODO: " + super.toString();
    }
}
