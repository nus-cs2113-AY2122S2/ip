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

public class Time {
    public static String modifyDatetime(String inputTime) throws InvalidCommandException {
        String[] timeAndDate = inputTime.split(" ");
        String refinedDatetimeFormat = "";
        LocalDate date;
        try {
            date = LocalDate.parse(timeAndDate[Indexes.INDEX_OF_FIRST_ITEM_IN_STRING]);
            if (date.isBefore(LocalDate.now())) {
                throw new InvalidCommandException("Invalid date! The date is past current date", ErrorCount.errorCount);
            }
            refinedDatetimeFormat += date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " ";
        } catch (DateTimeException e) {
            throw new InvalidCommandException("Wrong date format! Remember to input date as yyyy-mm-dd",
                    ErrorCount.errorCount);
        }
        try {
            LocalTime time = LocalTime.parse(timeAndDate[Indexes.INDEX_OF_SECOND_ITEM_IN_STRING]);
            if (date.isEqual(LocalDate.now()) && time.isBefore(LocalTime.now())) {
                throw new InvalidCommandException("Invalid time! The time is past current time", ErrorCount.errorCount);
            }
            refinedDatetimeFormat += time.format(DateTimeFormatter.ofPattern("h:mma"));
        } catch (DateTimeException e) {
            throw new InvalidCommandException("Wrong time format! Remember to input time in 24hr format", ErrorCount.errorCount);
        }
        return refinedDatetimeFormat;
    }

    public static String revertDatetime(String datetime) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date date = new SimpleDateFormat("MMM d yyyy hh:mma").parse(datetime);
        return formatter.format(date);
    }
}
