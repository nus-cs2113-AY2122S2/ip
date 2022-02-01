public class Event extends Task {
    private String at;

    public String getTypeIcon() { return "E"; }

    public Event(String eventDescription, String eventTime) {
        super(eventDescription);
        this.at = eventTime;
    }

    public void printItem() {
        String message = "[" + getTypeIcon() + "]" + "[" + getStatusIcon() + "] " + getDescription();
        System.out.println(message);
    }

    public String getEventDate() { return at; }

    public String getDescription() {
        String message = super.getDescription() + " (at: " + getEventDate() + ")";
        return message;
    }
}
