package duke.task;

/**
 * Example: deadline return book /by June 6th
 * description: "return book"
 * by: "June 6th"
 */
public class Deadline extends Task {
    protected String by;
    protected String preposition;

    public Deadline(String description, String by) {
        super(description.trim());
        this.by = by.trim();
        this.preposition = "by";
    }

    public String getTiming() {
        return this.by;
    }

    public String getPrepositions() {
        return "/" + this.preposition;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (%s: %s)", super.toString(), preposition, by);
    }

}
