public class Deadline extends Task {
    private String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        String taskLabel = "[D]";
        String dateString = String.format(" (by: %s)", date);
        return taskLabel + super.toString() + dateString;
    }
}
