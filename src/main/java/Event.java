public class Event extends Task {
    private String date;
    private static String icon = "E";

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toStringInFormat() {
        return icon + " / " + super.toStringInFormat() + " / " + date;
    }

    @Override
    public String toString() {
        String taskIcon = "[" + icon + "]";
        String dateString = String.format(" (at: %s)", date);
        return taskIcon + super.toString() + dateString;
    }
}
