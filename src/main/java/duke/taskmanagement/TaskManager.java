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

    public void listTasks(String userInput) {
        Command listTasksCommand = new ListTasksCommand();
        listTasksCommand.execute(tasks, userInput, taskUniqueID);
    }

    public void findTasks(String userInput) {
        Command findTasksCommand = new FindTasksCommand();
        findTasksCommand.execute(tasks, userInput, taskUniqueID);
    }

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