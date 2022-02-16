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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ChatBot {
    private final String BOT_NAME = "Big Bob";
    private final String FILE_PATH = "data/duke.txt";
    private ArrayList<Task> listOfTasks = new ArrayList<>();

    public ChatBot() throws IOException {
        System.out.println("\t Greetings Human! I'm " + BOT_NAME + ".");
        System.out.println("\t How may i be of service to you?");
        loadDataFromFile();
    }

    public void createFile(File f) throws IOException {
        File directory = f.getParentFile();
        boolean isDirectoryCreated;
        if (!directory.exists()) {
            isDirectoryCreated = directory.mkdirs();
            if (!isDirectoryCreated) {
                throw new IOException("Folder is unable to be created");
            }
        }
        try {
            f.createNewFile();
        } catch (IOException io) {
            throw io;
        }
    }

    public Events extractEventFromFile(StringTokenizer st) {
        Events newEventTask;
        boolean isDone;
        String taskName;
        String time;
        //0 means unmarked, 1 means marked.
        if (st.nextToken().equals("1")) {
            isDone = true;
        } else {
            isDone = false;
        }
        taskName = st.nextToken();
        time = st.nextToken();
        newEventTask = new Events(taskName, time);
        newEventTask.setDone(isDone);
        return newEventTask;
    }

    public ToDo extractToDoFromFile(StringTokenizer st) {
        ToDo newToDoTask;
        boolean isDone;
        String taskName;
        if (st.nextToken().equals("1")) {
            isDone = true;
        } else {
            isDone = false;
        }
        taskName = st.nextToken();
        newToDoTask = new ToDo(taskName);
        newToDoTask.setDone(isDone);
        return newToDoTask;
    }

    public Deadlines extractDeadlineFromFile(StringTokenizer st) {
        Deadlines newDeadlineTask;
        boolean isDone;
        String taskName;
        String by;
        if (st.nextToken().equals("1")) {
            isDone = true;
        } else {
            isDone = false;
        }
        taskName = st.nextToken();
        by = st.nextToken();
        newDeadlineTask = new Deadlines(taskName, by);
        newDeadlineTask.setDone(isDone);
        return newDeadlineTask;
    }

    public void loadDataFromFile() throws IOException {
        File f = new File(FILE_PATH);
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String fileData = s.nextLine();
                StringTokenizer st = new StringTokenizer(fileData, "|");
                String taskType = st.nextToken();
                Task newTask;
                switch (taskType) {
                case "E":
                    newTask = extractEventFromFile(st);
                    break;
                case "T":
                    newTask = extractToDoFromFile(st);
                    break;
                case "D":
                    newTask = extractDeadlineFromFile(st);
                    break;
                default:
                    System.out.println("\t Invalid Task Type found within the input file, skipping the invalid Task Type.");
                    continue;
                }
                listOfTasks.add(newTask);
            }
        } catch (FileNotFoundException fe) {
            try {
                createFile(f);
            } catch (IOException IE) {
                throw IE;
            }
        }
    }

    public void clearFileContents() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.close();
    }

    public void writeArrayListToFile() throws IOException {
        clearFileContents();
        FileWriter fw = new FileWriter(FILE_PATH, true);
        String taskDetails;
        for (int i = 0; i < listOfTasks.size(); i++) {
            Character isDoneSymbol;
            if (listOfTasks.get(i).getDone()) {
                isDoneSymbol = '1';
            } else {
                isDoneSymbol = '0';
            }
            if (listOfTasks.get(i) instanceof Events) {
                Events event = (Events) listOfTasks.get(i);
                taskDetails = "E|" + isDoneSymbol + "|" + event.getTaskName() + "|" + event.getTime();
            } else if (listOfTasks.get(i) instanceof Deadlines) {
                Deadlines deadline = (Deadlines) listOfTasks.get(i);
                taskDetails = "D|" + isDoneSymbol + "|" + deadline.getTaskName() + "|" + deadline.getBy();
            } else if (listOfTasks.get(i) instanceof ToDo) {
                ToDo todoTask = (ToDo) listOfTasks.get(i);
                taskDetails = "T|" + isDoneSymbol + "|" + todoTask.getTaskName();
            } else {
                System.out.println("Invalid Task Type");
                continue;
            }
            if(i != listOfTasks.size() -1) {
                fw.write(taskDetails + System.lineSeparator());
            }else{
                fw.write(taskDetails);
            }
        }
        fw.close();
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
        try {
            writeArrayListToFile();
        } catch (IOException ie) {
            System.out.println("Something went wrong when writing the list of tasks to file" + ie.getMessage());
        }
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
        try {
            writeArrayListToFile();
        } catch (IOException ie) {
            System.out.println("Something went wrong when writing the list of tasks to file" + ie.getMessage());
        }
    }

    public void updateTaskStatusInList(UpdateTaskStatusCommand newUpdateCommand) {
        boolean isTaskDone = newUpdateCommand.isTaskDone();
        int taskIndex = newUpdateCommand.getTaskIndex();
        String acknowledgementMessage;
        Task taskToUpdate = listOfTasks.get(taskIndex);
        if (isTaskDone == true) {
            System.out.println("\t Nice! I've marked this task as done:");
        } else {
            System.out.println("\t OK, I've marked this task as not done yet:");
        }
        acknowledgementMessage = taskToUpdate.setDone(isTaskDone);
        System.out.println("\t   " + acknowledgementMessage);
        try {
            writeArrayListToFile();
        } catch (IOException ie) {
            System.out.println("Something went wrong when writing the list of tasks to file" + ie.getMessage());
        }
    }

    public void echoFarewellGreeting() {
        System.out.println("    Good bye.See you soon :)) !");
    }
}
