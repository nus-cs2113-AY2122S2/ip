package time;

import exceptions.DukeExceptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class DateTimeFormatChecker extends Time{
    private static final List<String> formatStrings =
            Arrays.asList("M/d/y H:m", "M/d/y H", "M-d-y H:m",
                    "M-d-y H", "y-M-d H:m", "y-M-d H",
                    "y/M/d H:m", "y/M/d H", "MMM dd yyyy H:m","M/d H:m", "M/d H",
                    "M-d H:m", "d/M H:m", "d/M H", "d-M H:m", "d/M H");

    protected static final SimpleDateFormat stdFormatter = new SimpleDateFormat("MMM dd yyyy HH:mm");

    public DateTimeFormatChecker(String date) throws DukeExceptions {
        super(date);
        for(String formatString: formatStrings) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat(formatString);
                formatter.setLenient(false);
                newDate = formatter.parse(date);
                dateString = stdFormatter.format(newDate);
                //System.out.println(dateString);
                isValidTime = true;
                break;
            } catch (ParseException e) {
                //throw new IllegalTimeFormatException();
            }
        }
    }
}

