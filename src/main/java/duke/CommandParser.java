package duke;

import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CommandParser {

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

    public static String getCommandFromUserInput(String userInput) {
        return userInput.split(" ")[0].toLowerCase();
    }

    public static String getSearchStringFromUserInput(String userInput) {
        String userSearchString;
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
