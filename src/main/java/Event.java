/**
 * Subclass of Task
 * Event has an additional parameter 'at' that indicates when the event will start
 * The string "/at" in the user input will demarcate the time that the event starts
 * User input to add a new Event is:
 * > event [description] /at [time]
 */
public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

}

