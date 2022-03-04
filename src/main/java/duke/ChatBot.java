package duke;

import duke.command.FindTaskCommand;
import duke.command.UpdateTaskStatusCommand;
import duke.command.AddTaskCommand;
import duke.command.DeleteTaskCommand;
import duke.command.Command;

import duke.exception.DukeException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.Todo;

/**
 * Represents an instance of the bot which communicates with the user.
 * A ChatBot object contains methods for the various functionalities of the bot
 * such as Add Task, View Tasks, Update Task, Find Tasks and Delete Task.
 */
public class ChatBot {
    private TaskList listOfTasks;
    private Ui ui;

    private void setListOfTasks(TaskList listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    private void setUi(Ui ui) {
        this.ui = ui;
    }

    public ChatBot(Ui ui, TaskList listOfTasks) {
        setUi(ui);
        ui.showHorizontalLine();
        ui.showWelcomeMessage();
        ui.showHorizontalLine();
        setListOfTasks(listOfTasks);
    }

    /**
     * Returns true if the Task List has been updated due to the command executed.
     * This ensures that the updated Task List would be written to the data file.
     * This method takes in a Command Object and performs the relevant actions based on the command.
     *
     * @param inputCommand The Command object that was created from the user input.
     * @return true if the task list has been updated, false if it hasn't been updated.
     */
    public boolean executeCommand(Command inputCommand) {
        if (inputCommand.getType() == Command.CommandType.AddTaskCommand) {
            AddTaskCommand newAddCommand = (AddTaskCommand) inputCommand;
            addTaskToList(newAddCommand);
        } else if (inputCommand.getType() == Command.CommandType.UpdateTaskStatusCommand) {
            UpdateTaskStatusCommand newUpdateCommand = (UpdateTaskStatusCommand) inputCommand;
            updateTaskStatusInList(newUpdateCommand);
        } else if (inputCommand.getType() == Command.CommandType.PrintListCommand) {
            boolean isFindCommand = false;
            ui.printList(listOfTasks, isFindCommand);
            return false;
        } else if (inputCommand.getType() == Command.CommandType.DeleteTaskCommand) {
            DeleteTaskCommand newDeleteCommand = (DeleteTaskCommand) inputCommand;
            deleteTask(newDeleteCommand.getTaskIndex());
        } else if (inputCommand.getType() == Command.CommandType.FindTaskCommand) {
            boolean isFindCommand = true;
            FindTaskCommand newFindCommand = (FindTaskCommand) inputCommand;
            TaskList matchingTaskList = findTasksInList(newFindCommand);
            ui.printList(matchingTaskList, isFindCommand);
            return false;
        }
        return true;
    }

    /**
     * This method takes in the index of the task that the user wants to delete within the task list
     * and deletes that task from the task list.
     * Once the task has been deleted, an acknowledgement message would be shown
     * informing the user of the task that was deleted and the number of tasks remaining in the task list
     *
     * @param taskIndex The index within the task list of the task to delete.
     */
    private void deleteTask(int taskIndex) {
        String acknowledgementMessage = "\t Noted. I've removed this task:\n\t   ";
        if (taskIndex >= listOfTasks.getListSize() || taskIndex < 0) {
            ui.showIndexOutOfBoundError();
            return;
        }
        acknowledgementMessage = listOfTasks.removeTask(taskIndex);
        acknowledgementMessage = acknowledgementMessage + String.format("\n\t Now you have %d tasks in the list.",
                listOfTasks.getListSize());
        ui.showAcknowledgementMessage(acknowledgementMessage);
    }

    private void addTaskToList(AddTaskCommand inputCommand) {
        String acknowledgementMessage = "";
        String taskName = inputCommand.getTaskName();
        Task freshTask;
        if (inputCommand.getTaskType() == TaskType.Deadline) {
            String by = inputCommand.getTaskRequirement();
            freshTask = new Deadline(taskName, by);
        } else if (inputCommand.getTaskType() == TaskType.Event) {
            String time;
            time = inputCommand.getTaskRequirement();
            freshTask = new Event(taskName, time);
        } else {
            freshTask = new Todo(taskName);
        }
        listOfTasks.addTask(freshTask);
        acknowledgementMessage = freshTask.addTaskMessage();
        acknowledgementMessage = acknowledgementMessage + String.format("\n\t Now you have %d tasks in the list.",
                listOfTasks.getListSize());
        ui.showAcknowledgementMessage(acknowledgementMessage);
    }

    /**
     * This method takes in a UpdateTaskStatusCommand object which contains the index of the task
     * the user wants to update within the task list as well as the updated status of the task.
     * The status of a task is whether the task has been done.
     * Once the task has been updated, an acknowledgement message would be shown
     * informing the user of the task that was updated as well as its new status.
     *
     * @param newUpdateCommand The UpdateTaskStatusCommand object which contains the index of the task to update
     *                         as well as the task's new status.
     */
    private void updateTaskStatusInList(UpdateTaskStatusCommand newUpdateCommand) {
        boolean isTaskDone = newUpdateCommand.isTaskDone();
        int taskIndex = newUpdateCommand.getTaskIndex();
        try {
            String acknowledgementMessage = listOfTasks.updateTask(taskIndex, isTaskDone);
            ui.showAcknowledgementMessage(acknowledgementMessage);
        } catch (DukeException de) {
            ui.showIndexOutOfBoundError();
        }
    }

    /**
     * Returns a task list containing all the tasks that matches the keyword the user is searching for.
     * This method takes in the FindTaskCommand object and finds all the matching task within the task lists
     * using the keyword in the FindTaskCommand object and returns a task list of matching tasks.
     *
     * @param newFindTaskCommand The FindTaskCommand object which contains the keyword
     *                           that the user wants to use to search for tasks within the task list.
     * @return A task list containing all the matching tasks.
     */
    private TaskList findTasksInList(FindTaskCommand newFindTaskCommand) {
        TaskList listOfMatchingTasks;
        String keyWord = newFindTaskCommand.getKeyWord();
        listOfMatchingTasks = listOfTasks.findTasks(keyWord);
        return listOfMatchingTasks;
    }

}
