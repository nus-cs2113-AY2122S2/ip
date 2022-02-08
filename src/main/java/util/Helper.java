package util;

import java.util.Calendar;
import java.util.TimeZone;

public class Helper {
    public static int getHourOfDay() {
        // Returns the hour of the day in 24-hour format. Uses the GMT+8 timezone
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));

        return cal.get(Calendar.HOUR_OF_DAY);
    }
}
