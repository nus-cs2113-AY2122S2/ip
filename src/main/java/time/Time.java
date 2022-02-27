package time;

import exceptions.DukeExceptions;
import exceptions.IllegalTimeFormatException;

import java.time.LocalDate;
import java.util.Date;

public class Time {
    protected boolean isValid;
    protected static boolean isValidDate;
    protected static boolean isValidTime;
    protected static Date newDate;
    protected static String dateString;
    protected String oldDate;
    DateTimeFormatChecker checkTime;
    DateFormatChecker checkDate;

    public Time(String date) {
        oldDate = date;
        isValidDate = false;
        isValidTime = false;
        isValid = false;
    }

    public void check() throws DukeExceptions {
        if(oldDate.contains(":")) {
            checkTime = new DateTimeFormatChecker(oldDate);
        } else {
            checkDate = new DateFormatChecker(oldDate);
        }

        if (!isValidTime && !isValidDate) {
            throw new IllegalTimeFormatException();
        }
    }

    public boolean validation() {
        return isValid;
    }

    public Date getNewDate() {
        return newDate;
    }
    public String getDateString() {
        return dateString;
    }
}
