package duke.tasks;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends ToDo {
  
    DateTimeFormatter formatter;
    LocalDate localDate;


    /**
     * Constructor for Event object
     *
     * @param description Description of the event
     * @return The deadline object
     */
    public Event(String description, String dateTimeString) {
        super(description);
        try {
            if (dateTimeString.matches(" \\d{2}/\\d{2}/\\d{4}")) {
                dateTimeString = dateTimeString.replaceAll(" ", "");
                formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                localDate = LocalDate.parse(dateTimeString, formatter);

                //super(description);
                this.dateTimeString = " " + localDate.getDayOfWeek() + " " + localDate.getDayOfMonth()
                        + " " + localDate.getMonth() + " " + localDate.getYear();
                System.out.println("====== " + dateTimeString);
            } else {

                this.dateTimeString = dateTimeString;
            }
        } catch (DateTimeException e) {
            this.dateTimeString = dateTimeString;
        }
    }

    @Override
    public String getDescription() {
        String completeDescription = description + "(at: " + dateTimeString + ")";
        return completeDescription;
    }

    @Override
    public String getStatusIcon() {
        String status = (isDone ? "X" : " ");
        String finalString = "[E][" + status + "]";
        return finalString;
    }
}
