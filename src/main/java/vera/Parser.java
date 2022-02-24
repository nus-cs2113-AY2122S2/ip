package vera;

import vera.command.Command;
import vera.command.ListCommand;
import vera.command.MarkCommand;
import vera.command.UnmarkCommand;
import vera.command.TodoCommand;
import vera.command.EventCommand;
import vera.command.DeadlineCommand;
import vera.command.UpdateCommand;
import vera.command.DeleteCommand;
import vera.command.HelpCommand;
import vera.command.ExitCommand;
import vera.command.FindCommand;

import vera.constant.DateAndTimeFormat;
import vera.exception.InputEmptyException;
import vera.exception.InputRepeatedException;
import vera.task.Deadline;
import vera.task.Event;
import vera.task.Task;
import vera.task.Todo;


import static vera.constant.Indexes.INPUT_TO_FIND_INDEX;
import static vera.constant.Indexes.SAVE_TASK_DESCRIPTION_INDEX;
import static vera.constant.Indexes.SAVE_TASK_TYPE_INDEX;
import static vera.constant.Indexes.HELP_OPTIONS_INDEX;
import static vera.constant.Indexes.MARK_INDEX;
import static vera.constant.Indexes.OPTIONS_INDEX;
import static vera.constant.Indexes.SAVE_TASK_DATE_INDEX;
import static vera.constant.Indexes.SAVE_TASK_MARK_STATUS;
import static vera.constant.Indexes.TASK_CONTENT_INDEX;
import static vera.constant.Indexes.TASK_DATE_INDEX;
import static vera.constant.Messages.ERROR_INVALID_MARKING_INDEX_MESSAGE;
import static vera.constant.Messages.ERROR_TODO_REPEATED_INPUT_MESSAGE;
import static vera.constant.Messages.ERROR_EVENT_MISSING_COMMAND_MESSAGE;
import static vera.constant.Messages.ERROR_DEADLINE_MISSING_COMMAND_MESSAGE;
import static vera.constant.Messages.ERROR_INVALID_DELETE_INDEX_MESSAGE;
import static vera.constant.Messages.HELP_MESSAGE_SPECIFIC_COMMAND;
import static vera.constant.Messages.ERROR_INVALID_INPUT_MESSAGE;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    private static boolean isReadTaskEmpty(Task parsedData) {
        switch (parsedData.getType()) {
        case "T":
            return parsedData.getDescription().isBlank();
        case "D":
            // fallthrough since Deadline and Event have the same getters
        case "E":
            return parsedData.getDescription().isBlank() || parsedData.getDate().isBlank();
        default:
            return false;
        }
    }

    public static Task parseSavedData(String[] rawData) throws InputEmptyException {
        Task parsedData;
        if (!rawData[SAVE_TASK_MARK_STATUS].equals("0") &&
                !rawData[SAVE_TASK_MARK_STATUS].equals("1")) {
            throw new InputEmptyException();
        }

        switch (rawData[SAVE_TASK_TYPE_INDEX].trim()) {
        case "T":
            parsedData = new Todo(rawData[SAVE_TASK_DESCRIPTION_INDEX]);
            break;
        case "D":
            parsedData = new Deadline(rawData[SAVE_TASK_DESCRIPTION_INDEX],
                    rawData[SAVE_TASK_DATE_INDEX]);
            break;
        case "E":
            parsedData = new Event(rawData[SAVE_TASK_DESCRIPTION_INDEX],
                    rawData[SAVE_TASK_DATE_INDEX]);
            break;
        default:
            throw new InputEmptyException();
        }

        if (isReadTaskEmpty(parsedData)) {
            throw new InputEmptyException();
        }

        if (rawData[SAVE_TASK_MARK_STATUS].equals("1")) {
            parsedData.markAsDone();
        }

        return parsedData;
    }


    private static Command prepareMarkOrUnmark(String[] parsedInput, String commandWord, TaskList taskList) {
        try {
            if (parsedInput[MARK_INDEX].isBlank()) {
                System.out.println(ERROR_INVALID_MARKING_INDEX_MESSAGE);
                return null;
            }
            int markIndex = Integer.parseInt(parsedInput[MARK_INDEX]) - 1;
            if (commandWord.equals(MarkCommand.COMMAND_WORD)) {
                return new MarkCommand(markIndex, taskList);
            }
            return new UnmarkCommand(markIndex, taskList);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(ERROR_INVALID_MARKING_INDEX_MESSAGE);
        }
        return null;
    }

    private static void printMissingInputMessage(String input, String taskType) {
        System.out.println("☹ Oops! The " + input + " of a(n) " + taskType + " cannot be empty."
                + HELP_MESSAGE_SPECIFIC_COMMAND);
    }

    private static Command prepareAddTodo(String[] parsedInput, TaskList taskList) {
        try {
            return new TodoCommand(parsedInput, taskList);
        } catch (ArrayIndexOutOfBoundsException | InputEmptyException e) {
            printMissingInputMessage("description", "todo");
        } catch (InputRepeatedException e) {
            System.out.println(ERROR_TODO_REPEATED_INPUT_MESSAGE);
        }
        return null;
    }

    private static void printMissingCommandMessage(String taskType) {
        if (taskType.equals("event")) {
            System.out.println(ERROR_EVENT_MISSING_COMMAND_MESSAGE + HELP_MESSAGE_SPECIFIC_COMMAND);
        } else {
            System.out.println(ERROR_DEADLINE_MISSING_COMMAND_MESSAGE + HELP_MESSAGE_SPECIFIC_COMMAND);
        }
    }


    private static LocalDateTime confirmInvalidDateFormat() throws InputEmptyException {
        Ui anotherUi = new Ui();
        anotherUi.showToUser("It seems that the date and time\nyou gave is not in the correct format.\n"
                + "Would you like to reenter a valid date and time? (Y/N)\n"
                + "*Enter 'No' to skip the adding of this task*");
        anotherUi.showLine();
        while (true) {
            String input = anotherUi.readCommand();
            anotherUi.showLine();
            if (input.trim().equalsIgnoreCase("Y")
                    || input.trim().equalsIgnoreCase("Yes")) {
                anotherUi.showToUser("Understood. Please key in the date and time you wish to save.");
                anotherUi.showLine();
                anotherUi.showToUser("Enter valid date input: ");
                input = anotherUi.readCommand();
                anotherUi.showLine();
                return prepareTaskDate(input.trim());
            }
            if (input.trim().equalsIgnoreCase("N")
                    || input.trim().equalsIgnoreCase("No")) {
                anotherUi.showToUser("Okay, proceeding to stop adding/updating this task...");
                return null;
            }
            anotherUi.showToUser("Please confirm your choice with either Y (Yes) or N (No).");
            anotherUi.showLine();
        }
    }

    private static LocalDateTime prepareTaskDate(String rawTaskDate) throws InputEmptyException {
        if (rawTaskDate.isBlank()) {
            throw new InputEmptyException();
        }
        DateTimeFormatter[] dateFormats = DateAndTimeFormat.getFormat();
        int i = 0;
        while (i < dateFormats.length)  {
            try {
                return LocalDateTime.parse(rawTaskDate, dateFormats[i]);
            } catch (DateTimeParseException e) {
                i++;
            }
        }
        return confirmInvalidDateFormat();
    }

    private static Command prepareAddEventOrDeadline(String[] parsedInput, String inputKeyword,
                                                     TaskList taskList, String taskType) {
        String[] filteredTaskContent = null;
        LocalDateTime dueDate = null;
        try {
            if (!parsedInput[TASK_CONTENT_INDEX].contains(inputKeyword)) {
                printMissingCommandMessage(taskType);
                return null;
            }
            filteredTaskContent = parsedInput[TASK_CONTENT_INDEX].split(inputKeyword, 2);
            dueDate = prepareTaskDate(filteredTaskContent[TASK_DATE_INDEX].trim());
            if (dueDate == null) {
                return null;
            }
            if (taskType.equals(EventCommand.COMMAND_WORD)) {
                return new EventCommand(filteredTaskContent, taskList, dueDate);
            }
            return new DeadlineCommand(filteredTaskContent, taskList, dueDate);
        } catch (ArrayIndexOutOfBoundsException | InputEmptyException e) {
            printMissingInputMessage("description and/or date\n", taskType);
        } catch (InputRepeatedException e) {
            return new UpdateCommand(filteredTaskContent, dueDate);
        }
        return null;
    }

    private static Command prepareDelete(String[] parsedInput, TaskList taskList) {
        try {
            return new DeleteCommand(parsedInput, taskList);
        } catch (IndexOutOfBoundsException | InputEmptyException | NumberFormatException e) {
            System.out.println(ERROR_INVALID_DELETE_INDEX_MESSAGE + HELP_MESSAGE_SPECIFIC_COMMAND);
        }
        return null;
    }

    private static Command prepareHelp(String userInput) {
        try {
            String[] parsedInput = userInput.split(" ", 2);
            return new HelpCommand(parsedInput[HELP_OPTIONS_INDEX]);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return new HelpCommand("show help list");
        }
    }

    public static Command parseCommand(String userInput, TaskList taskList) {
        String[] parsedInput = userInput.split(" ", 2);
        String commandWord = parsedInput[OPTIONS_INDEX].toLowerCase().trim();
        switch (commandWord) {
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case MarkCommand.COMMAND_WORD:
            return prepareMarkOrUnmark(parsedInput, MarkCommand.COMMAND_WORD, taskList);
        case UnmarkCommand.COMMAND_WORD:
            return prepareMarkOrUnmark(parsedInput, UnmarkCommand.COMMAND_WORD, taskList);
        case TodoCommand.COMMAND_WORD:
            return prepareAddTodo(parsedInput, taskList);
        case EventCommand.COMMAND_WORD:
            return prepareAddEventOrDeadline(parsedInput, "/at",
                    taskList, EventCommand.COMMAND_WORD);
        case DeadlineCommand.COMMAND_WORD:
            return prepareAddEventOrDeadline(parsedInput, "/by",
                    taskList, DeadlineCommand.COMMAND_WORD);
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(parsedInput, taskList);
        case FindCommand.COMMAND_WORD:
            return new FindCommand(parsedInput[INPUT_TO_FIND_INDEX]);
        case HelpCommand.COMMAND_WORD:
            return prepareHelp(userInput);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            System.out.println(ERROR_INVALID_INPUT_MESSAGE);
            return null;
        }
    }

}
