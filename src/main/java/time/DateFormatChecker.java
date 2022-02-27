package time;

import exceptions.DukeExceptions;
import exceptions.IllegalTimeFormatException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ScheduledExecutorService;

public class DateFormatChecker extends Time {
    private static final List<String> formatStrings =
            Arrays.asList("M/d/y", "M-d-y", "y-M-d", "y/M/d", "MMM dd yyyy", "M/d", "M-d", "d/M", "d-M");
    protected static final SimpleDateFormat stdFormatter = new SimpleDateFormat("MMM dd yyyy");

    public DateFormatChecker(String date) throws DukeExceptions {
        super(date);
        for(String formatString : formatStrings) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat(formatString);
                formatter.setLenient(false);
                newDate = formatter.parse(date);
                dateString = stdFormatter.format(newDate);
                //System.out.println(dateString);
                isValidDate = true;
                break;
            } catch (ParseException e) {
                //throw new IllegalTimeFormatException();
            }
        }
    }

}

