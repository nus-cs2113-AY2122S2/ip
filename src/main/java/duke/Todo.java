package duke;

public class Todo extends Task {
    private static final String ICON = "T";

    public Todo(String description) {
        super(description);
    }

    @Override
    public boolean equals(Object task) {
        if (!(task instanceof Todo)) {
            return false;
        }
        return super.equals(task);
    }

    @Override
    public String toStringInSaveFormat() {
        return ICON + " / " + super.toStringInSaveFormat();
    }

    @Override
    public String toString() {
        String taskIcon = "[" + ICON + "]";
        return taskIcon + super.toString();
    }
}
