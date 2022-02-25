package shrek.helper;

import shrek.constant.Indexes;
import shrek.data.ErrorCount;
import shrek.exception.InvalidCommandException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Handles time input for deadline and event tasks.
 */
public class Time {
    /**
     * Changes time input of user to a more readable format.
     *
     * @param inputTime User input of time.
     * @return Modified time format.
     * @throws InvalidCommandException If data and time format is invalid.
     */
    public static String modifyDatetime(String inputTime) throws InvalidCommandException {
        String[] timeAndDate = inputTime.split(" ", Indexes.NUMBER_OF_TERMS_IN_SPLIT);
        String refinedDatetimeFormat = "";
        LocalDate date;
        try {
            date = LocalDate.parse(timeAndDate[Indexes.INDEX_OF_FIRST_ITEM_IN_STRING]);
            refinedDatetimeFormat += date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " ";
        } catch (DateTimeException e) {
            throw new InvalidCommandException("Wrong date format! Remember to input date as yyyy-mm-dd",
                    ErrorCount.errorCount);
        }
        try {
            LocalTime time = LocalTime.parse(timeAndDate[Indexes.INDEX_OF_SECOND_ITEM_IN_STRING]);
            refinedDatetimeFormat += time.format(DateTimeFormatter.ofPattern("h:mma"));
        } catch (DateTimeException e) {
            throw new InvalidCommandException("Wrong time format! Remember to input time in 24hr format", ErrorCount.errorCount);
        }
        return refinedDatetimeFormat;
    }

    /**
     * Changes the date and time from modified format back to user input format.
     *
     * @param datetime Modified date and time format.
     * @return User input dat and time format.
     * @throws ParseException If fail to parse datetime.
     */
    public static String revertDatetime(String datetime) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date date = new SimpleDateFormat("MMM d yyyy hh:mma").parse(datetime);
        return formatter.format(date);
    }
}
