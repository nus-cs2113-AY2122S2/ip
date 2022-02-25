package Duke;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;

public class TimeHandler {
    private LocalDate localDate;
    private LocalTime localTime;
    private LocalDateTime localDateTime;

    public TimeHandler(String input) throws  DukeExceptionTiming{
        this.localDate = convertDate(input.trim());
        this.localTime = convertTime(input.trim());
        this.localDateTime = joinDateTime(localDate, localTime);
    }

    private LocalDate convertDate(String input) throws DukeExceptionTiming  {
        LocalDate localDate = LocalDate.now();
        String date = input.split(" ")[0];

        try {
            if (input.contains("day")) {
                //user input only includes day
                localDate = dueDate(input, localDate);
            } else if (input.contains("/")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                        "yyyy/mm/dd");
                localDate = LocalDate.parse(date, formatter);
            } else {
                localDate = LocalDate.parse(date);
            }
        } catch (DateTimeParseException e) {
            throw new DukeExceptionTiming();
        }

        return localDate;
    }

    private LocalDate dueDate(String input, LocalDate localDate) {
        String day = input.split(" ")[0].toUpperCase(Locale.ROOT);
        DayOfWeek dueDay = DayOfWeek.valueOf(day.trim());
        int dayToAdd = dueDay.getValue() - localDate.getDayOfWeek().getValue();
        if (dayToAdd < 0) {
            localDate = localDate.plusDays(dayToAdd + 7);
        } else {
            localDate = localDate.plusDays(dayToAdd);
        }
        return localDate;
    }

    private LocalDateTime joinDateTime(LocalDate date, LocalTime time) {
        return time.atDate(date);
    }

    private LocalTime convertTime(String input) {
        LocalTime localTime;
        int index = 0;

        //take at most the 2 digits before am/pm as a time
        if(input.contains("am")) {
            index = input.indexOf("am");
            localTime = convertTime(input, index, false);
        } else if (input.contains("pm")) {
            index = input.indexOf("pm");
            localTime = convertTime(input, index, false);
        } else if (input.contains(":")) {
            index = input.indexOf(":");
            localTime = convertTime(input, index, true);
        } else {
            localTime = checkTime(input);
        }

        //if user did not input a time, checkTime() will return null
        //initialise localTime to 0000
        if (localTime == null) {
            localTime = LocalTime.of(23,59);
        }
        return localTime;
    }

    private LocalTime convertTime(String input, int index, boolean hasMinute) {
        if (hasMinute) {
            String hourStr = input.substring(index - 2 ,index);
            String minStr = input.substring(index + 1, index + 2);
            int hourInt = Integer.parseInt(hourStr.trim());
            int minInt = Integer.parseInt(minStr.trim());
            return LocalTime.of(hourInt, minInt);
        }
        String hourStr = input.substring(index - 2 ,index);
        int hourInt = Integer.parseInt(hourStr.trim());
        return LocalTime.of(hourInt, 0);
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    private LocalTime checkTime(String input) {
        String regex = "\\d{4}$";
        Matcher matcher = Storage.regexMatching(regex, input);
        if (matcher.find()) {
            String time = matcher.group();
            String timeHrs = time.substring(0, 2);
            String timeMin = time.substring(2);
            return LocalTime.of(Integer.parseInt(timeHrs),
                    Integer.parseInt(timeMin));
        }
        return null;
    }
}
