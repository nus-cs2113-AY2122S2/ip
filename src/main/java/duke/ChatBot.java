package duke;

import duke.command.AddTaskCommand;
import duke.command.DeleteTaskCommand;
import duke.command.Command;
import duke.command.UpdateTaskStatusCommand;
import duke.exception.DukeException;
import duke.exception.DukeExceptionCause;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.ToDo;

import java.util.ArrayList;

public class ChatBot {
    private final String BOT_NAME = "Big Bob";
    private ArrayList<Task> listOfTasks = new ArrayList<>();

    public ChatBot() {
        System.out.println("\t Greetings Human! I'm " + BOT_NAME + ".");
        System.out.println("\t How may i be of service to you?");
    }

    public void executeCommand(Command inputCommand) {
        if (inputCommand.getType() == Command.CommandType.ADDTASK) {
            AddTaskCommand newAddCommand = (AddTaskCommand) inputCommand;
            addTaskToList(newAddCommand);

        } else if (inputCommand.getType() == Command.CommandType.UPDATETASKSTATUS) {
            UpdateTaskStatusCommand newUpdateCommand = (UpdateTaskStatusCommand) inputCommand;
            updateTaskStatusInList(newUpdateCommand);
        } else if (inputCommand.getType() == Command.CommandType.PRINTLIST) {
            printList();
        } else if (inputCommand.getType() == Command.CommandType.DELETETASKS) {
            DeleteTaskCommand newDeleteCommand = (DeleteTaskCommand) inputCommand;
            deleteTask(newDeleteCommand.getTaskIndex());
        }
    }

    public void printList() {
        System.out.println("\t Here are the tasks in your list:");
        Task taskToPrint;
        for (int i = 0; i < listOfTasks.size(); i++) {
            int taskNumber = i + 1;
            taskToPrint = listOfTasks.get(i);
            System.out.println("\t " + taskNumber + "." + taskToPrint.printTaskDescription());
        }
    }

    public void deleteTask(int taskIndex) {
        String deleteMessage = "\t Noted. I've removed this task:\n\t   ";
        Task taskToDelete;
        try {
            taskToDelete = listOfTasks.get(taskIndex - 1);
        } catch (IndexOutOfBoundsException IE) {
            processExceptions(new DukeException(DukeExceptionCause.TASKINDEXOUTOFRANGE));
            return;
        }
        if (taskToDelete instanceof Events) {
            Events eventToDelete = (Events) taskToDelete;
            deleteMessage = deleteMessage + eventToDelete.printTaskDescription();
        } else if (taskToDelete instanceof Deadlines) {
            Deadlines deadlinesToDelete = (Deadlines) taskToDelete;
            deleteMessage = deleteMessage + deadlinesToDelete.printTaskDescription();
        } else if (taskToDelete instanceof ToDo) {
            ToDo toDoToDelete = (ToDo) taskToDelete;
            deleteMessage = deleteMessage + toDoToDelete.printTaskDescription();
        }
        listOfTasks.remove(taskIndex - 1);
        System.out.println(deleteMessage);
        System.out.println("\t Now you have " + listOfTasks.size() + " tasks in the list.");
    }

    public void echoInvalidCommandMessage() {
        System.out.println("\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void echoMissingTaskNameMessage(String typeOfTask) {
        System.out.println("\t ☹ OOPS!!! The description of a " + typeOfTask + " cannot be empty.");
    }

    public void echoMissingTaskIndexMessage() {
        System.out.println("\t ☹ OOPS!!! The index of a task cannot be empty.");
    }

    public void echoTaskIndexOutOfRangeMessage() {
        System.out.println("\t ☹ OOPS!!! You have entered an invalid index of a task.");
    }

    public void processExceptions(DukeException de) {
        DukeExceptionCause causeOfException = de.getExceptionCause();
        switch (causeOfException) {
        case INVALIDCOMMAND:
            echoInvalidCommandMessage();
            break;
        case TODOTASKNAMEEMPTY:
            echoMissingTaskNameMessage("todo");
            break;
        case EVENTTASKNAMEEMPTY:
            echoMissingTaskNameMessage("event");
            break;
        case DEADLINETASKNAMEEMPTY:
            echoMissingTaskNameMessage("deadline");
            break;
        case EMPTYTASKINDEX:
            echoMissingTaskIndexMessage();
        case TASKINDEXOUTOFRANGE:
            echoTaskIndexOutOfRangeMessage();
        default:
            break;
        }
    }

    public void addTaskToList(AddTaskCommand inputCommand) {
        String acknowledgementMessage = "\t Got it. I've added this task:\n";
        String taskName = inputCommand.getTaskName();
        Task freshTask;
        if (inputCommand.getTaskType() == TaskType.DEADLINE) {
            String by = inputCommand.getTaskRequirement();
            freshTask = new Deadlines(taskName, by);
            acknowledgementMessage = acknowledgementMessage + String.format("\t   [D][ ] %s (by: %s)", taskName, by);
        } else if (inputCommand.getTaskType() == TaskType.EVENT) {
            String time;
            time = inputCommand.getTaskRequirement();
            freshTask = new Events(taskName, time);
            acknowledgementMessage = acknowledgementMessage + String.format("\t   [E][ ] %s (at: %s)", taskName, time);
        } else {
            freshTask = new ToDo(taskName);
            acknowledgementMessage = acknowledgementMessage + String.format("\t   [T][ ] %s", taskName);
        }
        listOfTasks.add(freshTask);
        System.out.println(acknowledgementMessage);
        System.out.println("\t Now you have " + listOfTasks.size() + " tasks in the list.");
    }

    public void updateTaskStatusInList(UpdateTaskStatusCommand newUpdateCommand) {
        boolean isTaskDone = newUpdateCommand.isTaskDone();
        int taskIndex = newUpdateCommand.getTaskIndex();
        String acknowledgementMessage;
        Task taskToUpdate = listOfTasks.get(taskIndex);
        acknowledgementMessage = taskToUpdate.setDone(isTaskDone);
        System.out.println("\t   " + acknowledgementMessage);
    }

    public void echoFarewellGreeting() {
        System.out.println("    Good bye.See you soon :)) !");
    }
}
