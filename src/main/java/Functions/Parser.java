package Functions;

import Commands.*;

import Exceptions.BadDateTimeFormatException;
import Exceptions.NoDateTimeException;
import Exceptions.NoKeywordException;
import Exceptions.NoTaskDescriptionException;

import static Constants.TaskManagerConstants.EVENT_LENGTH;
import static Constants.TaskManagerConstants.DEADLINE_LENGTH;
import static Constants.TaskManagerConstants.TODO_LENGTH;
import static Constants.TaskManagerConstants.DATETIME_DELIMITER_LENGTH;

public class Parser {
    public static Command commandParse(String command) throws NoTaskDescriptionException, NoDateTimeException,
            BadDateTimeFormatException, NoKeywordException {
        String commandWord = command.trim().toLowerCase().split(" ")[0];

        try {
            switch (commandWord) {
            case "list":
                return new ListCommand();
            case "mark":
                int markIndex = getIndex(command);
                return new MarkCommand(markIndex);
            case "unmark":
                int unmarkIndex = getIndex(command);
                return new UnmarkCommand(unmarkIndex);
            case "todo":
                String todoDescription;
                todoDescription = command.substring(TODO_LENGTH).trim();

                if (todoDescription.isBlank()) {
                    throw new NoTaskDescriptionException("Task description cannot be blank.");
                }

                return new NewTodoCommand(todoDescription);
            case "deadline":
                String[] deadlineMsgParsed = deadlineEventParse(command);
                return new NewDeadlineCommand(deadlineMsgParsed[0], deadlineMsgParsed[1]);
            case "event":
                String[] eventMsgParsed = deadlineEventParse(command);
                return new NewEventCommand(eventMsgParsed[0], eventMsgParsed[1]);
            case "delete":
                int deleteIndex = getIndex(command);
                return new DeleteTaskCommand(deleteIndex);
            case "find":
                String keyword = getKeyword(command);
                return new FindCommand(keyword);
            case "bye":
                return new ExitCommand();
            default:
                return new UnknownCommand();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    // String parser for Deadline and Event
    public static String[] deadlineEventParse(String msg) throws BadDateTimeFormatException,
            NoTaskDescriptionException, NoDateTimeException {
        int dateTimeDelimiterIndex, descDelimiterIndex;
        String[] strings = new String[2];
        boolean isDeadline = msg.indexOf("/by") > -1;

        try {
            // Get delimiters
            if (isDeadline) {
                dateTimeDelimiterIndex = msg.indexOf("/by");
                descDelimiterIndex = DEADLINE_LENGTH;
            } else {
                dateTimeDelimiterIndex = msg.indexOf("/at");
                descDelimiterIndex = EVENT_LENGTH;
            }

            if (dateTimeDelimiterIndex == -1) {
                throw new BadDateTimeFormatException("No delimiter.");
            }

            // Parse message (0 - Description, 1 - DateTime)
            strings[0] = msg.substring(descDelimiterIndex, dateTimeDelimiterIndex).trim(); // Task description
            strings[1] = msg.substring(dateTimeDelimiterIndex + DATETIME_DELIMITER_LENGTH).trim(); // DateTime

            if (strings[0].isBlank()) {
                throw new NoTaskDescriptionException("Task description cannot be blank.");
            }

            if (strings[1].isBlank()) {
                throw new NoDateTimeException("Task DateTime is empty.");
            }

            return strings;
        } catch (Exception e) {
            throw e;
        }
    }

    // Return the index of task to mark/unmark
    public static int getIndex(String msg) throws NumberFormatException {
        try {
            // Extract Task number as String and parse into int
            int ind = Integer.parseInt( msg.substring(msg.indexOf(' ') + 1) );
            return --ind;
        } catch (Exception e) {
            throw e;
        }
    }

    // Return the keyword to find in tasks
    public static String getKeyword(String msg) throws NoKeywordException {
        try {
            if (msg.indexOf(' ') == -1 || msg.indexOf(' ') + 1 >= msg.length()) {
                throw new NoKeywordException("No keyword is given");
            }
            String keyword = msg.substring(msg.indexOf(' ') + 1);
            return keyword;
        } catch (Exception e) {
            throw e;
        }
    }
}
