package task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Deadlines extends Task {
    protected LocalDate by;

    public Deadlines(String description, String by, boolean isDone) {
        super(description);
        LocalDate date = LocalDate.parse(by);

        this.by = date;
        this.isDone = isDone;
    }

    public Deadlines(String description, String by) {
        super(description);
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-mm-dd");
        LocalDate date = LocalDate.parse(by);

        this.by = date;
        this.isDone = false;
    }

    public String getBy() {
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)\n", this.getType(), this.getStatusIcon(), this.getDescription(), this.getBy());
    }
}
