package Duke;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Duke.Tokenise is to help split user input
 * into individual strings for sanitisation
 * and other checks
 */
public class Parser {
    private String[] tokens = new String[]{};
    private int markIndex;
    private String description;
    private LocalDateTime dateTime;
    private boolean hasTime;

    public Parser(String userInput) throws DukeException {
        tokens = userInput.split(" ");
        switch (tokens[0]) {
        case "list":
            if (tokens.length != 1) {
                throw new DukeExceptionList();
            }
            break;
        case "deadline":
            if (tokens.length < 2) {
                throw new DukeExceptionDescription();
            }
            int deadlineBy = findIndex(tokens, "/by");
            if (deadlineBy > 0) {
                //remove keyword deadline and deadline time from raw user input
                description = combineToken(tokens, 1, deadlineBy);
                String timeStr = combineToken(tokens, deadlineBy + 1);
                dateTime = LocalDateTime.parse(timeStr);
            } else {
                throw new DukeExceptionTiming();
            }
            break;
        case "event":
            if (tokens.length < 2) {
                throw new DukeExceptionDescription();
            }
            int eventAt = findIndex(tokens, "/at");
            if (eventAt > 0) {
                //remove keyword deadline and deadline time from raw user input
                description = combineToken(tokens, 1, eventAt);
                time = combineToken(tokens, eventAt + 1);
            } else {
                throw new DukeExceptionTiming();
            }
            break;
        case "todo":
            if (tokens.length < 2) {
                throw new DukeExceptionDescription();
            }
            description = combineToken(tokens, 1);
            break;
        case "mark":
        case "unmark":
        case "delete":
            //checking if value after mark or unmark is a number
            if (tokens.length != 2) {
                throw new DukeExceptionMark();
            }
            try {
                markIndex = Integer.parseInt(tokens[1]) - 1;
            } catch (NumberFormatException e) {
                throw new DukeExceptionMark();
            }
            break;
        default:
            throw new DukeExceptionCommand();
        }
    }

    public int getMarkIndex() {
        return markIndex;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public boolean isHasTime() {
        return hasTime;
    }

    public String[] getTokens() {
        return tokens;
    }

    /**
     * Finds the index of word in the string array
     * mimicking the use of an array list
     * @param tokens string to search of in tokens array
     * @param word string to search of in tokens array
     * @return index of word in tokens array, or -1 if word is not found
     */
    private int findIndex(String[] tokens, String word) {
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals(word)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * This will combine the Token into a String for the actual Duke.Task
     *
     * @param tokens User input token
     * @param startIndex Where to start copying the tokens
     * @return combinedToken String of the combined tokens
     */
    private String combineToken(String[] tokens, int startIndex) {
        String combinedToken = "";
        for (int i = startIndex; i < tokens.length; i++) {
            combinedToken = combinedToken + tokens[i] + " ";
        }
        return combinedToken.trim();
    }

    /**
     * This will combine the Token into a String for the actual Duke.Task
     *
     * @param tokens User input token
     * @param startIndex Where to start copying the tokens
     * @param endIndex Where to end copying the tokens
     * @return combinedToken String of the combined tokens
     */
    private String combineToken(String[] tokens, int startIndex, int endIndex) {
        String combinedToken = "";
        for (int i = startIndex; i < endIndex; i++) {
            combinedToken = combinedToken + tokens[i] + " ";
        }
        return combinedToken.trim();
    }

    public LocalDateTime timeFormatter(String dateTime) {
        LocalDateTime localDateTime;
        String regex = "(?<year>\\d{4})";
        if (dateTime.contains("-")) {
            String[] dateTimeArray = dateTime.split("-");
        } else if (dateTime.contains("/")) {
            String[] dateTimeArray = dateTime.split("/");

        } else {

        }

        return localDateTime;
    }

    public static boolean hasTime(String dateTime) {
        if (dateTime.contains("pm") ||
        dateTime.contains("am")) {
            return true;
        }
    }
}
