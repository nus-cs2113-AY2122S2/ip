import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String at;
    protected LocalDate date;

    public Event(String description, String at) {
        super(description);
        this.at = at.trim();
        parseDate();
    }

    public void parseDate() {
        try {
            this.date = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            System.out.println("OOPS!!! Please key in a correct at date.");
        }
    }

    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "    [E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " " + date.getDayOfWeek() + ")";
    }
}
