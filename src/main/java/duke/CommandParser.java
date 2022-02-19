package duke;

import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CommandParser {

    public static void executeCommand(String input, String command, TaskList taskList) {
        switch (command) {
        case "mark":
        case "unmark":
            taskList.markTask(input, command);
            break;
        case "list":
            taskList.printTaskList();
            break;
        case "delete":
            taskList.deleteTask(input);
            break;
        default:
            taskList.addTaskToTaskList(input, command);
            break;
        }
    }

    public static String getCommandFromUserInput(String input) {
        return input.split(" ")[0].toLowerCase();
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

    public static String getDeadlineTaskDescription(String input) throws DukeException, StringIndexOutOfBoundsException {
        if (input.contains("/by")) {
            int firstSpaceIndex = input.indexOf(" ");
            int slashIndex = input.indexOf("/by");
            // +1 to exclude " " and -1 to exclude "/"
            String description = input.substring(firstSpaceIndex + 1, slashIndex - 1).trim();
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

    public static String getDeadlineDate(String input) throws DukeException, StringIndexOutOfBoundsException {
        if (input.contains("/by")) {
            int slashIndex = input.indexOf("/by");
            // +3 to exclude "/by"
            String date = input.substring(slashIndex + 3).trim();
            if (date.isEmpty()) {
                throw new StringIndexOutOfBoundsException();
            }
            if (date.contains(",")) {
                throw new DukeException("Please do not use ',' in the date");
            }
            return date;
        }
        throw new DukeException("Oops! It seems that you left out the date for the /by command!");
    }

    public static String getEventTaskDescription(String input) throws DukeException, StringIndexOutOfBoundsException {
        if (input.contains(" ") && input.contains("/at")) {
            int firstSpaceIndex = input.indexOf(" ");
            int slashIndex = input.indexOf("/at");
            // +1 to exclude " " and -1 to exclude "/"
            String description = input.substring(firstSpaceIndex + 1, slashIndex - 1).trim();
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

    public static String getEventDateTime(String input) throws DukeException, StringIndexOutOfBoundsException {
        if (input.contains("/at")) {
            int slashIndex = input.indexOf("/at");
            // +3 to exclude "/at"
            String dateTime = input.substring(slashIndex + 3).trim();
            if (dateTime.isEmpty()) {
                throw new StringIndexOutOfBoundsException();
            }
            if (dateTime.contains(",")) {
                throw new DukeException("Please do not use ',' in the date");
            }
            return dateTime;
        }
        throw new DukeException("Oops! It seems that you left out the date time for the /at command!");
    }

    public static LocalDate getDateFormat(String dateTime) {
        LocalDate date = null;
        try {
            date = LocalDate.parse(dateTime);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Oops! Date Format should be in yyyy-mm-dd. " +
                    "E.g (2021-12-05)", e.getParsedString(), e.getErrorIndex());
        }
        return date;
    }

    public static String getToDoTaskDescription(String input) throws DukeException, StringIndexOutOfBoundsException {
        if (input.contains(" ")) {
            int firstSpaceIndex = input.indexOf(" ");
            // +1 to exclude " "
            String description = input.substring(firstSpaceIndex + 1).trim();
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
}
