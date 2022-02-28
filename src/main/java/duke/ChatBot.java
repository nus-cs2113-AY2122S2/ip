package duke;

import duke.command.FindTaskCommand;
import duke.command.UpdateTaskStatusCommand;
import duke.command.AddTaskCommand;
import duke.command.DeleteTaskCommand;
import duke.command.Command;

import duke.exception.DukeException;
import duke.exception.DukeExceptionCause;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.ToDo;

public class ChatBot {
    private final String BOT_NAME = "Big Bob";
    private final String FILE_PATH = "data/duke.txt";
    private TaskList listOfTasks;

    private Ui ui;

    public ChatBot(Ui ui, TaskList listOfTasks) {
        setUi(ui);
        ui.showWelcomeMessage();
        setListOfTasks(listOfTasks);
    }

    public void setListOfTasks(TaskList listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public void setUi(Ui ui) {
        this.ui = ui;
    }

    public boolean executeCommand(Command inputCommand) {
        try {
            if (inputCommand.getType() == Command.CommandType.ADDTASK) {
                AddTaskCommand newAddCommand = (AddTaskCommand) inputCommand;
                addTaskToList(newAddCommand);
            } else if (inputCommand.getType() == Command.CommandType.UPDATETASKSTATUS) {
                UpdateTaskStatusCommand newUpdateCommand = (UpdateTaskStatusCommand) inputCommand;
                updateTaskStatusInList(newUpdateCommand);
            } else if (inputCommand.getType() == Command.CommandType.PRINTLIST) {
                ui.printList(listOfTasks,false);
                return false;
            } else if (inputCommand.getType() == Command.CommandType.DELETETASKS) {
                DeleteTaskCommand newDeleteCommand = (DeleteTaskCommand) inputCommand;
                deleteTask(newDeleteCommand.getTaskIndex());
            }else if(inputCommand.getType() == Command.CommandType.FINDTASKS){
                FindTaskCommand newFindCommand = (FindTaskCommand) inputCommand;
                TaskList matchingTaskList= findTaskInList(newFindCommand.getKeyWord());
                ui.printList(matchingTaskList,true);
            }
        }catch(DukeException de){
            ui.showIndexOutOfBoundError();
        }
        return true;
    }



    public void deleteTask(int taskIndex) throws DukeException{
        String acknowledgementMessage = "\t Noted. I've removed this task:\n\t   ";
        Task taskToDelete;
        try {
            taskToDelete = listOfTasks.getTask(taskIndex - 1);
        } catch (IndexOutOfBoundsException IE) {
            throw new DukeException(DukeExceptionCause.TASKINDEXOUTOFRANGE);
        }
        if (taskToDelete instanceof Events) {
            Events eventToDelete = (Events) taskToDelete;
        } else if (taskToDelete instanceof Deadlines) {
            Deadlines deadlinesToDelete = (Deadlines) taskToDelete;
        } else if (taskToDelete instanceof ToDo) {
            ToDo toDoToDelete = (ToDo) taskToDelete;
        }
        acknowledgementMessage = listOfTasks.removeTask(taskIndex - 1);
        acknowledgementMessage = acknowledgementMessage + String.format("\n\t Now you have %d tasks in the list.",listOfTasks.getListSize());
        ui.showAcknowledgementMessage(acknowledgementMessage);
    }


    public void addTaskToList(AddTaskCommand inputCommand) {
        String acknowledgementMessage = "";
        String taskName = inputCommand.getTaskName();
        Task freshTask;
        if (inputCommand.getTaskType() == TaskType.DEADLINE) {
            String by = inputCommand.getTaskRequirement();
            freshTask = new Deadlines(taskName, by);
        } else if (inputCommand.getTaskType() == TaskType.EVENT) {
            String time;
            time = inputCommand.getTaskRequirement();
            freshTask = new Events(taskName, time);
        } else {
            freshTask = new ToDo(taskName);
        }
        listOfTasks.addTask(freshTask);
        acknowledgementMessage = freshTask.addTaskMessage();
        acknowledgementMessage =  acknowledgementMessage + String.format("\n\t Now you have %d tasks in the list.",listOfTasks.getListSize());
        ui.showAcknowledgementMessage(acknowledgementMessage);
    }

    public void updateTaskStatusInList(UpdateTaskStatusCommand newUpdateCommand) {
        boolean isTaskDone = newUpdateCommand.isTaskDone();
        int taskIndex = newUpdateCommand.getTaskIndex();
        try {
            String acknowledgementMessage = listOfTasks.updateTask(taskIndex, isTaskDone);
            ui.showAcknowledgementMessage(acknowledgementMessage);
        }catch(DukeException de) {
            ui.showIndexOutOfBoundError();
        }
    }

    public TaskList findTaskInList(String keyWord){
        TaskList listOfMatchingTask;
        listOfMatchingTask = listOfTasks.findTasks(keyWord);
        return listOfMatchingTask;
    }

}
