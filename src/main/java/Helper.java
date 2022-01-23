import java.util.Calendar;
import java.util.TimeZone;

public class Helper {
    protected static void printLine() {
        // Prints a line based on the default parameters
        int defaultLength = 60;
        String defaultChar = "-";
        printLine(defaultLength, defaultChar);
    }

    protected static void printLine(int lineLen, String character) {
        // Prints a line based on the specified length and the character/symbol to use
        System.out.println(String.format("%" + lineLen + "s", " ").replaceAll(" ", character));
    }

    protected static int getHourOfDay() {
        // Returns the hour of the day in 24-hour format. Uses the GMT+8 timezone
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        int hourOfDay = cal.get(Calendar.HOUR_OF_DAY);

        return hourOfDay;
    }
}
