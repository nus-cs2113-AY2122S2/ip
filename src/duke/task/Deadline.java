package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate deadlineDate;
    protected LocalTime deadlineTime;
    public Deadline(String name, boolean marked, LocalDate deadlineDate, LocalTime deadlineTime){
        super(name, marked);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    public String getDeadline() {
        String deadline = deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " ";
        deadline += deadlineTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        return deadline;
    }

    public String getDeadlineDetails() {
        return deadlineDate + " | " + deadlineTime;
    }

    public String toString() {
        if (super.getMarked()) {
            return " [D][X] " + getName() + " (by: " + getDeadline() + ")";
        }else {
            return " [D][ ] " + getName() + " (by: " + getDeadline() + ")";
        }
    }

    public String getTaskDetails() {
        return "deadline | " + (getMarked() ? 1:0) + " | " + getName() + " | " + getDeadlineDetails() + "\n";
    }
}
