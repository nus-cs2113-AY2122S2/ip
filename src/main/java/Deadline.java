public class Deadline extends Task {
    private String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        String taskIcon = "[D]";
        String dateString = String.format(" (by: %s)", date);
        return taskIcon + super.toString() + dateString;
    }
}
