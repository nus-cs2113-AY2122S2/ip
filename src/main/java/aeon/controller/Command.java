package aeon.controller;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import aeon.exception.AeonException;
import aeon.task.Task;
import aeon.task.Event;
import aeon.task.Todo;
import aeon.task.Deadline;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 * The core function of parsing and executing the user's commands into Aeon, and automatically
 * saving/loading all tasks to a text file on disk
 */
public class Command {

    public static final String TASK_NOT_FOUND =
            "Task not found! Perhaps try listing out the available tasks first...";
    public static final String INVALID_INTEGER_MSG = "Please type in a valid integer!";
    public static final String TODO_DESC_ERROR = "Description of TODO cannot be empty!!!";
    public static final String DEADLINE_FORMAT_ERR =
            "Please try again in this format: deadline <description> /by <date>";
    public static final String EVENT_FORMAT_ERR =
            "Please try again in this format: event <description> /at <date>";
    public static final String INVALID_COMMAND = "Not sure what you were trying to do...";
    public static final String TASK_ADDED = "Task added!";
    public static final String CONGRATULATIONS_MSG = "Congrats on completing this task!";
    public static final String MARK_UNDONE = "Alright, marked as undone!";
    public static final String NO_TASKS = "No tasks!";
    public static final String FILE_PATH = "./data/tasklist.txt";
    public static final String DIR_PATH = "./data/";
    public static final String CREATE_FILE_FAILED = "Failed to create file to store task!";
    public static final int COMMAND_WORD = 0;
    public static final String TASK_LIST = "list";
    public static final String TASK_UNMARK = "unmark";
    public static final String TASK_MARK = "mark";
    public static final String TASK_TODO = "todo";
    public static final String TASK_DEADLINE = "deadline";
    public static final String TASK_EVENT = "event";
    public static final String TASK_DELETE = "delete";
    public static final String TASK_FIND = "find";
    public static final int TASK_TYPE = 0;
    public static final String TASKTYPE_TODO = "T";
    public static final String TASKTYPE_DEADLINE = "D";
    public static final String TASKTYPE_EVENT = "E";
    public static final String USER_BYE = "bye";
    public static final String TASK_MARKED = "X";
    public static final int TARGET_WORD = 1;
    public static final String EMPTY_KEYWORD_MSG = "Keyword to look for cannot be empty!!!";
    public static final String TASK_DETAILS_MISSING_MSG = "Details of task not complete!!!";
    public static final String TEXT_FILE_INCORRECT_CONTENTS =
            "Some content(s) of text file do not match the correct format! Ignoring faulty commands...";
    public static final String UNKNOWN_COMMAND_TEXT_FILE =
            "Unknown command found in text file! Ignoring and moving on...";
    private static final ArrayList<String> rawDescriptions = new ArrayList<>();
    public static final String TASK_DELETED = "Task deleted!";

    /**
     * Processes each command input by the user and executes the respective methods depending
     * on the task specified
     */
    public static void CommandProcessor() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            readSavedTaskList(list);
        } catch (AeonException e) {
            System.out.println(TEXT_FILE_INCORRECT_CONTENTS);
        }
        Scanner in = new Scanner(System.in);
        String response = in.nextLine();
        while (!response.equals(USER_BYE)) {
            String[] words = response.split(" ", 2);
            executeCommand(list, words);
            response = in.nextLine();
        }
    }

    /**
     * Determines which method to execute based on the user's input, and executes it
     * @param list list of tasks entered by the user
     * @param words command itself
     */
    private static void executeCommand(ArrayList<Task> list, String[] words) {
        switch (words[COMMAND_WORD]) {
        case TASK_LIST:
            printListOfTasks(list);
            break;
        case TASK_UNMARK:
            executeUnmark(list, words);
            break;
        case TASK_MARK:
            executeMark(list, words);
            break;
        case TASK_TODO:
            executeTodo(list, words);
            break;
        case TASK_DEADLINE:
            executeDeadline(list, words);
            break;
        case TASK_EVENT:
            executeEvent(list, words);
            break;
        case TASK_DELETE:
            executeDelete(list, words);
            break;
        case TASK_FIND:
            //String target = words[TARGET_WORD];
            executeFind(list, words);
            break;
        default:
            System.out.println(INVALID_COMMAND);
            break;
        }
    }



    /**
     * Inserts a new event into the list of tasks
     * @param list list of tasks entered by the user
     * @param words command itself
     */
    private static void executeEvent(ArrayList<Task> list, String[] words) {
        try {
            addEventTask(list, words);
            System.out.println(TASK_ADDED);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(EVENT_FORMAT_ERR);
        } catch (AeonException e) {
            System.out.println(TASK_DETAILS_MISSING_MSG);
        }
    }

    /**
     * Inserts a new deadline into the list of tasks
     * @param list list of tasks entered by the user
     * @param words command itself
     */
    private static void executeDeadline(ArrayList<Task> list, String[] words) {
        try {
            addDeadlineTask(list, words);
            System.out.println(TASK_ADDED);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(DEADLINE_FORMAT_ERR);
        } catch (AeonException e) {
            System.out.println(TASK_DETAILS_MISSING_MSG);
        }
    }

    /**
     * Inserts a new 'To-Do' task into the list of tasks
     * @param list list of tasks entered by the user
     * @param words command itself
     */
    private static void executeTodo(ArrayList<Task> list, String[] words) {
        try {
            addTodoTask(list, words);
            System.out.println(TASK_ADDED);
        } catch (IndexOutOfBoundsException | AeonException e) {
            System.out.println(TASK_DETAILS_MISSING_MSG);
        }
    }

    /**
     * Marks a task as Done based on its index.
     * Users may only mark tasks that are currently existing in the list
     * @param list list of tasks entered by the user
     * @param words command itself
     */
    private static void executeMark(ArrayList<Task> list, String[] words) {
        try {
            markTask(list, words);
            System.out.println(CONGRATULATIONS_MSG);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(TASK_NOT_FOUND);
        } catch (NumberFormatException e) {
            System.out.println(INVALID_INTEGER_MSG);
        }
    }

    /**
     * Marks a task as Not Done based on its index.
     * Users may only unmark tasks that are currently existing in the list
     * @param list list of tasks entered by the user
     * @param words command itself
     */
    private static void executeUnmark(ArrayList<Task> list, String[] words) {
        try {
            unmarkTask(list, words);
            System.out.println(MARK_UNDONE);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(TASK_NOT_FOUND);
        } catch (NumberFormatException e) {
            System.out.println(INVALID_INTEGER_MSG);
        }
    }

    /**
     * Tries to look for all existing tasks that contain a specified keyword.
     * @param list list of tasks entered by the user
     * @param words command itself
     */
    private static void executeFind(ArrayList<Task> list, String[] words) {
        try {
            lookForTasks(list, words);
        } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(EMPTY_KEYWORD_MSG);
            }
        }

    /**
     * Performs the deletion of a task based on its index
     * @param list list of tasks entered by the user
     * @param words command itself
     */
    private static void executeDelete(ArrayList<Task> list, String[] words) {
        try {
            deleteTask(list, words);
            System.out.println(TASK_DELETED);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(TASK_NOT_FOUND);
        } catch (NumberFormatException e) {
            System.out.println(INVALID_INTEGER_MSG);
        }
    }

    /**
     * Looks for all existing tasks that contain a specific keyword.
     * @param list list of tasks entered by the user
     * @param words command itself
     */
    private static void lookForTasks(ArrayList<Task> list, String[] words) {
        String target = words[TARGET_WORD];
        boolean isFound = false;
        for (Task task : list) {
            if (task.getDescription().contains(target)) {
                isFound = true;
                System.out.println(task);
            }
        }
        if (!isFound) {
            System.out.println("No tasks found!");
        }
    }

    /**
     * Loads an existing list of tasks from a previously saved text file
     * and stores it in the list of tasks
     * @param list list of tasks to store the tasks read from the text file
     */
    private static void readSavedTaskList(ArrayList<Task> list) throws AeonException {
        File FILE = new File(FILE_PATH);
        File DIRECTORY = new File(DIR_PATH);
        checkDirExists(DIRECTORY);
        checkFileExists(FILE);
        setReadAndWritePermissions(FILE, DIRECTORY);
        Scanner fileScanner = null;
        fileScanner = openTaskFile(FILE, fileScanner);
        while (fileScanner.hasNext()) {
            String taskInFile = fileScanner.nextLine();
            String[] taskInFileArray = taskInFile.split(" ", 2);
            if (checkDetails(taskInFileArray)) {
                throw new AeonException();
            }
            try {
                parseSavedTaskList(list, taskInFileArray);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(TEXT_FILE_INCORRECT_CONTENTS);
            }
        }
    }

    /**
     * Ensures the tasklist file and directory are both readable and writeable, in the event the user changes the file
     * permissions directly
     * @param FILE the file object to store the list of tasks
     * @param DIRECTORY the directory object which stores the text file
     */
    private static void setReadAndWritePermissions(File FILE, File DIRECTORY) {
        FILE.setWritable(true);
        FILE.setReadable(true);
        DIRECTORY.setWritable(true);
        DIRECTORY.setReadable(true);
    }

    /**
     * Reads in each task from a saved list of task and determines how it should be added to the list
     * @param list list of tasks to store the tasks read from the text file
     * @param taskInFileArray list of tasks in the text file
     */
    private static void parseSavedTaskList(ArrayList<Task> list, String[] taskInFileArray) {
        String[] taskType = taskInFileArray[1].split(" ", 2);
        String isDone = taskInFileArray[0];
        switch (taskType[TASK_TYPE]) {
        case TASKTYPE_TODO:
            readFromFileTodo(list, taskType, isDone);
            break;
        case TASKTYPE_DEADLINE:
            readFromFileDeadline(list, taskType, isDone);
            break;
        case TASKTYPE_EVENT:
            readFromFileEvent(list, taskType, isDone);
            break;
        default:
            System.out.println(UNKNOWN_COMMAND_TEXT_FILE);
        }
    }

    /**
     * Opens the target file in order to read its contents
     * @param FILE the file to be read
     * @param fileScanner a pointer to the file
     * @return the pointer to the file if said file exists
     */
    private static Scanner openTaskFile(File FILE, Scanner fileScanner) {
        try {
            fileScanner = getScanner(FILE, fileScanner);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return fileScanner;
    }

    /**
     * Checks if target file exists, and creates a new one if it does not exist yet
     * @param FILE the target file
     */
    private static void checkFileExists(File FILE) {
        if (!FILE.exists()) {
            try {
                createTaskFile(FILE);
            } catch (IOException e) {
                System.out.println(CREATE_FILE_FAILED);
            }
        }
    }

    /**
     * Checks if target directory exists, and creates a new one if it does not exist yet
     * @param DIRECTORY the target directory
     */
    private static void checkDirExists(File DIRECTORY) {
        if (!DIRECTORY.exists()) {
                DIRECTORY.mkdir();
        }
    }

    /**
     * Creates a new text file to save the list of tasks to
     * @param fileobj the file to be created
     * @throws IOException if file creation fails
     */
    private static void createTaskFile(File fileobj) throws IOException {
            fileobj.createNewFile();
    }

    /**
     * Places a pointer at the beginning of the file to begin reading of its contents
     * @param fileobj the file itself
     * @param fileScanner the name of the pointer
     * @return the pointer to the beginning of the file
     * @throws FileNotFoundException if file currently does not exist
     */
    private static Scanner getScanner(File fileobj, Scanner fileScanner) throws FileNotFoundException {
        fileScanner = new Scanner(fileobj);
        return fileScanner;
    }

    /**
     * Inserts an event from the saved list of tasks
     * @param list list of tasks to store the tasks read from the text file
     * @param taskType the type of task, whether its a Todo, Deadline or Event task
     * @param isDone boolean to represent if the task was previously marked as done
     */
    private static void readFromFileEvent(ArrayList<Task> list, String[] taskType, String isDone) {
        try {
            addEventTask(list, taskType);
        } catch (AeonException e) {
            System.out.println(TASK_DETAILS_MISSING_MSG);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(EVENT_FORMAT_ERR);
        }
        fileMarkTask(list, isDone);
    }

    /**
     * Inserts a deadline from the saved list of tasks
     * @param list list of tasks to store the tasks read from the text file
     * @param taskType the type of task, whether its a Todo, Deadline or Event task
     * @param isDone boolean to represent if the task was previously marked as done
     */
    private static void readFromFileDeadline(ArrayList<Task> list, String[] taskType, String isDone) {
        try {
            addDeadlineTask(list, taskType);
        } catch (AeonException e) {
            System.out.println(TASK_DETAILS_MISSING_MSG);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(DEADLINE_FORMAT_ERR);
        }

        fileMarkTask(list, isDone);
    }

    /**
     * Inserts a ToDo task from the saved list of tasks
     * @param list list of tasks to store the tasks read from the text file
     * @param taskType the type of task, whether its a Todo, Deadline or Event task
     * @param isDone boolean to represent if the task was previously marked as done
     */
    private static void readFromFileTodo(ArrayList<Task> list, String[] taskType, String isDone) {
        try {
            addTodoTask(list, taskType);
        } catch (AeonException e) {
            System.out.println(TASK_DETAILS_MISSING_MSG);
        }
        fileMarkTask(list, isDone);
    }

    /**
     * Checks if task from text file was marked as done before, and mark it if true
     * @param list list of tasks to store the tasks read from the text file
     * @param isDone boolean to represent if the task was previously marked as done
     */
    private static void fileMarkTask(ArrayList<Task> list, String isDone) {
        if (isDone.equals("1")) {
            Integer index = Task.getNoOfItems();
            list.get(index - 1).setDoneStatus(true);
        }
    }

    /**
     * Adds a task to the list
     * @param list list of tasks itself
     * @param t the task object to be added
     * @param taskType the type of task, whether its a Todo, Deadline or Event task
     * @param rawDesc the actual user input itself, to be saved into the text file
     */
    public static void addToList(ArrayList<Task> list, Task t, String taskType, String rawDesc) {
        list.add(t);
        rawDescriptions.add(taskType + " " + rawDesc);
        Task.setNoOfItems(Task.getNoOfItems() + 1);
        System.out.println("Total: " + Task.getNoOfItems() + " task(s) in the list!");
        try {
            writeToFile(list, rawDescriptions);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves the current list of tasks into a text file on disk
     * @param list the list of tasks to be saved
     * @param rawDesc the actual user input itself, to be saved into the text file
     * @throws IOException if writing to file fails
     */
    private static void writeToFile(ArrayList<Task> list, ArrayList<String> rawDesc) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        Integer noOfItems = Task.getNoOfItems();
        for (int index = 0; index < noOfItems; index++) {
            String taskDone = list.get(index).getStatusIcon();
            if (taskDone.equals(TASK_MARKED)) {
                taskDone = "1";
            } else {
                taskDone = "0";
            }
            String[] taskToAdd = rawDesc.get(index).split(" ", 2);
            String textToAdd = taskDone + " " + taskToAdd[0] + " " + taskToAdd[1] + "\n";
            fw.write(textToAdd);
            }
        fw.close();
    }

    /**
     * Parses the task knowing that it is of Event type
     * @param list list of tasks itself
     * @param words command itself
     * @throws IndexOutOfBoundsException if some parameters of the task is missing
     * @throws AeonException if user tries to add blank parameters
     */
    private static void addEventTask(ArrayList<Task> list, String[] words) throws IndexOutOfBoundsException, AeonException {
        String[] eventDateTask = words[1].split(" /at ", 2);
        if (checkDetails(eventDateTask)) {
            throw new AeonException();
        }
        String rawDate = eventDateTask[1].trim();
        try {
            String eventDate = reformatDate(rawDate);
            Task e = new Event(eventDateTask[0].trim(), eventDate);
            System.out.println(e);
            addToList(list, e, "E", words[1]);
        } catch (DateTimeParseException x) {
            Task e = new Event(eventDateTask[0].trim(), rawDate);
            System.out.println(e);
            addToList(list, e, "E", words[1]);
        }
    }

    /**
     * Parses the task knowing that it is of Deadline type
     * @param list list of tasks itself
     * @param words command itself
     * @throws IndexOutOfBoundsException if some parameters of the task is missing
     * @throws AeonException if user tries to add blank parameters
     */
    private static void addDeadlineTask(ArrayList<Task> list, String[] words)
            throws IndexOutOfBoundsException, AeonException {
        String[] deadlineTask = words[1].split(" /by ", 2);
        if (checkDetails(deadlineTask)) {
            throw new AeonException();
        }
        String rawDate = deadlineTask[1].trim();
        try {
            String dueDate = reformatDate(rawDate);
            Task d = new Deadline(deadlineTask[0].trim(), dueDate);
            System.out.println(d);
            addToList(list, d, "D", words[1]);
        } catch (DateTimeParseException e) {
            Task d = new Deadline(deadlineTask[0].trim(), rawDate);
            System.out.println(d);
            addToList(list, d, "D", words[1]);
        }
    }

    /**
     * Parses the task knowing that it is of Todo type
     * @param list list of tasks itself
     * @param words command itself
     */
    private static void addTodoTask(ArrayList<Task> list, String[] words)
            throws IndexOutOfBoundsException, AeonException {
        Task t = new Todo(words[1]);
        if (checkDetails(words)) {
            throw new AeonException();
        }
        System.out.println(t);
        addToList(list, t, "T", words[1]);
    }

    /**
     *Marks a task as Done based on its index.
     *Users may only mark tasks that are currently existing in the list
     * @param list list of tasks itself
     * @param words command itself
     * @throws IndexOutOfBoundsException if task is not found in the list
     * @throws NumberFormatException if the index of the targeted task is not a valid integer
     */
    private static void markTask(ArrayList<Task> list, String[] words) throws IndexOutOfBoundsException,
            NumberFormatException {
        int index;
        index = Integer.parseInt(words[1]);
        list.get(index - 1).setDoneStatus(true);

        System.out.println(list.get(index - 1));
        try {
            writeToFile(list, rawDescriptions);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *Marks a task as Not Done based on its index.
     *Users may only unmark tasks that are currently existing in the list
     * @param list list of tasks itself
     * @param words command itself
     * @throws IndexOutOfBoundsException if task is not found in the list
     * @throws NumberFormatException if the index of the targeted task is not a valid integer
     */
    private static void unmarkTask(ArrayList<Task> list, String[] words)
            throws IndexOutOfBoundsException, NumberFormatException {
        int index = Integer.parseInt(words[1]);
        list.get(index - 1).setDoneStatus(false);

        System.out.println(list.get(index - 1));
        try {
            writeToFile(list, rawDescriptions);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints out all tasks that are currently saved in the list
     * @param list the list of tasks to be printed out
     */
    private static void printListOfTasks(ArrayList<Task> list) {
        Integer noOfItems = Task.getNoOfItems();
        if (noOfItems == 0) {
            System.out.println(NO_TASKS);
        }
        for (int index = 0; index < noOfItems; index++) {
            System.out.println(index + 1 + ". " + list.get(index));
        }
    }

    /**
     * Deletes a task from the list of tasks.
     * Task can only be deleted if it exists in the list.
     * @param list list of tasks itself
     * @param words command itself
     * @throws IndexOutOfBoundsException if task is not found in the list of tasks
     * @throws NumberFormatException if index is not of integer data type
     */
    private static void deleteTask(ArrayList<Task> list, String[] words)
            throws IndexOutOfBoundsException, NumberFormatException {
        int index = Integer.parseInt(words[1]);
        System.out.println(list.get(index - 1));
        list.remove(index - 1);
        Task.setNoOfItems(Task.getNoOfItems() - 1);
        System.out.println("Total: " + Task.getNoOfItems() + " task(s) in the list!");
        try {
            writeToFile(list, rawDescriptions);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Changes the date format from YYYY-MM-DD to MM-DD-YYYY for Deadline and Event tasks
     * @param rawDate The date that the user inputs
     * @return The reformatted date in the form of MM-DD-YYYY
     */
    private static String reformatDate(String rawDate) {
        LocalDate deadline = LocalDate.parse(rawDate);
        String dueDate = deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return dueDate;
    }

    /**
     * Checks if the user tries to input blank parameters
     * @param taskDetails a list of parameters belonging to a single task
     * @return boolean if task is considered valid or not
     */
    private static boolean checkDetails(String[] taskDetails) {
        boolean isEmpty = false;
        for (String element : taskDetails) {
            if (element.trim().length() == 0)
            {
                isEmpty = true;
            }
        }
        return isEmpty;
    }

}
