package aeon.controller;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import aeon.exception.AeonException;
import aeon.task.Task;
import aeon.task.Event;
import aeon.task.Todo;
import aeon.task.Deadline;
import java.util.ArrayList;


/**
 * The core function of parsing and executing the user's commands into Aeon, and automatically
 * saving/loading all tasks to a text file on disk
 */
public class Command {

    private static final ArrayList<String> rawDescriptions = new ArrayList<>();
    /**
     * Processes each command input by the user and executes the respective methods depending
     * on the task specified
     */
    public static void CommandProcessor() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            Storage.readSavedTaskList(list);
        } catch (AeonException e) {
            UI.printOut(UI.TEXT_FILE_INCORRECT_CONTENTS);
        }
        Scanner in = new Scanner(System.in);
        String response = in.nextLine();
        while (!response.equals(UI.USER_BYE)) {
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
        switch (words[UI.COMMAND_WORD]) {
        case UI.TASK_LIST:
            printListOfTasks(list);
            break;
        case UI.TASK_UNMARK:
            executeUnmark(list, words);
            break;
        case UI.TASK_MARK:
            executeMark(list, words);
            break;
        case UI.TASK_TODO:
            executeTodo(list, words);
            break;
        case UI.TASK_DEADLINE:
            executeDeadline(list, words);
            break;
        case UI.TASK_EVENT:
            executeEvent(list, words);
            break;
        case UI.TASK_DELETE:
            executeDelete(list, words);
            break;
        case UI.TASK_FIND:
            //String target = words[TARGET_WORD];
            executeFind(list, words);
            break;
        default:
            UI.printOut(UI.INVALID_COMMAND);
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
            UI.printOut(UI.TASK_ADDED);
        } catch (IndexOutOfBoundsException e) {
            UI.printOut(UI.EVENT_FORMAT_ERR);
        } catch (AeonException e) {
            UI.printOut(UI.TASK_DETAILS_MISSING_MSG);
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
            UI.printOut(UI.TASK_ADDED);
        } catch (IndexOutOfBoundsException e) {
            UI.printOut(UI.DEADLINE_FORMAT_ERR);
        } catch (AeonException e) {
            UI.printOut(UI.TASK_DETAILS_MISSING_MSG);
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
            UI.printOut(UI.TASK_ADDED);
        } catch (IndexOutOfBoundsException | AeonException e) {
            UI.printOut(UI.TASK_DETAILS_MISSING_MSG);
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
            UI.printOut(UI.CONGRATULATIONS_MSG);
        } catch (IndexOutOfBoundsException e) {
            UI.printOut(UI.TASK_NOT_FOUND);
        } catch (NumberFormatException e) {
            UI.printOut(UI.INVALID_INTEGER_MSG);
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
            UI.printOut(UI.MARK_UNDONE);
        } catch (IndexOutOfBoundsException e) {
            UI.printOut(UI.TASK_NOT_FOUND);
        } catch (NumberFormatException e) {
            UI.printOut(UI.INVALID_INTEGER_MSG);
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
                UI.printOut(UI.EMPTY_KEYWORD_MSG);
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
            UI.printOut(UI.TASK_DELETED);
        } catch (IndexOutOfBoundsException e) {
            UI.printOut(UI.TASK_NOT_FOUND);
        } catch (NumberFormatException e) {
            UI.printOut(UI.INVALID_INTEGER_MSG);
        }
    }

    /**
     * Looks for all existing tasks that contain a specific keyword.
     * @param list list of tasks entered by the user
     * @param words command itself
     */
    private static void lookForTasks(ArrayList<Task> list, String[] words) {
        String target = words[UI.TARGET_WORD];
        boolean isFound = false;
        for (Task task : list) {
            if (task.getDescription().contains(target)) {
                isFound = true;
                System.out.println(task);
            }
        }
        if (!isFound) {
            UI.printOut("No tasks found!");
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
        UI.printOut("Total: " + Task.getNoOfItems() + " task(s) in the list!");
        try {
            Storage.writeToFile(list, rawDescriptions);
        } catch (IOException e) {
            UI.printOut(e.getMessage());
        }
    }

    /**
     * Parses the task knowing that it is of Event type
     * @param list list of tasks itself
     * @param words command itself
     * @throws IndexOutOfBoundsException if some parameters of the task is missing
     * @throws AeonException if user tries to add blank parameters
     */
    public static void addEventTask(ArrayList<Task> list, String[] words) throws IndexOutOfBoundsException,
            AeonException {
        String[] eventDateTask = words[1].split(" /at ", 2);
        if (checkDetails(eventDateTask)) {
            throw new AeonException();
        }
        String rawDate = eventDateTask[1].trim();
        try {
            String eventDate = Parser.reformatDate(rawDate);
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
    public static void addDeadlineTask(ArrayList<Task> list, String[] words)
            throws IndexOutOfBoundsException, AeonException {
        String[] deadlineTask = words[1].split(" /by ", 2);
        if (checkDetails(deadlineTask)) {
            throw new AeonException();
        }
        String rawDate = deadlineTask[1].trim();
        try {
            String dueDate = Parser.reformatDate(rawDate);
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
    public static void addTodoTask(ArrayList<Task> list, String[] words)
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
            Storage.writeToFile(list, rawDescriptions);
        } catch (IOException e) {
            UI.printOut(e.getMessage());
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
            Storage.writeToFile(list, rawDescriptions);
        } catch (IOException e) {
            UI.printOut(e.getMessage());
        }
    }

    /**
     * Prints out all tasks that are currently saved in the list
     * @param list the list of tasks to be printed out
     */
    private static void printListOfTasks(ArrayList<Task> list) {
        Integer noOfItems = Task.getNoOfItems();
        if (noOfItems == 0) {
            UI.printOut(UI.NO_TASKS);
        }
        for (int index = 0; index < noOfItems; index++) {
            UI.printOut(index + 1 + ". " + list.get(index));
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
        UI.printOut("Total: " + Task.getNoOfItems() + " task(s) in the list!");
        try {
            Storage.writeToFile(list, rawDescriptions);
        } catch (IOException e) {
            UI.printOut(e.getMessage());
        }
    }

    /**
     * Checks if the user tries to input blank parameters
     * @param taskDetails a list of parameters belonging to a single task
     * @return boolean if task is considered valid or not
     */
    public static boolean checkDetails(String[] taskDetails) {
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
