import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;

public class Helper {
    private static String[] acknowledgementWords = {
        "Alright",
        "Okay",
        "Gotcha",
        "Got it"
    };

    public static String[] getAcknowledgementWords() {
        return acknowledgementWords;
    }

    protected static String getRandomAcknowledgement() {
        // Randomly chooses one of the acknowledgement words from the array acknowledgementWords and returns it
        Random rand = new Random();
        int randNum = rand.nextInt(getAcknowledgementWords().length);

        return getAcknowledgementWords()[randNum];
    }

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

        return cal.get(Calendar.HOUR_OF_DAY);
    }
}
