package jarvis.commands;

public class Event extends Task {
    private String eventDate;

    public String getTypeIcon() { return "E"; }

    public Event(String eventDescription, String eventTime) {
        super(eventDescription);
        this.eventDate = eventTime;
    }

    public void printItem() {
        String message = "[" + getTypeIcon() + "]" + "[" + getStatusIcon() + "] " + getDescription();
        System.out.println(message);
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getDescription() {
        String message = super.getDescription() + " (at: " + getEventDate() + ")";
        return message;
    }
}
