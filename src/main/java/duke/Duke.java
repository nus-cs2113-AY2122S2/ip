package duke;

import duke.task.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Duke {

    public static final int TASK_LIST_LENGTH = 100;
    public static final int MINIMUM_LENGTH_OF_TODO_STATEMENT = 4;
    public static final int MINIMUM_LENGTH_OF_DEADLINE_STATEMENT = 8;
    public static final int MINIMUM_LENGTH_OF_EVENT_STATEMENT = 5;
    public static final int MINIMUM_LENGTH_OF_MARK_STATEMENT = 4;
    public static final int MINIMUM_LENGTH_OF_UNMARK_STATEMENT = 6;
    public static final int MINIMUM_LENGTH_OF_DELETE_STATEMENT = 6;
    public static final String DATAFILE_RELATIVE_PATH = "data\\duke.txt";
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final int INVALID_TASKNUMBER = -1;

    static Task[] taskList = new Task[TASK_LIST_LENGTH];
    static int taskCount = 0;
    static boolean isModified = false;      // isModified refers to whether taskList is modified or not.

    public static void displayLine() {
        System.out.println("____________________________________________________________");
    }

    public static void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("You are entering the\n" + logo + "\nZone...\n");

        displayLine();
        System.out.println("Hey there! Duke here!");
        System.out.println("How can I serve you today?");
        displayLine();
    }

    public static void goodbye() {
        System.out.println("Goodbye. See you in the funny papers.");
        displayLine();
    }

    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.print(String.format("%d. ", i + 1));
            System.out.println(taskList[i]);
        }
        displayLine();
    }

    public static boolean isDeadline(String userInput) {
        if (userInput.length() >= MINIMUM_LENGTH_OF_DEADLINE_STATEMENT) {
            // return true if first 8 letters of userInput spell "deadline", else false
            return userInput.substring(0, MINIMUM_LENGTH_OF_DEADLINE_STATEMENT).equals("deadline");
        }
        return false;
    }

    public static boolean isTodo(String userInput) {
        if (userInput.length() >= MINIMUM_LENGTH_OF_TODO_STATEMENT) {
            // return true if first 4 letters of userInput spell "todo", else false
            return userInput.substring(0, MINIMUM_LENGTH_OF_TODO_STATEMENT).equals("todo");
        }
        return false;
    }

    public static boolean isEvent(String userInput) {
        if (userInput.length() >= MINIMUM_LENGTH_OF_EVENT_STATEMENT) {
            // return true if first 5 letters of userInput spell "event", else false
            return userInput.substring(0, MINIMUM_LENGTH_OF_EVENT_STATEMENT).equals("event");
        }
        return false;
    }

    public static String getTodoFromUserInput(String userInput) {
        return userInput.substring(MINIMUM_LENGTH_OF_TODO_STATEMENT + 1, userInput.length());
    }

    public static String getEventFromUserInput(String userInput, int eventTimeIdx) {
        return userInput.substring(MINIMUM_LENGTH_OF_EVENT_STATEMENT + 1, eventTimeIdx - 1);
    }

    public static String getDeadlineFromUserInput(String userInput, int dueDateIdx) {
        return userInput.substring(MINIMUM_LENGTH_OF_DEADLINE_STATEMENT + 1, dueDateIdx - 1);
    }

    public static String getEventTimeFromUserInput(String userInput, int eventTimeIdx) {
        // ignore /by in the userInput
        return userInput.substring(eventTimeIdx + MINIMUM_LENGTH_OF_TODO_STATEMENT, userInput.length());
    }

    public static String getDueDateFromUserInput(String userInput, int dueDateIdx) {
        // ignore /at in the userInput
        return userInput.substring(dueDateIdx + MINIMUM_LENGTH_OF_TODO_STATEMENT, userInput.length());
    }

    private static void printAddedTask() {
        System.out.println("Noted. I've added:");
        System.out.println(taskList[taskCount - 1]);
        System.out.println("Now you have " + Integer.toString(taskCount) + " tasks in the list.");
        displayLine();
    }

    private static void printDeletedTask(int taskNumber) {
        System.out.println("Noted. I've removed:");
        System.out.println(taskList[taskNumber - 1]);
        System.out.println("Now you have " + Integer.toString(taskCount - 1) + " tasks in the list.");
        displayLine();
    }

    public static void addTodoToList(String todoDescription) {
        Todo newTodo = new Todo(todoDescription);
        //add the new object to taskList.
        taskList[taskCount] = newTodo;
        //keep track of the number of tasks in taskList.
        taskCount++;

        printAddedTask();
    }

    // overloaded function called when loading data.
    public static void addTodoToList(String todoDescription, boolean isUserMode) {
        Todo newTodo = new Todo(todoDescription);
        //add the new object to taskList.
        taskList[taskCount] = newTodo;
        //keep track of the number of tasks in taskList.
        taskCount++;
    }

    public static void addEventToList(String eventDescription, String eventTime) {
        Event newEvent = new Event(eventDescription, eventTime);
        // add the new object to taskList.
        taskList[taskCount] = newEvent;
        // keep track of the number of tasks in taskList.
        taskCount++;

        printAddedTask();
    }

    // overloaded function called when loading data.
    public static void addEventToList(String eventDescription, String eventTime, boolean isUserMode) {
        Event newEvent = new Event(eventDescription, eventTime);
        // add the new object to taskList.
        taskList[taskCount] = newEvent;
        // keep track of the number of tasks in taskList.
        taskCount++;
    }

    public static void addDeadlineToList(String deadlineDescription, String dueDate) {
        Deadline newDeadline = new Deadline(deadlineDescription, dueDate);
        // add the new object to taskList.
        taskList[taskCount] = newDeadline;
        // keep track of the number of tasks in taskList.
        taskCount++;

        printAddedTask();
    }

    // overloaded function called when loading data.
    public static void addDeadlineToList(String deadlineDescription, String dueDate, boolean isUserMode) {
        Deadline newDeadline = new Deadline(deadlineDescription, dueDate);
        // add the new object to taskList.
        taskList[taskCount] = newDeadline;
        // keep track of the number of tasks in taskList.
        taskCount++;
    }

    public static boolean isInvalidTodo(String todo) {
        int lengthOfTodoDescription = todo.length() - MINIMUM_LENGTH_OF_TODO_STATEMENT - 1;
        return (lengthOfTodoDescription <= 0);
    }

    public static boolean isInvalidEvent(String event) {
        int lengthOfEventDescription = event.length() - MINIMUM_LENGTH_OF_EVENT_STATEMENT - 1;
        if (lengthOfEventDescription <= 0) {
            return true;
        }
        // invalid format
        if (event.indexOf("/") == -1) {
            return true;
        }

        // get string components after "/" in the event input.
        String[] eventTimeSplit = event.substring(event.indexOf("/"), event.length()).split(" ");

        // invalid format or event time is not specified.
        if (eventTimeSplit.length < 2 || !(eventTimeSplit[0].equals("/at"))) {
            return true;
        }
        return false;
    }

    public static boolean isInvalidDeadline(String deadline) {
        int lengthOfDeadlineDescription = deadline.length() - MINIMUM_LENGTH_OF_DEADLINE_STATEMENT - 1;
        if (lengthOfDeadlineDescription <= 0) {
            return true;
        }
        // invalid format
        if (deadline.indexOf("/") == -1) {
            return true;
        }

        // get string components after "/" in the deadline input.
        String[] dueDateSplit = deadline.substring(deadline.indexOf("/"), deadline.length()).split(" ");

        // invalid format or due date is not specified.
        if (dueDateSplit.length < 2 || !(dueDateSplit[0].equals("/by"))) {
            return true;
        }
        return false;
    }

    // method adds task to task_list.
    public static void addTaskToList(String userInput) {
        if (isTodo(userInput)) {
            if (isInvalidTodo(userInput)) {
                System.out.println("OOPS! The description for todo cannot be empty.");
                displayLine();
                return;
            }

            String todoDescription = getTodoFromUserInput(userInput);

            // add Todo task to List
            addTodoToList(todoDescription);
        } else if (isEvent(userInput)) {
            if (isInvalidEvent(userInput)) {
                System.out.println("OOPS! Either the description or event time or both are empty for this event. Please try again.");
                displayLine();
                return;
            }

            int eventTimeIdx = userInput.indexOf("/");
            String eventDescription = getEventFromUserInput(userInput, eventTimeIdx);
            String eventTime = getEventTimeFromUserInput(userInput, eventTimeIdx);

            // add Event task to List
            addEventToList(eventDescription, eventTime);
        } else if (isDeadline(userInput)) {
            if (isInvalidDeadline(userInput)) {
                System.out.println("OOPS! Either the description or due date or both are empty for this deadline. Please try again.");
                displayLine();
                return;
            }

            int dueDateIdx = userInput.indexOf("/");
            String deadlineDescription = getDeadlineFromUserInput(userInput, dueDateIdx);
            String dueDate = getDueDateFromUserInput(userInput, dueDateIdx);

            // add Deadline task to List
            addDeadlineToList(deadlineDescription, dueDate);
        }

        // If not a recognizable command, inform user
        else {
            System.out.println("OOPS! I'm sorry but I don't know what you mean :(");
            return;
        }
        isModified = true;
    }

    //method marks task in list with taskNumber as done.
    public static void markTaskAsDone(int taskNumber) {
        if (taskNumber == INVALID_TASKNUMBER) {
            System.out.println("Please provide a valid task number for me to mark as done.");
            return;
        }
        // mark task with taskNumber as done.
        taskList[taskNumber - 1].markAsDone();
        System.out.println("Great Job! I've marked the following task as done:");
        // display updated task entry in list.
        System.out.println(taskList[taskNumber - 1]);
        displayLine();
        isModified = true;
    }

    //method marks task in list with taskNumber as not yet done.
    public static void unmarkTaskAsDone(int taskNumber) {
        if (taskNumber == INVALID_TASKNUMBER) {
            System.out.println("Please provide a valid task number for me to mark as not yet done.");
            return;
        }
        // mark task with taskNumber as yet to be done.
        taskList[taskNumber - 1].unmarkAsDone();
        System.out.println("Ok, I've marked the following task as yet to be done:");
        // display updated task entry in list.
        System.out.println(taskList[taskNumber - 1]);
        displayLine();
        isModified = true;
    }

    public static void deleteTask(int taskNumber) {
        if (taskNumber == INVALID_TASKNUMBER) {
            System.out.println("Please provide a valid task number for me to delete.");
            return;
        }
        printDeletedTask(taskNumber);
        while (taskNumber < taskCount) {
            taskList[taskNumber - 1] = taskList[taskNumber];
            taskNumber++;
        }
        taskCount--;
        isModified = true;
    }

    public static boolean isMarkCommand(String userInput) {
        if (userInput.length() >= MINIMUM_LENGTH_OF_MARK_STATEMENT) {
            // return true if first 4 letters of userInput spell "mark", else false
            return userInput.substring(0, MINIMUM_LENGTH_OF_MARK_STATEMENT).equals("mark");
        }
        return false;
    }

    public static boolean isUnmarkCommand(String userInput) {
        if (userInput.length() >= MINIMUM_LENGTH_OF_UNMARK_STATEMENT) {
            // return true if first 6 letters of userInput spell "unmark", else false
            return userInput.substring(0, MINIMUM_LENGTH_OF_UNMARK_STATEMENT).equals("unmark");
        }
        return false;
    }

    public static boolean isListCommand(String userInput) {
        return userInput.equals("list");
    }

    public static boolean isDeleteCommand(String userInput) {
        // return true if command is list, else false
        if (userInput.length() >= MINIMUM_LENGTH_OF_DELETE_STATEMENT) {
            return userInput.substring(0, MINIMUM_LENGTH_OF_DELETE_STATEMENT).equals("delete");
        }
        return false;
    }

    public static boolean isByeCommand(String userInput) {
        return userInput.equals("bye");
    }

    // method returns task number else -1 if any errors encountered.
    public static int getTaskNumber(String userInput) {
        if (taskCount == 0) {
            return INVALID_TASKNUMBER;
        }
        String[] splitInput = userInput.split(" ");
        if (splitInput.length <= 1) {
            return INVALID_TASKNUMBER;
        }
        try {
            int taskNumber = Integer.parseInt(splitInput[1]);
            if (taskNumber>taskCount||taskNumber<1){
                return INVALID_TASKNUMBER;
            }
            return taskNumber;
        } catch (NumberFormatException e) {
            return INVALID_TASKNUMBER;
        }
    }

    public static String getUserInput() {
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        displayLine();
        return userInput;
    }

    public static void createDirectory() {
        String directory = "\\data";
        String directoryPath = PROJECT_PATH + directory;
        File dir = new File(directoryPath);
        boolean isMade = dir.mkdir();
        if (!isMade) {
            System.out.println("Error making directory \"data\".");
        }
    }

    public static void createFile() {
        File dir = new File("data");
        // attempt to create directory if it does not exist.
        while (!dir.exists()) {
            createDirectory();
        }
        File f = new File(PROJECT_PATH + "\\" + DATAFILE_RELATIVE_PATH);

        try {
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred making file.");
        }
    }

    public static String getTaskInFileFormat(int listIndex) {
        Task task = taskList[listIndex];
        if (task.getTaskTypeSymbol() == "T") {
            return task.getTaskTypeSymbol() + "/" + task.isDone() + "/" + task.getDescription();
        }
        DynamicTask dTask = (DynamicTask) task;
        return dTask.getTaskTypeSymbol() + "/" + dTask.isDone() + "/" + dTask.getDescription()
                + "/" + dTask.getTime();
    }

    public static void writeToFile() throws IOException {
        FileWriter fWrite = new FileWriter(DATAFILE_RELATIVE_PATH);
        if (taskCount == 0) {
            fWrite.write("");
            fWrite.close();
        } else {
            fWrite.write(getTaskInFileFormat(0) + "\n");
            fWrite.close();
        }
        fWrite = new FileWriter(DATAFILE_RELATIVE_PATH, true);
        for (int i = 1; i < taskCount; i++) {
            fWrite.write(getTaskInFileFormat(i) + "\n");
        }
        fWrite.close();
    }

    public static void saveData() {
        File f = new File(DATAFILE_RELATIVE_PATH);
        // attempt to create file if it does not exist.
        while (!f.exists()) {
            createFile();
        }
        try {
            writeToFile();
        } catch (IOException e) {
            System.out.println("Something went wrong writing to file.");
        }
    }


    public static void transferDataFromFileToList() throws FileNotFoundException {
        File f = new File(DATAFILE_RELATIVE_PATH);
        Scanner fileReader = new Scanner(f);
        while (fileReader.hasNext()) {
            String fileRow = fileReader.nextLine();
            String[] taskArguments = fileRow.split("/", 5);
            if (taskArguments[0].equals("T")) {
                addTodoToList(taskArguments[2], false);
            } else if (taskArguments[0].equals("D")) {
                addDeadlineToList(taskArguments[2], taskArguments[3], false);
            } else if (taskArguments[0].equals("E")) {
                addEventToList(taskArguments[2], taskArguments[3], false);
            } else {
                System.out.println("Corrupted entry detected in file.");
                continue;
            }

            // if task being loaded is done, mark as done.
            if (taskArguments[1].equals("true")) {
                taskList[taskCount - 1].markAsDone();
            }
        }
        System.out.println("Now you have " + Integer.toString(taskCount) + " tasks in the list.");
        displayLine();
    }

    // load data if data file exists, else create file.
    public static void loadData() {
        File f = new File(DATAFILE_RELATIVE_PATH);
        try {
            transferDataFromFileToList();
        } catch (FileNotFoundException e) {
            // attempt to create file till it is created.
            while (!f.exists()) {
                createFile();
            }
        }
    }

    // method runs main echo functionality of duke.
    public static void echo() {
        while (true) {
            isModified = false;

            //read input from user.
            String userInput = getUserInput();

            // Check userInput for respective command.

            if (isByeCommand(userInput)) {
                return;
            } else if (isListCommand(userInput)) {
                printList();
            } else if (isMarkCommand(userInput)) {
                int taskNumber = getTaskNumber(userInput);
                markTaskAsDone(taskNumber);
            } else if (isUnmarkCommand(userInput)) {
                int taskNumber = getTaskNumber(userInput);
                unmarkTaskAsDone(taskNumber);
            } else if (isDeleteCommand(userInput)) {
                int taskNumber = getTaskNumber(userInput);
                deleteTask(taskNumber);
            }
            // else it is an addition command or some unknown command
            else {
                addTaskToList(userInput);
            }

            if (isModified) {
                saveData();
            }
        }
    }

    public static void main(String[] args) {
        // opening sequence.
        greeting();

        // if data exists, load.
        loadData();

        // echo loop between user and Dukebot.
        echo();

        // ending sequence.
        goodbye();
    }
}
