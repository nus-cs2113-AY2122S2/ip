package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private LocalDate dateOfDeadline;

    public Deadline(String description, LocalDate dateOfDeadline, String typeOfTask) {
        super(description, typeOfTask);
        this.dateOfDeadline = dateOfDeadline;
    }

    public LocalDate getDate() {
        return dateOfDeadline;
    }

    @Override
    public String toString() {
        String deadline = dateOfDeadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

}
