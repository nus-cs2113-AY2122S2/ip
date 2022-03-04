package duke.tasks;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Deadline extends ToDo {
    DateTimeFormatter formatter;
    LocalDate localDate;

    /**
     * Constructor for Deadline object
     *
     * @param description Description of the deadline
     * @param doBy the due date of the deadline
     * @returns the deadline object
     */
    public Deadline(String description, String doBy) {
        super(description);
        try {
            if (doBy.matches(" \\d{2}/\\d{2}/\\d{4}")) {
                doBy = doBy.replaceAll(" ", "");
                formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                localDate = LocalDate.parse(doBy, formatter);

                //super(description);
                this.doBy = " " + localDate.getDayOfWeek() + " " + localDate.getDayOfMonth()
                        + " " + localDate.getMonth() + " " + localDate.getYear();
                System.out.println("====== " + doBy);
            } else {

                this.doBy = doBy;
            }
        } catch (DateTimeException e) {
            //System.out.println("Hey, user; you did it again. You messed up your date format this time.");
            this.doBy = doBy;
        }
    }

    @Override
    public String getDescription() {
        String completeDescription = description + "(by: " + doBy + ")";
        return completeDescription;
    }

    @Override
    public String getStatusIcon() {
        String status = (isDone ? "X" : " ");
        String finalString = "[D][" + status + "]";
        return finalString;
    }
}
