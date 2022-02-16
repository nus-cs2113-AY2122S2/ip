package aeon.controller;
import java.io.IOException;
import java.util.Scanner;
import aeon.task.Task;
import aeon.task.Event;
import aeon.task.Todo;
import aeon.task.Deadline;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;


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
    public static final int TASK_TYPE = 0;
    public static final String TASKTYPE_TODO = "T";
    public static final String TASKTYPE_DEADLINE = "D";
    public static final String TASKTYPE_EVENT = "E";

    private static ArrayList<String> rawDescriptions = new ArrayList<>();
    public static final String TASK_DELETED = "Task deleted!";

    public static void CommandProcessor() {
        printWelcomeMessage();
        ArrayList<Task> list = new ArrayList<>();
        readSavedTaskList(list);
        Scanner in = new Scanner(System.in);
        String response = in.nextLine();
        while (!response.equals("bye")) {
            String[] words = response.split(" ", 2);
            switch (words[COMMAND_WORD]) {
            case TASK_LIST:
                printListOfTasks(list);
                break;
            case TASK_UNMARK:
                try {
                    unmarkTask(list, words);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(TASK_NOT_FOUND);
                } catch (NumberFormatException e) {
                    System.out.println(INVALID_INTEGER_MSG);
                }
                break;
            case TASK_MARK:
                try {
                    markTask(list, words);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(TASK_NOT_FOUND);
                } catch (NumberFormatException e) {
                    System.out.println(INVALID_INTEGER_MSG);
                }
                break;
            case TASK_TODO:
                try {
                    addTodoTask(list, words);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(TODO_DESC_ERROR);
                }
                break;
            case TASK_DEADLINE:
                try {
                    addDeadlineTask(list, words);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(DEADLINE_FORMAT_ERR);
                }
                break;
            case TASK_EVENT:
                try {
                    addEventTask(list, words);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(EVENT_FORMAT_ERR);
                }
                break;
            case TASK_DELETE:
                try {
                    deleteTask(list, words);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(TASK_NOT_FOUND);
                } catch (NumberFormatException e) {
                    System.out.println(INVALID_INTEGER_MSG);
                }
                break;
            default:
                System.out.println(INVALID_COMMAND);
                break;
            }
            response = in.nextLine();
        }
        printGoodbyeMessage();
    }

    private static void readSavedTaskList(ArrayList<Task> list) {
        File FILE = new File(FILE_PATH);
        File DIRECTORY = new File(DIR_PATH);
        if (!DIRECTORY.exists()) {
                DIRECTORY.mkdir();
        }
        if (!FILE.exists()) {
            try {
                createTaskFile(FILE);
            } catch (IOException e) {
                System.out.println(CREATE_FILE_FAILED);
            }
        }
        Scanner fileScanner = null;
        try {
            fileScanner = getScanner(FILE, fileScanner);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
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
            if (taskDone.equals("X")) {
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

    public static void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________\n");
    }

    private static void addEventTask(ArrayList<Task> list, String[] words) throws IndexOutOfBoundsException {
        String[] eventDate = words[1].split(" /at ", 2);
        Task e = new Event(eventDate[0].trim(), eventDate[1].trim());
        System.out.println(TASK_ADDED);
        System.out.println(e);
        addToList(list, e, "E", words[1]);
    }

    private static void addDeadlineTask(ArrayList<Task> list, String[] words) throws IndexOutOfBoundsException {
        String[] dueDate = words[1].split(" /by ", 2);
        Task d = new Deadline(dueDate[0].trim(), dueDate[1].trim());
        System.out.println(TASK_ADDED);
        System.out.println(d);
        addToList(list, d, "D", words[1]);
    }

    private static void addTodoTask(ArrayList<Task> list, String[] words) throws IndexOutOfBoundsException {
        Task t = new Todo(words[1]);
        System.out.println(TASK_ADDED);
        System.out.println(t);
        addToList(list, t, "T", words[1]);
    }

    private static void markTask(ArrayList<Task> list, String[] words) throws IndexOutOfBoundsException,
            NumberFormatException {
        int index;
        index = Integer.parseInt(words[1]);
        list.get(index - 1).setDoneStatus(true);
        System.out.println(CONGRATULATIONS_MSG);
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
        System.out.println(MARK_UNDONE);
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
        System.out.println(TASK_DELETED);
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

    public static void printWelcomeMessage() {
        String logo = "     /\\   |  ____/ __ \\| \\ | |\n"
                + "    /  \\  | |__ | |  | |  \\| |\n"
                + "   / /\\ \\ |  __|| |  | | . ` |\n"
                + "  / ____ \\| |___| |__| | |\\  |\n"
                + " /_/    \\_\\______\\____/|_| \\_|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm AEON, your personal TO-DO list bot!\nWhat can I do for you?");
    }
}
