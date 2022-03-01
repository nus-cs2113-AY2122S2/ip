package duke;

import duke.exception.DukeEmptyDescriptionException;
import duke.exception.DukeMissingTimeSeparator;
import duke.exception.DukeTaskOutOfRangeException;
import duke.task.Task;
import duke.task.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public Parser() {

    }

    /**
     * Performs add operation for tasks with type Todo.
     * Also saves the newly added task to storage and
     * handles the exception for empty task description.
     *
     * @param taskManager the object that manages task operations on tasks.
     * @param userInput the input entered by user on terminal.
     */
    public static void performTodo(TaskList taskManager, String userInput) {
        try {
            Task newTask = taskManager.addTask(userInput);
            Storage.saveDataAddToListOperation(taskManager, newTask);
        } catch (DukeEmptyDescriptionException e) {
            System.out.println("OOPS! The description of a todo cannot be empty!");
        }
    }

    /**
     * Performs add operation for tasks with type Deadline and Event.
     * Also saves the newly added task to storage and
     * handles the exception for empty task description, missing time separator
     * (e.g. '/at' for event tasks) and exception for wrong datetime format supplied.
     *
     * @param taskManager the object that manages task operations on tasks.
     * @param userInput the input entered by user on terminal.
     * @param stringSeparator such as '/at' or '/by' to identify different type of tasks.
     * @param taskType holds the task child classes, for instance, deadline.
     */
    public static void performTaskWithTimeConstraints(TaskList taskManager, String userInput, String stringSeparator, String taskType) {
        try {
            Task newTask = taskManager.addTaskWithTime(userInput, stringSeparator);
            Storage.saveDataAddToListOperation(taskManager, newTask);
        } catch (DukeEmptyDescriptionException e) {
            System.out.println("OOPS! The description of a " + taskType + " cannot be empty!");
        } catch (DukeMissingTimeSeparator e) {
            System.out.println("OOPS! You did not include '" + stringSeparator + "' in your command!");
        } catch (DateTimeParseException e) {
            System.out.println("Incorrect date time format supplied. Use 'yyyy-mm-dd [01-24]'\n" +
                    "Example: event party /at 2021-10-09 05");
        }
    }

    /**
     * Performs mark operations for tasks to be marked or unmarked.
     * Also saves the modified mark status of that task to storage and
     * handles the exception for empty description, number format
     * and task input that is not within the range of the task list.
     *
     * @param taskManager the object that manages task operations on tasks.
     * @param userInput the input entered by user on terminal.
     * @param isMarked true if task is to be marked, false if it is to be unmarked.
     * @param markType either 'mark' or 'unmark'
     */
    public static void performMarking(TaskList taskManager, String userInput, boolean isMarked, String markType) {
        try {
            //Gets task number which corresponds to line number in storage save data.
            int taskNumberMarked = taskManager.markTask(isMarked, userInput);
            Storage.saveDataChangeMarkStatusOperation(taskManager, taskNumberMarked, isMarked);
        } catch (DukeEmptyDescriptionException e) {
            System.out.println("OOPS! Please add the list number you want to " + markType +"!");
        } catch (NumberFormatException e) {
            System.out.println("OOPS! Specify a number for the list to " + markType +"!");
        } catch (DukeTaskOutOfRangeException e) {
            System.out.println("Task does not exist!");
        }
    }

    /**
     * Performs delete operation for tasks to be deleted.
     * Also modifies storage to remove the task that was deleted and
     * handles the exception for empty description, number format
     * and task input that is not within the range of the task list.
     *
     * @param taskManager the object that manages task operations on tasks.
     * @param userInput the input entered by user on terminal.
     */
    public static void performDeletion(TaskList taskManager, String userInput) {
        try {
            int taskNumberToDelete = taskManager.deleteTask(userInput);
            Storage.saveDataDeleteFromListOperation(taskManager, taskNumberToDelete);
        } catch (DukeEmptyDescriptionException e) {
            System.out.println("OOPS! Please add the list number you want to delete!");
        } catch (NumberFormatException e) {
            System.out.println("OOPS! Specify a number for the list to delete!");
        } catch (DukeTaskOutOfRangeException e) {
            System.out.println("Task does not exist!");
        }
    }

    /**
     * Returns the task description that is extracted from the original full
     * user input supplied in the terminal.
     * Also checks that the task description is not empty.
     *
     * @param userInput the input entered by user on terminal.
     * @return only the task description from original command.
     * @throws DukeEmptyDescriptionException If description of task is empty.
     */
    public static String validateAndExtractTaskDescription(String userInput) throws DukeEmptyDescriptionException {
        String[] arrayOfTaskStrings = userInput.split(" ");
        if (arrayOfTaskStrings.length <= 1) {
            throw new DukeEmptyDescriptionException();
        }

        String extractTaskDescription = "";
        for (int i = 1; i < arrayOfTaskStrings.length; i++) {
            extractTaskDescription += arrayOfTaskStrings[i] + " ";
        }

        return extractTaskDescription;
    }

    /**
     * Returns the integer or task number that operations such as
     * mark, unmark or delete intends to perform on. This method
     * also checks that the task number to be performed on by
     * operations is within the range of the task list.
     *
     * @param userInput the input entered by user on terminal.
     * @return the task number for operations to perform on.
     * @throws DukeTaskOutOfRangeException If the task number supplied is less or more than the current task list.
     */
    public static int fetchTaskNumber(String userInput) throws DukeTaskOutOfRangeException {
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
        if (!isWithinTaskRange(taskNumber)) {
            throw new DukeTaskOutOfRangeException();
        }
        return taskNumber;
    }

    /**
     * Returns false if the task number for operations to perform on
     * is out of range from the current number of tasks in the task list.
     * Returns true if task is within the range of task list.
     *
     * @param taskNumber the task number or integer to check.
     * @return different boolean value depending on checks.
     */
    public static boolean isWithinTaskRange(int taskNumber) {
        if (taskNumber > Task.getNumberOfTasks() || taskNumber <= 0) {
            return false;
        }
        return true;
    }

    /**
     * Performs search operation to find a task by searching
     * for a keyword and handles the exception for empty description.
     *
     * @param taskManager the object that manages task operations on tasks.
     * @param userInput the input entered by user on terminal.
     */
    public static void performFindTask(TaskList taskManager, String userInput) {
        try {
            taskManager.findTask(userInput);
        } catch (DukeEmptyDescriptionException e) {
            System.out.print("OOPS! The keyword to find cannot be empty!\n");
        }
    }

    /**
     * Returns converted deadline date as LocalDateTime data type from String originally.
     * The deadline format accepted from the user input is 'yyyy-MM-dd kk'.
     * The converted format or format to be stored in the save file is as follows:'MMM d yyyy kka'.
     *
     * @param dateTimeToConvert the deadline extracted from user input as String data type.
     * @return the converted deadline as LocalDateTime data type.
     */
    public static LocalDateTime parseDateTime(String dateTimeToConvert) {
        String correctDatePattern = "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}";
        if(!dateTimeToConvert.trim().matches(correctDatePattern)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy kka");
            dateTimeToConvert = LocalDateTime.parse(dateTimeToConvert.trim(), formatter)
                    .toString().replace("T"," ").replace(":00","");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd kk");
        LocalDateTime convertToLocalDate = LocalDateTime.parse(dateTimeToConvert.trim(), formatter);
        return convertToLocalDate;
    }
}
