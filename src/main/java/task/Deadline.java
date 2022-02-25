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


    public static String getDeadlineTask(String input) {
        //first space
        int firstSpaceIndex = input.indexOf(" ");
        int byIndex = input.indexOf("/by");
        String deadlineTask = input.substring(firstSpaceIndex+1,byIndex-1).trim();
        if (deadlineTask.length() == 0){
            throw new StringIndexOutOfBoundsException();
        }
        return deadlineTask.trim();
    }

    public static LocalDate getDateObject(String date){
        LocalDate dateObject = LocalDate.now();
        try {
            int year = Integer.parseInt(date.substring(0, 4));
            int month = Integer.parseInt(date.substring(5, 7));
            int day = Integer.parseInt(date.substring(8,10));
            dateObject = LocalDate.of(year,month,day);
            System.out.println(year + month + day);
        } catch (DateTimeParseException e){
            System.out.println("Error Please input a date in format yyyy-mm-dd");
        }
        return dateObject;
    }

    public static LocalDate getDeadlineDate(String input) {
        int byIndex = input.indexOf("/by");
        int deadlineDateIndex = input.indexOf(" ",byIndex);
        int deadlineTimeIndex = input.indexOf(" ",deadlineDateIndex + 1);
        String deadlineDate = input.substring(deadlineDateIndex + 1,deadlineTimeIndex).trim();
        String deadlineTime = input.substring(deadlineTimeIndex + 1).trim();
        if (deadlineDate.length() == 0 || deadlineTime.length() == 0){
            throw new StringIndexOutOfBoundsException();
        }
        LocalDate dateObject = getDateObject(deadlineDate);
        return dateObject;
    }
}
