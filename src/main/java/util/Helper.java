package util;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * A static class that contains helper methods that cannot be properly categorised.
 */
public class Helper {
    /**
     * Returns the hour of the day in 24-hour format. Uses the GMT+8 timezone.
     *
     * @return The hour of the day in 24-hour format.
     */
    public static int getHourOfDay() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * Checks if the specified string of text contains more than one of the specified keyword.
     *
     * @param stringText The string of text to be searched.
     * @param keyword The keyword that will be searched for in stringText.
     * @return true if more than one occurrence of keyword is found in stringText. Otherwise,
     * false is returned.
     */
    public static boolean checkMultipleOccurrences(String stringText, String keyword) {
        int lastIndexOfString = stringText.length() - 1;
        int currentCursorIndex = 0;
        int occurrencesFound = 0;

        while (currentCursorIndex <= lastIndexOfString) {
            int indexOfNextOccurrence = stringText.indexOf(keyword, currentCursorIndex);

            if (indexOfNextOccurrence != -1) {
                String substring = stringText.substring(indexOfNextOccurrence, indexOfNextOccurrence + keyword.length());
                if (substring.equals(keyword)) {
                    // Occurrence of keyword found
                    occurrencesFound += 1;
                }
            } else {
                break;
            }

            if (occurrencesFound > 1) {
                // More than one occurrence of the keyword found
                return true;
            }

            currentCursorIndex = indexOfNextOccurrence + keyword.length();
        }

        return false;
    }
}
