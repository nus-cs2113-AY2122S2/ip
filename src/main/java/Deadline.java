public class Deadline extends Task {
    private String date;
    private static String icon = "D";

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toStringInFormat() {
        return icon + " | " + super.toStringInFormat() + " | " + date;
    }

    @Override
    public String toString() {
        String taskIcon = "[" + icon + "]";
        String dateString = String.format(" (by: %s)", date);
        return taskIcon + super.toString() + dateString;
    }
}
