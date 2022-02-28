public class Event extends Task {
    private String date;
    private static final String ICON = "E";

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public boolean equals(Object task) {
        if (!(task instanceof Event)) {
            return false;
        }
        return super.equals(task);
    }

    @Override
    public String toStringInSaveFormat() {
        return ICON + " / " + super.toStringInSaveFormat() + " / " + date;
    }

    @Override
    public String toString() {
        String taskIcon = "[" + ICON + "]";
        String dateString = String.format(" (at: %s)", date);
        return taskIcon + super.toString() + dateString;
    }
}
