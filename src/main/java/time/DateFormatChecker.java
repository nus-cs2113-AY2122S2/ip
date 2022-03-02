package time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;


public class DateFormatChecker extends Time {
    //Standard format for printing task information
    private static final SimpleDateFormat stdFormatter = new SimpleDateFormat("MMM dd yyyy");
    //Acceptable format for time converting
    private static final List<String> formatStrings =
            Arrays.asList("M/d/y", "M-d-y", "y-M-d", "y/M/d", "MMM dd yyyy", "M/d", "M-d", "d/M", "d-M");
    /**
     * Initializes a checker to check whether the date string is in acceptable format
     * @param date the string of date
     */
    public DateFormatChecker(String date) {
        super(date);
        //Try to convert the date to check if the time is in any of the acceptable formats
        for (String formatString : formatStrings) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat(formatString);
                formatter.setLenient(false);
                newDate = formatter.parse(date);
                dateString = stdFormatter.format(newDate);
                isValidDate = true;
                break;
            } catch (ParseException e) {
                //Time is not in this format if the exception is caught. Try next one to see if it is in
                //acceptable format
            }
        }
    }

}

