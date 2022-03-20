package duke.taskmanagement;

import duke.commands.*;
import duke.customexceptions.EmptyDescriptionException;
import duke.customexceptions.EmptyTimingDetailsException;
import duke.task.Task;
import duke.userinterface.UserInterface;

import java.io.IOException;
import java.util.ArrayList;

/**
 * In charge of the array list of tasks that is updated by the user. This includes reading, writing, updating and
 * deleting tasks as the user interacts with the program.
 */
public class TaskManager {
    private static int taskUniqueID = 0;
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static TaskRecorder taskRecorder = new TaskRecorder();
    private static UserInterface ui = new UserInterface();

    /**
     * Calls taskRecorder methods to create the initial list of tasks based on file data when Duke first starts up.
     */
    public TaskManager() {
        try {
            ArrayList<String[]> fileData = taskRecorder.loadData();
            tasks = taskRecorder.createTasks(fileData);
            taskUniqueID = tasks.size() - 1;
            ui.printSaveDataFileInitializedMessage();
        } catch (IOException e) {
            ui.printIOExceptionMessageRead();
        } catch (EmptyDescriptionException e) {
            ui.printEmptyDescriptionMessage();
        } catch (EmptyTimingDetailsException e) {
            ui.printEmptyTimingDetailsMessage();
        }
    }

    /**
     * Creates a listTasksCommand instance to pass in the inputs for command execution.
     * @param userInput is the string input by the user.
     */
    public void listTasks(String userInput) {
        Command listTasksCommand = new ListTasksCommand();
        listTasksCommand.execute(tasks, userInput, taskUniqueID);
    }

    /**
     * Creates a findTasksCommand instance to pass in the inputs for command execution.
     * @param userInput is the string input by the user.
     */
    public void findTasks(String userInput) {
        Command findTasksCommand = new FindTasksCommand();
        findTasksCommand.execute(tasks, userInput, taskUniqueID);
    }

    /**
     * Creates a addTodoCommand instance to pass in the inputs for command execution.
     * Checks if saving to data file is needed after command execution.
     * @param userInput is the string input by the user.
     */
    public void addTodo(String userInput) {
        try {
            AddCommand addTodoCommand = new AddTodoCommand();
            addTodoCommand.execute(tasks, userInput,taskUniqueID);
            if (addTodoCommand.isSaveRequired()) {
                taskRecorder.addData(userInput);
            }
        } catch (IOException e) {
            ui.printIOExceptionMessageWrite();
        }
    }

    /**
     * Creates a addDeadlineCommand instance to pass in the inputs for command execution.
     * Checks if saving to data file is needed after command execution.
     * @param userInput is the string input by the user.
     */
    public void addDeadline(String userInput) {
        try {
            AddCommand addDeadlineCommand = new AddDeadlineCommand();
            addDeadlineCommand.execute(tasks, userInput, taskUniqueID);
            if (addDeadlineCommand.isSaveRequired()) {
                taskRecorder.addData(userInput);
            }
        } catch (IOException e) {
            ui.printIOExceptionMessageWrite();
        }
    }

    /**
     * Creates a addEventCommand instance to pass in the inputs for command execution.
     * Checks if saving to data file is needed after command execution.
     * @param userInput is the string input by the user.
     */
    public void addEvent(String userInput) {
        try {
            AddCommand addEventCommand = new AddEventCommand();
            addEventCommand.execute(tasks, userInput,taskUniqueID);
            if (addEventCommand.isSaveRequired()) {
                taskRecorder.addData(userInput);
            }
        } catch (IOException e) {
            ui.printIOExceptionMessageWrite();
        }
    }

    /**
     * Creates a markOrUnmarkTaskCommand instance to pass in the inputs for command execution.
     * Saves to data file if no error is thrown.
     * Calls ui methods to check for input formatting and prompts user for format errors if needed
     * when exceptions are thrown.
     * @param userInput is the string input by the user.
     */
    public void markOrUnmarkTask(String userInput) {
        try {
            Command markOrUnmarkTaskCommand = new MarkOrUnmarkTaskCommand();
            markOrUnmarkTaskCommand.execute(tasks, userInput, taskUniqueID);
            int taskNumber = ui.getTaskNumber(userInput);
            taskRecorder.markOrUnmarkData(taskNumber);
        } catch (IOException e) {
            ui.printIOExceptionMessageWrite();
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            ui.printOutOfBoundsExceptionMessage(tasks);
        } catch (NumberFormatException e) {
            ui.printNumberFormatExceptionMessage();
        }
    }

    /**
     * Creates a deleteCommand instance to pass in the inputs for command execution.
     * Saves to data file if no error is thrown.
     * Calls ui methods to check for input formatting and prompts user for format errors if needed
     * when exceptions are thrown.
     * @param userInput is the string input by the user.
     */
    public void deleteTask(String userInput) {
        try {
            Command deleteTaskCommand = new DeleteTaskCommand();
            deleteTaskCommand.execute(tasks, userInput, taskUniqueID);
            int number = ui.getTaskNumber(userInput);
            taskRecorder.deleteData(number);
        } catch (IOException e) {
            ui.printIOExceptionMessageWrite();
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            ui.printOutOfBoundsExceptionMessage(tasks);
        } catch (NumberFormatException e) {
            ui.printNumberFormatExceptionMessage();
        }
    }
}