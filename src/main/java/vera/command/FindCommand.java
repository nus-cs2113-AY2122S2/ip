package vera.command;

import vera.Storage;
import vera.TaskList;
import vera.Ui;
import vera.exception.InputEmptyException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static vera.constant.DateAndTimeFormat.noTimeFormat;
import static vera.constant.DateAndTimeFormat.withTimeFormat;
import static vera.constant.Indexes.FIND_BY_TASK_DESCRIPTION_NO_DATE_INDEX;
import static vera.constant.Messages.DATE_FORMAT_WITHOUT_TIME;
import static vera.constant.Messages.DATE_FORMAT_WITH_TIME;

public class FindCommand extends Command {
    private String findTaskByDescription;
    private String findTaskByDate;

    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = "Find: Finds related task descriptions \nbased on the given input.\n"
            + "\nTo search for a task, \nenter 'find <keyword>', e.g. 'find book'";


    public FindCommand(String[] filteredSearchKeyword, String dateInput) throws InputEmptyException {
        if (dateInput == null) {
            if (filteredSearchKeyword[FIND_BY_TASK_DESCRIPTION_NO_DATE_INDEX].isBlank()) {
                throw new InputEmptyException();
            }
            findTaskByDescription = filteredSearchKeyword[FIND_BY_TASK_DESCRIPTION_NO_DATE_INDEX].trim();
        } else {
            findTaskByDate = dateInput;
        }
    }

    private String checkDateFormat() {
        try {
            return LocalDate.parse(findTaskByDate, noTimeFormat)
                    .format(DateTimeFormatter.ofPattern(DATE_FORMAT_WITHOUT_TIME));
        } catch (DateTimeParseException e) {
            return null;
        }
    }


    private String checkTimeInput() {
        try {
            return LocalDateTime.parse(findTaskByDate, withTimeFormat)
                    .format(DateTimeFormatter.ofPattern(DATE_FORMAT_WITH_TIME));
        } catch (DateTimeParseException e) {
            return checkDateFormat();
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (findTaskByDate == null) {
            taskList.findByTaskDescription(findTaskByDescription, ui);
        } else {
            String taskDateToSearch = checkTimeInput();
            taskList.findByTaskDate(taskDateToSearch, ui);
        }
    }
}
