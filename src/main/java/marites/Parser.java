package marites;

import marites.command.AddTaskCommand;
import marites.command.Command;
import marites.command.DeleteCommand;
import marites.command.ExitCommand;
import marites.command.FindCommand;
import marites.command.ListCommand;
import marites.command.SetTaskStatusCommand;
import marites.exception.*;
import marites.task.Deadline;
import marites.task.Event;
import marites.task.Task;
import marites.task.Todo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Parser {

    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_ADD_TODO = "todo";
    private static final String COMMAND_ADD_DEADLINE = "deadline";
    private static final String COMMAND_ADD_EVENT = "event";
    private static final String COMMAND_ADD_DEADLINE_TAG = "--by";
    private static final String COMMAND_ADD_EVENT_TAG = "--at";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";

    private static final SimpleDateFormat DATE_TIME_PARSER = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static Command parse(String inputCommand) throws MaritesException {
        String[] commandSplit = splitCommandTypeAndCommand(inputCommand);
        String commandType = commandSplit[0], command = commandSplit[1];
        switch (commandType) {
        case COMMAND_EXIT:
            return new ExitCommand();
        case COMMAND_LIST:
            return new ListCommand();
        case COMMAND_MARK:
            return new SetTaskStatusCommand(parseTaskIndex(command), true);
        case COMMAND_UNMARK:
            return new SetTaskStatusCommand(parseTaskIndex(command), false);
        case COMMAND_ADD_TODO:
        case COMMAND_ADD_DEADLINE:
        case COMMAND_ADD_EVENT:
            // These 3 cases all fallthrough
            return parseAddTask(commandType, command);
        case COMMAND_DELETE:
            return new DeleteCommand(parseTaskIndex(command));
        case COMMAND_FIND:
            return new FindCommand(command);
        default:
            throw new UnknownTaskTypeException(commandType);
        }
    }

    public static int parseTaskIndex(String index) throws MaritesException {
        try {
            return Integer.parseInt(index);
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndexException(index);
        }
    }

    /**
     * Divides a line of user input into the command type, and the command.
     * @param userInput The user's input
     * @return A String[] with length 2. The first element is the command type,
     *  while the second element is the command. If the command has no body
     *  (e.g. list, unmark), this second element is an empty string.
     */
    private static String[] splitCommandTypeAndCommand(String userInput) {
        String[] inputSplit = userInput.split("\\s", 2);
        return new String[]{inputSplit[0].strip(),
                (inputSplit.length > 1 ? inputSplit[1] : "").strip()};
    }

    /**
     * Parses an add task command given by the user.
     * @param taskType The task's type.
     * @param command The user's command.
     * @return A marites.Command object representing the command.
     */
    private static Command parseAddTask(String taskType, String command)
            throws MaritesException {
        String[] parametersSplit;
        if (command.length() == 0) {
            throw new EmptyTaskDescriptionException();
        }
        Task newTask;
        LocalDateTime dateTime;
        switch (taskType) {
        case COMMAND_ADD_TODO:
            newTask = new Todo(command);
            break;
        case COMMAND_ADD_DEADLINE:
            parametersSplit = getAddParameters(command, COMMAND_ADD_DEADLINE_TAG);
            dateTime = parseDateTime(parametersSplit[1].strip());
            newTask = new Deadline(parametersSplit[0].strip(), dateTime);
            break;
        case COMMAND_ADD_EVENT:
            parametersSplit = getAddParameters(command, COMMAND_ADD_EVENT_TAG);
            dateTime = parseDateTime(parametersSplit[1].strip());
            newTask = new Event(parametersSplit[0].strip(), dateTime);
            break;
        default:
            throw new UnknownTaskTypeException(taskType);
        }
        return new AddTaskCommand(newTask);
    }

    private static String[] getAddParameters(String command, String separator) throws MissingParameterException {
        String[] parametersSplit;
        parametersSplit = command.split(COMMAND_ADD_DEADLINE_TAG);
        if (parametersSplit.length == 1) {
            throw new MissingParameterException(COMMAND_ADD_DEADLINE_TAG);
        }
        return parametersSplit;
    }

    private static LocalDateTime parseDateTime(String dateTime) throws InvalidDateTimeException {
        try {
            return DATE_TIME_PARSER.parse(dateTime)
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
        } catch (ParseException e) {
            throw new InvalidDateTimeException(dateTime);
        }
    }
}
