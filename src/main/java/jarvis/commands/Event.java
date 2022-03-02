package jarvis.commands;

public class Event extends Task {
    private String eventDate;

    /**
     * Override getter function for retrieving Event's icon
     *
     * @return Icon in String data type to represent an Event
     */
    public String getTypeIcon() { return "E"; }

    /**
     * Constructor for an Event.
     *
     * @param eventDescription A description of the event in String
     * @param eventTime
     */
    public Event(String eventDescription, String eventTime) {
        super(eventDescription);
        this.eventDate = eventTime;
    }

    /**
     * Function to print the Event formatted with icon, status and description.
     */
    public void printItem() {
        String message = "[" + getTypeIcon() + "]" + "[" + getStatusIcon() + "] " + getDescription();
        System.out.println(message);
    }

    /**
     * Getter function to retrieve Event's date.
     *
     * @return Date of the Event as a String
     */
    public String getEventDate() {
        return eventDate;
    }

    /**
     * Override getter function to retrieve Event's description
     * @return String of Event's description
     */
    public String getDescription() {
        String message = super.getDescription() + " (at: " + getEventDate() + ")";
        return message;
    }

    /**
     * Override function to export data of Event to be used to store inside user's saved data file for when Jarvis is
     * shutting down.
     *
     * @return String of Event's data formatted.
     */
    public String exportData() {
        String status = isDone ? "YES" : "NO";
        return getTypeIcon() + " " + status + " " + super.getDescription() + " | " + this.getEventDate();
    }
}
