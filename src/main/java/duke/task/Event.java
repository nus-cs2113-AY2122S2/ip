package duke.task;

/**
 * Example: event company meeting /at 2-4pm, June 8th
 * description: "company meeting"
 * by: "2-4pm, June 8th"
 * preposition: "/at"
 */
public class Event extends Task {
    protected String at;
    protected String preposition;

    public Event(String description, String at, String preposition) {
        super(description.trim());
        this.at = at.trim();
        this.preposition = preposition.substring(1);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (%s: %s)", super.toString(), preposition, at);
    }
}
