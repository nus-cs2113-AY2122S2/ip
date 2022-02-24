package duke.task;

/**
 * Example: event company meeting /at 2-4pm, June 8th
 * description: "company meeting"
 * by: "2-4pm, June 8th"
 */
public class Event extends Task {
    protected String at;
    protected String preposition;

    public Event(String description, String at) {
        super(description.trim());
        this.at = at.trim();
        this.preposition = "at";
    }

    public String getTiming() {
        return this.at;
    }

    public String getPrepositions() {
        return "/" + this.preposition;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (%s: %s)", super.toString(), preposition, at);
    }
}
