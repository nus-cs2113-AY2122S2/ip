public class ChatBot {
    private final String BOT_NAME = "Big Bob";
    private final int MAXIMUM_NUMBER_OF_TASK = 100;
    private Task[] listOfTasks = new Task[MAXIMUM_NUMBER_OF_TASK];
    private int numOfTaskInList = 0;

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
        }
    }

    public void printList() {
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < numOfTaskInList; i++) {
            int taskNumber = i + 1;
            System.out.println("\t " + taskNumber + "." + listOfTasks[i].printTaskDescription());
        }
    }

    public void echoInvalidCommandMessage() {
        System.out.println("\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void echoMissingTaskNameMessage(String typeOfTask) {
        System.out.println("\t ☹ OOPS!!! The description of a " + typeOfTask + " cannot be empty.");
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
        listOfTasks[numOfTaskInList] = freshTask;
        numOfTaskInList++;
        System.out.println(acknowledgementMessage);
        System.out.println("\t Now you have " + numOfTaskInList + " tasks in the list.");
    }

    public void updateTaskStatusInList(UpdateTaskStatusCommand newUpdateCommand) {
        boolean isTaskDone = newUpdateCommand.isTaskDone();
        int taskIndex = newUpdateCommand.getTaskIndex();
        String acknowledgementMessage;
        acknowledgementMessage = listOfTasks[taskIndex].setDone(isTaskDone);
        System.out.println("\t   " + acknowledgementMessage);
    }

    public void echoFarewellGreeting() {
        System.out.println("    Good bye.See you soon :)) !");
    }
}
