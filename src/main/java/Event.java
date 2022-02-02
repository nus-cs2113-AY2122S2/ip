public class Event extends Task {
    private String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        String marking = "[E]";
        String dateString = String.format(" (at: %s)", date);
        return marking + super.toString() + dateString;
    }
}
