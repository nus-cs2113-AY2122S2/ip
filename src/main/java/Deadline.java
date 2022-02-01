/**
 * Example: deadline return book /by June 6th
 * description: "return book"
 * by: "June 6th"
 * preposition: "/by"
 */
public class Deadline extends Task {
    protected String by;
    protected String preposition;

    public Deadline(String description, String by, String preposition) {
        super(description.trim());
        this.by = by.trim();
        this.preposition = preposition.substring(1);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + preposition + ": " + by + ")";
    }
}
