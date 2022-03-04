import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

/**
 * Represents a <code>Parser</code> object that contains support for
 * parsing user input
 */

public class Parser {
    /**
     * Helper for addTask.
     * Converts specified line of user input representing date into LocalDateTime object and
     * reformats it into a user-readable string.
     * @param dateString The substring of user input representing the date of a deadline or event
     * @return Reformatted string representation of date
     * @throws DukeException If the date provided by user is in an invalid format.
     */
    public String checkDateStringFormat(String dateString) throws DukeException {
        LocalDateTime dateTime;
        String newDateString;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            dateTime = LocalDateTime.parse(dateString, formatter);
            String rawHour = "0" + Integer.toString(dateTime.getHour());
            String hour = rawHour.substring(rawHour.length() - 2);
            String rawMinute = "0" + Integer.toString(dateTime.getMinute());
            String minute = rawMinute.substring(rawMinute.length() - 2);
            newDateString = dateTime.getMonth() + " " + dateTime.getDayOfMonth() + " " + dateTime.getYear() + " "
                    + hour + ":" + minute;
        } catch (DateTimeParseException e) {
            throw new DukeException("Date is not formatted correctly! Should be in format yyyy-MM-dd HHmm.");
        }
        return newDateString;
    }
    /**
     * Helper for addTask.
     * Extracts information (type, description, date) about Task object from line of user input.
     * Reports information in array of Strings.
     * @param inputLine Line of user input.
     * @return Array of Strings (task type, description, additional info)
     * @throws DukeException If the user did not follow the correct format for specifying a Task.
     */
    public String[] extractTaskInfo(String inputLine) throws DukeException {
        String type;
        String description = "";
        //String additionalInfo;
        String dateString;
        int dateStringIndex = inputLine.length(); // Index where description should end

        // Extract the type of task
        type = inputLine.split("\\s")[0].trim();
        // If the task type is not a todo, deadline, or event, throw a DukeException
        if ((!type.equals("todo")) && (!type.equals("deadline")) && (!type.equals("event"))) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        // Extract the deadline (for Deadline tasks) or time (for Event tasks), if applicable
        if (type.equals("deadline")) {
            // If the task doesn't have a deadline, throw a DukeException
            if (!inputLine.contains("/by") || inputLine.endsWith("/by")) {
                throw new DukeException("OOPS!!! Task of type deadline must have a deadline specified.");
            }
            dateString = inputLine.substring(inputLine.indexOf("/by") + 4);
            // If the task has an empty deadline, throw a DukeException
            if (dateString.trim().isEmpty()) {
                throw new DukeException("OOPS!!! Type of task deadline must not have an empty deadline.");
            }
            dateStringIndex = inputLine.indexOf("/by");
        } else if (type.equals("event")) {
            // If the task is an event and doesn't have a time, throw a DukeException
            if (!inputLine.contains("/at") || inputLine.endsWith("/at")) {
                throw new DukeException("OOPS!!! Task of type event must have a time specified.");
            }
            dateString = inputLine.substring(inputLine.indexOf("/at") + 4);
            // If the task has an empty time, throw a DukeException
            if (dateString.trim().isEmpty()) {
                throw new DukeException("OOPS! Task of type event must not have an empty time.");
            }
            dateStringIndex = inputLine.indexOf("/at");
        } else {
            dateString = "";
        }

        // Extract the description
        // If the description is empty, throw a DukeException
        try {
            description = inputLine.substring(type.length(), dateStringIndex);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The description of a " + type + " cannot be empty.");
        }
        if (description.trim().isEmpty()) {
            throw new DukeException("OOPS!!! The description of a " + type + " cannot be empty.");
        }

        // Check if the user formatted the date correctly
        try {
            dateString = checkDateStringFormat(dateString);
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }

        return new String[] { type.trim(), description.trim(), dateString.trim() };
    }
}
