package Duke;

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;
    protected boolean hasTime;

    public Deadline(String description, LocalDateTime by, boolean hasTime) {
        super(description);
        this.by = by;
        this.hasTime = hasTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by + ")\n";
    }
}
