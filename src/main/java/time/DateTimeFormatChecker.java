package time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class DateTimeFormatChecker extends Time {
    private static final List<String> formatStrings =
            Arrays.asList("M/d/y H:m", "M/d/y H", "M-d-y H:m", "y-M-d H:m", "y/M/d H:m", "MMM dd yyyy H:m",
                    "M/d H:m", "M-d H:m", "d/M H:m", "d-M H:m");

    private static final SimpleDateFormat stdFormatter = new SimpleDateFormat("MMM dd yyyy HH:mm");

    /**
     * Initializes a checker to check whether the date with time string is in acceptable format
     * @param date the string of date with time
     */
    public DateTimeFormatChecker(String date) {
        super(date);
        for (String formatString: formatStrings) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat(formatString);
                formatter.setLenient(false);
                newDate = formatter.parse(date);
                dateString = stdFormatter.format(newDate);
                isValidTime = true;
                break;
            } catch (ParseException e) {
                //Time is not in this format if the exception is caught. Try next one to see if it is in
                //acceptable format
            }
        }
    }
}

