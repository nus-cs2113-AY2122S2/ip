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

    /**
     * Takes the time section of the user input for
     * event and deadline after the respective keyword /at or /by
     * and change them into a LocalDate format
     * Users can input either the day eg: "Monday" or
     * input the exact date in yyyy/mm/dd or yyyy-mm-dd
     *
     * @param input User input string containing the due date
     * @return LocalDate which contains the date of the task end
     * @throws DukeExceptionTiming
     */
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

    /**
     * This is to handle the instance where user input for time
     * was just a day "Monday" or "Sunday"
     *
     * @param input User input string containing the due date
     * @param localDate local date when the task was added
     * @return LocalDate of when the due date of the task is
     */
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

    /**
     * Checks if the user input includes a specific due time
     * If yes then add it in, if not then set it as due by 2359
     * The user input can come in the form of 7am, 6pm, 16:00
     * or just 1500 in 24 hours format
     *
     *
     * @param input User input string containing the due time
     * @return LocalTime for the due time
     */
    private LocalTime convertTime(String input) {
        LocalTime localTime;
        int index = 0;

        //take at most the 2 digits before am/pm as a time
        if(input.toLowerCase(Locale.ROOT).contains("am")) {
            index = input.toLowerCase(Locale.ROOT).indexOf("am");
            localTime = convertTime(input, index, false, false);
        } else if (input.toLowerCase(Locale.ROOT).contains("pm")) {
            index = input.toLowerCase(Locale.ROOT).indexOf("pm");
            localTime = convertTime(input, index, false, true);
        } else if (input.contains(":")) {
            index = input.indexOf(":");
            localTime = convertTime(input, index, true, false);
        } else {
            localTime = checkTime(input);
        }

        //if user did not input a time, checkTime() will return null
        if (localTime == null) {
            localTime = LocalTime.of(23,59);
        }
        return localTime;
    }

    /**
     * Converting user input due time into a LocalTime variable
     *
     * @param input User input string containing the due time
     * @param index Index for where the number time is contained
     * @param hasMinute check if due time includes minutes
     * @param isPm check if the time is indicated as pm
     * @return LocalTime for the due time
     */
    private LocalTime convertTime(String input, int index, boolean hasMinute, boolean isPm) {
        int hourInt;
        int minInt = 0;
        if (hasMinute) {
            String hourStr = input.substring(index - 2 ,index);
            String minStr = input.substring(index + 1, index + 2);
            hourInt = Integer.parseInt(hourStr.trim());
            minInt = Integer.parseInt(minStr.trim());
            return LocalTime.of(hourInt, minInt);
        } else {
            String hourStr = input.substring(index - 2, index);
            hourInt = Integer.parseInt(hourStr.trim());
            //if time is PM, other than 12 pm, convert to 24 hour format
            if (isPm) {
                if (hourInt != 12) {
                    hourInt += 12;
                }
            } else {
                //if time is 12am, convert to 24 hour format 0000
                if (hourInt == 12) {
                    hourInt = 0;
                }
            }
        }

        return LocalTime.of(hourInt, minInt);
    }

    /**
     * Using regex to find if the due time indicated
     * is just in a 24 hours format eg: 1900
     *
     * @param input User input string containing the due time
     * @return LocalTime for the due time or NULL if no due time is found
     */
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

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
