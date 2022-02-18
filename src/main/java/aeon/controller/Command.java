package aeon.controller;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;
import aeon.task.Task;
import aeon.task.Event;
import aeon.task.Todo;
import aeon.task.Deadline;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.time.LocalDate;

public class Command {

    public static final String TASK_NOT_FOUND = "Task not found! Perhaps try listing out the available tasks first...";
    public static final String INVALID_INTEGER_MSG = "Please type in a valid integer!";
    public static final String TODO_DESC_ERROR = "Description of TODO cannot be empty!!!";
    public static final String DEADLINE_FORMAT_ERR = "Please try again in this format: deadline /by <date>";
    public static final String EVENT_FORMAT_ERR = "Please try again in this format: event /at <date>";
    public static final String INVALID_COMMAND = "Not sure what you were trying to do...";
    public static final String TASK_ADDED = "Task added!";
    public static final String CONGRATULATIONS_MSG = "Congrats on completing this task!:";
    public static final String MARK_UNDONE = "Alright, marked as undone!:";
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

    private static ArrayList<String> rawDescriptions = new ArrayList<>();
    public static final String TASK_DELETED = "Task deleted!";

    public static void CommandProcessor() {
        ArrayList<Task> list = new ArrayList<>();
        readSavedTaskList(list);
        Scanner in = new Scanner(System.in);
        String response = in.nextLine();
        while (!response.equals(USER_BYE)) {
            String[] words = response.split(" ", 2);
            executeCommand(list, words);
            response = in.nextLine();
        }
    }

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
            String target = words[TARGET_WORD];
            executeFind(list, target);
            break;
        default:
            System.out.println(INVALID_COMMAND);
            break;
        }
    }

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

    private static void executeEvent(ArrayList<Task> list, String[] words) {
        try {
            addEventTask(list, words);
            System.out.println(TASK_ADDED);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(EVENT_FORMAT_ERR);
        }
    }

    private static void executeDeadline(ArrayList<Task> list, String[] words) {
        try {
            addDeadlineTask(list, words);
            System.out.println(TASK_ADDED);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(DEADLINE_FORMAT_ERR);
        }
    }

    private static void executeTodo(ArrayList<Task> list, String[] words) {
        try {
            addTodoTask(list, words);
            System.out.println(TASK_ADDED);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(TODO_DESC_ERROR);
        }
    }

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

    private static void executeFind(ArrayList<Task> list, String target) {
        for (Task task : list) {
            if (task.getDescription().contains(target)) {
                System.out.println(task);
            }
        }
    }

    private static void readSavedTaskList(ArrayList<Task> list) {
        File FILE = new File(FILE_PATH);
        File DIRECTORY = new File(DIR_PATH);
        checkDirExists(DIRECTORY);
        checkFileExists(FILE);
        Scanner fileScanner = null;
        fileScanner = openTaskFile(FILE, fileScanner);
        while (fileScanner.hasNext()) {
            String taskInFile = fileScanner.nextLine();
            String[] taskInFileArray = taskInFile.split(" ", 2);
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
            }
        }
    }

    private static Scanner openTaskFile(File FILE, Scanner fileScanner) {
        try {
            fileScanner = getScanner(FILE, fileScanner);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return fileScanner;
    }

    private static void checkFileExists(File FILE) {
        if (!FILE.exists()) {
            try {
                createTaskFile(FILE);
            } catch (IOException e) {
                System.out.println(CREATE_FILE_FAILED);
            }
        }
    }

    private static void checkDirExists(File DIRECTORY) {
        if (!DIRECTORY.exists()) {
                DIRECTORY.mkdir();
        }
    }


    private static void createTaskFile(File f) throws IOException {
            f.createNewFile();
    }

    private static Scanner getScanner(File f, Scanner fileScanner) throws FileNotFoundException {
        fileScanner = new Scanner(f);
        return fileScanner;
    }

    private static void readFromFileEvent(ArrayList<Task> list, String[] taskType, String isDone) {
        addEventTask(list, taskType);
        fileMarkTask(list, isDone);
    }

    private static void readFromFileDeadline(ArrayList<Task> list, String[] taskType, String isDone) {
        addDeadlineTask(list, taskType);
        fileMarkTask(list, isDone);
    }

    private static void readFromFileTodo(ArrayList<Task> list, String[] taskType, String isDone) {
        addTodoTask(list, taskType);
        fileMarkTask(list, isDone);
    }

    private static void fileMarkTask(ArrayList<Task> list, String isDone) {
        if (isDone.equals("1")) {
            Integer index = Task.getNoOfItems();
            list.get(index - 1).setDoneStatus(true);
        }
    }

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


    private static void addEventTask(ArrayList<Task> list, String[] words) throws IndexOutOfBoundsException {
        String[] eventDateTask = words[1].split(" /at ", 2);
        String rawDate = eventDateTask[1].trim();
        try {
            String eventDate = reformatDate(rawDate);
            Task e = new Event(eventDateTask[0].trim(), eventDate);
            System.out.println(e);
            addToList(list, e, "E", words[1]);
        } catch (DateTimeParseException x) {
            Task e = new Deadline(eventDateTask[0].trim(), rawDate);
            System.out.println(e);
            addToList(list, e, "E", words[1]);
        }
    }

    private static void addDeadlineTask(ArrayList<Task> list, String[] words) throws IndexOutOfBoundsException {
        String[] deadlineTask = words[1].split(" /by ", 2);
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

    private static String reformatDate(String rawDate) {
        LocalDate deadline = LocalDate.parse(rawDate);
        String dueDate = deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return dueDate;
    }

    private static void addTodoTask(ArrayList<Task> list, String[] words) throws IndexOutOfBoundsException {
        Task t = new Todo(words[1]);
        System.out.println(t);
        addToList(list, t, "T", words[1]);
    }

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

    private static void printListOfTasks(ArrayList<Task> list) {
        Integer noOfItems = Task.getNoOfItems();
        if (noOfItems == 0) {
            System.out.println(NO_TASKS);
        }
        for (int index = 0; index < noOfItems; index++) {
            System.out.println(index + 1 + ". " + list.get(index));
        }
    }

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


}
