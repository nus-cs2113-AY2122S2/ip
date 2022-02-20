package duke;

import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CommandParser {

    /**
     * Process and executes the user's command.
     *
     * @param userInput The whole user's input as String
     * @param command The command as String
     * @param taskList The TaskList object that handles task related jobs
     */
    public static void executeCommand(String userInput, String command, TaskList taskList) {
        switch (command) {
        case "mark":
        case "unmark":
            taskList.markTaskInTaskList(userInput, command);
            break;
        case "list":
            taskList.printTasksFromTaskList();
            break;
        case "delete":
            taskList.deleteTaskInTaskList(userInput);
            break;
        case "find":
            taskList.findTaskInTaskList(userInput);
            break;
        default:
            taskList.addTaskToTaskList(userInput, command);
            break;
        }
    }

    /**
     * Returns the command from the user's input.
     * The command is the first word of in the given user's input
     *
     * @param userInput The whole user command as String
     * @return The command as String
     */
    public static String getCommandFromUserInput(String userInput) {
        return userInput.split(" ")[0].toLowerCase();
    }

    /**
     * Returns a valid search string from the user's input.
     * Checks if the user has entered a valid search string,
     * else it returns null
     *
     * @param userInput The whole user command as String
     * @return The search string
     */
    public static String getSearchStringFromUserInput(String userInput) {
        String userSearchString;
        // remove extra spaces in the user's input
        String input = userInput.strip();
        int indexOfSpace = input.indexOf(" ");
        try {
            if (indexOfSpace == -1) {
                throw new DukeException("Oops! It seems that you did " +
                        "not type anything! Please try again!");
            }
            userSearchString = input.substring(indexOfSpace + 1);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return userSearchString;
    }

    /**
     * Returns the description of a deadline task from the user's input.
     * The function processes the whole user input as a string.
     * The function would assume the description is located
     * between the first " " and the "/by" in the user's input.
     *
     * @param userInput The whole user command as String.
     * @return The description of the deadline task in String.
     * @throws DukeException If "/by" is not found in the user's command.
     *                       If ',' was found in the user's command,
     *                       this is a check to prevent errors when storing into a CSV file.
     * @throws StringIndexOutOfBoundsException  If user command does not contain a description.
     */
    public static String getDeadlineDescription(String userInput) throws DukeException, StringIndexOutOfBoundsException {
        if (userInput.contains("/by")) {
            int indexOfFirstSpace = userInput.indexOf(" ");
            int indexOfSlash = userInput.indexOf("/by");
            // +1 to exclude " " and -1 to exclude "/"
            String description = userInput.substring(indexOfFirstSpace + 1, indexOfSlash - 1).trim();
            if (description.isEmpty()) {
                throw new StringIndexOutOfBoundsException();
            }
            if (description.contains(",")) {
                throw new DukeException("Please do not use ',' in the description");
            }
            return description;
        }
        throw new DukeException("Oops! It seems that you left out the /by in your command!");
    }

    /**
     * Returns the Date specified when creating a deadline task.
     * The function processes the whole user input as a string.
     * The function would assume the date is located
     * after the "/by" in the user's input.
     *
     * @param userInput The whole user command as String.
     * @return The date of the deadline task in String
     * @throws DukeException If "/by" is not found in the user's command.
     *                       If ',' was found in the user's command,
     *                       this is a check to prevent errors when storing into a CSV file.
     * @throws StringIndexOutOfBoundsException If user command does not contain a date.
     */
    public static String getDeadlineDate(String userInput) throws DukeException, StringIndexOutOfBoundsException {
        if (userInput.contains("/by")) {
            int slashIndex = userInput.indexOf("/by");
            // +3 to exclude "/by"
            String deadlineDate = userInput.substring(slashIndex + 3).trim();
            if (deadlineDate.isEmpty()) {
                throw new StringIndexOutOfBoundsException();
            }
            if (deadlineDate.contains(",")) {
                throw new DukeException("Please do not use ',' in the date");
            }
            return deadlineDate;
        }
        throw new DukeException("Oops! It seems that you left out the date for the /by command!");
    }

    /**
     * Returns the description of an event task from the user's input.
     * The function processes the whole user input as a string.
     * The function would assume the description is located
     * between the first " " and the "/at" in the user's input.
     *
     * @param userInput The whole user command as String.
     * @return The description of the event task in String.
     * @throws DukeException If "/at" is not found in the user's command.
     *                       If ',' was found in the user's command,
     *                       this is a check to prevent errors when storing into a CSV file.
     * @throws StringIndexOutOfBoundsException If user command does not contain a description.
     */
    public static String getEventDescription(String userInput) throws DukeException, StringIndexOutOfBoundsException {
        if (userInput.contains(" ") && userInput.contains("/at")) {
            int indexOfFirstSpace = userInput.indexOf(" ");
            int indexOfSlash = userInput.indexOf("/at");
            // +1 to exclude " " and -1 to exclude "/"
            String description = userInput.substring(indexOfFirstSpace + 1, indexOfSlash - 1).trim();
            if (description.isEmpty()) {
                throw new StringIndexOutOfBoundsException();
            }
            if (description.contains(",")) {
                throw new DukeException("Please do not use ',' in the description");
            }
            return description;
        }
        throw new DukeException("Oops! It seems that you left out the /at in your command!");
    }

    /**
     * Returns the Date specified when creating an event task.
     * The function processes the whole user input as a string.
     * The function would assume the date is located
     * after the "/at" in the user's input.
     *
     * @param userInput The whole user command as String.
     * @return The date of the event task in String
     * @throws DukeException If "/at" is not found in the user's command.
     *                       If ',' was found in the user's command,
     *                       this is a check to prevent errors when storing into a CSV file.
     * @throws StringIndexOutOfBoundsException If user command does not contain a description.
     */
    public static String getEventDate(String userInput) throws DukeException, StringIndexOutOfBoundsException {
        if (userInput.contains("/at")) {
            int indexOfSlash = userInput.indexOf("/at");
            // +3 to exclude "/at"
            String eventDate = userInput.substring(indexOfSlash + 3).trim();
            if (eventDate.isEmpty()) {
                throw new StringIndexOutOfBoundsException();
            }
            if (eventDate.contains(",")) {
                throw new DukeException("Please do not use ',' in the date");
            }
            return eventDate;
        }
        throw new DukeException("Oops! It seems that you left out the date time for the /at command!");
    }

    /**
     * Returns the description of a todo task from the user's input.
     * The function processes the whole user input as a string.
     * The function would assume the description is located
     * after the first " " in the user's input.
     *
     * @param userInput The whole user command as String.
     * @return The description of the event task in String.
     * @throws DukeException If " " is not found in the user's command.
     *                       If ',' was found in the user's command,
     *                       this is a check to prevent errors when storing into a CSV file.
     * @throws StringIndexOutOfBoundsException If user command does not contain a description.
     */
    public static String getToDoDescription(String userInput) throws DukeException, StringIndexOutOfBoundsException {
        if (userInput.contains(" ")) {
            int indexOfFirstSpace = userInput.indexOf(" ");
            // +1 to exclude " "
            String description = userInput.substring(indexOfFirstSpace + 1).trim();
            if (description.isEmpty()) {
                throw new StringIndexOutOfBoundsException();
            }
            if (description.contains(",")) {
                throw new DukeException("Please do not use ',' in the description");
            }
            return description;
        }
        throw new DukeException("Oops! The description of a todo task cannot be empty!");
    }

    /**
     * Converts the date as string to LocalDate object
     *
     * @param dateTime The date to be formatted in String.
     * @return The date as LocalDate object
     */
    public static LocalDate getDateFormat(String dateTime) {
        LocalDate date = null;
        try {
            date = LocalDate.parse(dateTime);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Oops! Date Format should be in yyyy-mm-dd. " +
                    "E.g (2022-02-19)", e.getParsedString(), e.getErrorIndex());
        }
        return date;
    }
}
