package time;

import java.util.Date;

import exceptions.DukeExceptions;
import exceptions.IllegalTimeFormatException;

public class Time {
    protected static boolean isValidDate;
    protected static boolean isValidTime;
    protected static Date newDate;
    protected static String dateString;
    protected boolean isValid;
    protected String oldDate;
    private DateTimeFormatChecker checkTime;
    private DateFormatChecker checkDate;

    /**
     * Initializes a time object for time format checking and converting
     * @param date the date for checking and converting in String format
     */
    public Time(String date) {
        oldDate = date;
        isValidDate = false;
        isValidTime = false;
        isValid = false;
    }

    /**
     * Checks whether the string is a date without time or a date with time
     * @throws DukeExceptions if the string fails in checking
     */
    public void check() throws DukeExceptions {
        if (oldDate.contains(":")) { //the string contains time, may be a date with time
            checkTime = new DateTimeFormatChecker(oldDate);
        } else { //the string does not contain time, may be a date without time
            checkDate = new DateFormatChecker(oldDate);
        }
        //throws exceptions if the string is neither date with time nor date without time
        if (!isValidTime && !isValidDate) {
            throw new IllegalTimeFormatException();
        }
    }

    public Date getNewDate() {
        return newDate;
    }
    public String getDateString() {
        return dateString;
    }
}
