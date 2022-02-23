package duke.taskmanagement;

import duke.commands.*;
import duke.customexceptions.EmptyDescriptionException;
import duke.customexceptions.EmptyTimingDetailsException;
import duke.task.Task;
import duke.userinterface.UserInterface;

import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {
    private static int taskUniqueID = 0;
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static TaskRecorder taskRecorder = new TaskRecorder();
    private static UserInterface ui = new UserInterface();

    public TaskManager() {
        try {
            ArrayList<String[]> fileData = taskRecorder.loadData();
            tasks = taskRecorder.createTasks(fileData);
            taskUniqueID = tasks.size() - 1;
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
            Command addTodoCommand = new AddTodoCommand();
            addTodoCommand.execute(tasks, userInput,taskUniqueID);
            taskRecorder.addData(userInput);
        } catch (IOException e) {
            ui.printIOExceptionMessageWrite();
        }
    }

    public void addDeadline(String userInput) {
        try {
            Command addDeadlineCommand = new AddDeadlineCommand();
            addDeadlineCommand.execute(tasks, userInput, taskUniqueID);
            taskRecorder.addData(userInput);
        } catch (IOException e) {
            ui.printIOExceptionMessageWrite();
        }
    }

    public void addEvent(String userInput) {
        try {
            Command addEventCommand = new AddEventCommand();
            addEventCommand.execute(tasks, userInput,taskUniqueID);
            taskRecorder.addData(userInput);
        } catch (IOException e) {
            ui.printIOExceptionMessageWrite();
        }
    }

    public void markOrUnmarkTask(String userInput) {
        try {
            Command markOrUnmarkTaskCommand = new MarkOrUnmarkTaskCommand();
            markOrUnmarkTaskCommand.execute(tasks, userInput, taskUniqueID);
            taskRecorder.addData(userInput);
        } catch (IOException e) {
            ui.printIOExceptionMessageWrite();
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            ui.printOutOfBoundsExceptionMessage();
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
            ui.printOutOfBoundsExceptionMessage();
        } catch (NumberFormatException e) {
            ui.printNumberFormatExceptionMessage();
        }
    }
}