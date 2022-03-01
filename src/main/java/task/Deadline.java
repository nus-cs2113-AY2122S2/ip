package task;
import errors.DukeException;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Deadline extends Task {
    
    public static final String DATE_STRING_PATTERN = "MMM dd yyyy";
    private LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.type = "D";
        this.by = by;
    }

    public LocalDate getBy() {
        return this.by;
    }

    public void setBy(LocalDate by) {

        this.by = by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern(DATE_STRING_PATTERN)) + ")";
    }

    /**
     * Get task name of task type deadline
     *
     * @param taskDetails string to retrieve task name from
     *
     */
    public static String getDeadlineTask(String taskDetails) {
        //first space
        int firstSpaceIndex = taskDetails.indexOf(" ");
        int byIndex = taskDetails.indexOf("/by");
        String deadlineTask = taskDetails.substring(firstSpaceIndex+1,byIndex-1).trim();
        if (deadlineTask.length() == 0){
            throw new StringIndexOutOfBoundsException();
        }
        return deadlineTask.trim();
    }

    /**
     * Based on user input generate and return a date object
     *
     * @param dateString user input of date string
     *
     */
    public static LocalDate getDateObject(String dateString){
        LocalDate dateObject = LocalDate.now();
        try {
            int year = Integer.parseInt(dateString.substring(0, 4));
            int month = Integer.parseInt(dateString.substring(5, 7));
            int day = Integer.parseInt(dateString.substring(8,10));
            dateObject = LocalDate.of(year,month,day);
            System.out.println(year + month + day);
        } catch (DateTimeParseException e){
            System.out.println("Error Please input a date in format yyyy-mm-dd");
        }
        return dateObject;
    }

    /**
     * Get deadline date of task type deadline
     *
     * @param input user input of deadline task
     *
     */
    public static LocalDate getDeadlineDate(String input)  {
        int byIndex = input.indexOf("/by");
        int deadlineDateIndex = input.indexOf(" ",byIndex);
        String deadlineDate = input.substring(deadlineDateIndex + 1).trim();
        if (deadlineDate.length() == 0){
            throw new StringIndexOutOfBoundsException();
        }
        LocalDate dateObject = getDateObject(deadlineDate);
        return dateObject;
    }
}
