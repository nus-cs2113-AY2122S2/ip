package task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Deadlines extends Task {
    protected LocalDate by;

    /**
     * A constructor of Deadlines
     * @param description the description of the deadline
     * @param by the time deadline of this deadline task
     * @param isDone A boolean value records if the deadline has been done
     */
    public Deadlines(String description, String by, boolean isDone) {
        super(description);
        LocalDate date = LocalDate.parse(by, DateTimeFormatter.ofPattern("MMM d yyyy"));

        this.by = date;
        this.isDone = isDone;
    }

    /**
     * A constructor of deadlines
     * @param description the description of the deadline
     * @param by the time deadline of this deadline task
     */
    public Deadlines(String description, String by) {
        super(description);
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-mm-dd");
        LocalDate date = LocalDate.parse(by);

        this.by = date;
        this.isDone = false;
    }

    /**
     * A method that get the deadline of the deadline task
     * @return the deadline
     */
    public String getBy() {
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * A method return the type icon of deadlines
     * @return the icon of the deadlines
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * A method converts deadlines to string
     * @return return the string format of the deadlines
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)\n", this.getType(), this.getStatusIcon(), this.getDescription(), this.getBy());
    }
}
