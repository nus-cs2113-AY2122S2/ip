package duke;

public class Deadline extends Task {
    private final String date;
    private static final String ICON = "D";

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public boolean equals(Object task) {
        if (!(task instanceof Deadline)) {
            return false;
        }
        return super.equals(task);
    }

    @Override
    public String toStringInSaveFormat() {
        return ICON + " | " + super.toStringInSaveFormat() + " | " + date;
    }

    @Override
    public String toString() {
        String taskIcon = "[" + ICON + "]";
        String dateString = String.format(" (by: %s)", date);
        return taskIcon + super.toString() + dateString;
    }
}
