package time;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeToStringConverter {
    protected static final SimpleDateFormat stdDateTimeFormatter = new SimpleDateFormat("MMM dd yyyy HH:mm");
    protected static final SimpleDateFormat stdDateFormatter = new SimpleDateFormat("MMM dd yyyy");
    protected boolean isDateTime;
    protected boolean isDate;
    protected String timeString;

    public TimeToStringConverter (Date date) throws Exception {
        try{
            timeString = stdDateFormatter.format(date);
            //System.out.println(timeString);
            isDate = true;
        } catch (Exception e) {
            isDate = false;
        }
        if (!isDate) {
            try {
                timeString = stdDateTimeFormatter.format(date);
                //System.out.println(timeString);
                isDateTime = true;
            } catch (Exception e) {
            }
            isDateTime = false;
        }
    }

    public String getTimeString() {
        return timeString;
    }
}
