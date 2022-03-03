package Functions;

import Commands.*;

import Exceptions.BadDateTimeFormatException;
import Exceptions.NoDateTimeException;
import Exceptions.NoKeywordException;
import Exceptions.NoTaskDescriptionException;

import static Constants.TaskManagerConstants.LENGTH_EVENT;
import static Constants.TaskManagerConstants.LENGTH_DEADLINE;
import static Constants.TaskManagerConstants.LENGTH_TODO;
import static Constants.TaskManagerConstants.LENGTH_DATETIME_DELIMITER;

/**
 * User input processor.
 */
public class Parser {
    /**
     * Creates corresponding <code>Command</code> object based on keyword input.
     *
     * @param command Input containing command keyword and required information for command execution.
     * @return <code>Command</code> object.
     * @throws NoTaskDescriptionException no description about the task is given when adding any task.
     * @throws NoDateTimeException no date and time is given when adding deadline or event.
     * @throws BadDateTimeFormatException format to input date and time is not followed.
     */
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
                todoDescription = command.substring(LENGTH_TODO).trim();

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

    /**
     * Decomposes user commands for adding deadlines or events. Separates description from date and time.
     *
     * @param msg User command to parse.
     * @return Array of Strings. String at index 0 is task/event description. String at index 1 is date and time.
     * @throws NoTaskDescriptionException no description about the task is given when adding any task.
     * @throws NoDateTimeException no date and time is given when adding deadline or event.
     * @throws BadDateTimeFormatException format to input date and time is not followed.
     */
    public static String[] deadlineEventParse(String msg) throws BadDateTimeFormatException,
            NoTaskDescriptionException, NoDateTimeException {
        int dateTimeDelimiterIndex, descDelimiterIndex;
        String[] strings = new String[2];
        boolean isDeadline = msg.indexOf("/by") > -1;

        try {
            // Get delimiters
            if (isDeadline) {
                dateTimeDelimiterIndex = msg.indexOf("/by");
                descDelimiterIndex = LENGTH_DEADLINE;
            } else {
                dateTimeDelimiterIndex = msg.indexOf("/at");
                descDelimiterIndex = LENGTH_EVENT;
            }

            if (dateTimeDelimiterIndex == -1) {
                throw new BadDateTimeFormatException("No delimiter.");
            }

            // Parse message (0 - Description, 1 - DateTime)
            strings[0] = msg.substring(descDelimiterIndex, dateTimeDelimiterIndex).trim(); // Task description
            strings[1] = msg.substring(dateTimeDelimiterIndex + LENGTH_DATETIME_DELIMITER).trim(); // DateTime

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

    /**
     * Decomposes user commands for marking, unmarking, and deleting. Separates command keyword from index of target.
     *
     * @param msg User command to parse.
     * @return Index of target task in task list.
     * @throws NumberFormatException an integer is not given after the command keyword.
     */
    public static int getIndex(String msg) throws NumberFormatException {
        try {
            // Extract Task number as String and parse into int
            int ind = Integer.parseInt( msg.substring(msg.indexOf(' ') + 1) );
            return --ind;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Decomposes user command for finding. Separates command keyword from target keyword.
     *
     * @param msg User command to parse.
     * @return Keyword to find.
     * @throws NoKeywordException no keyword was given after the command keyword.
     */
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
