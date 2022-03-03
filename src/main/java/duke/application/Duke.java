package duke.application;

import duke.entity.Deadline;
import duke.entity.Event;
import duke.entity.Todo;
import duke.entity.Task;
import duke.exception.DukeException;
import duke.exception.IllegalDeadlineException;
import duke.exception.IllegalDeleteException;
import duke.exception.IllegalEventException;
import duke.exception.IllegalFindException;
import duke.exception.IllegalTodoException;

import duke.database.TaskDatabase;

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

/**
 * Main program user interface.
 */
public class Duke {
    public static final String taskAddedSuccessfully = "Got it, Olivia has added this task:";
    public static final String FILENAME = "taskdata.txt";
    public static final List<String> VALID_COMMANDS_FOR_STARTS_WITH = Arrays.asList("todo", "event","deadline","mark",
            "unmark","delete","find");
    public static final List<String> VALID_COMMANDS_FOR_EQUALS = Arrays.asList("list","bye");

    public static void main(String[] args) throws IOException {
        printWelcomeMessage();
        ArrayList<Task> taskList;
        taskList = loadData();
        getUserInput(taskList);
        storeData(taskList);
        printGoodbyeMessage();
    }
    /**
     * Gets the user input and validates it
     * Throws exception if user does not input valid command.
     *
     * @param taskList list of all tasks.
     */
    private static void getUserInput(ArrayList<Task> taskList) {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        parseCommand(taskList, sc, userInput);
    }
    /**
     * Stores data in task list into an external file.
     *
     * @param taskList list of all tasks
     * @throws IOException If format not valid.
     */
    private static void storeData(ArrayList<Task> taskList) throws IOException {
        TaskDatabase.getInstance().save(FILENAME, taskList);
    }
    /**
     * Read in data from database.
     */
    private static ArrayList<Task> loadData() {
        return TaskDatabase.getInstance().read(FILENAME);
    }
    /**
     * Make sense of user command.
     */
    private static void parseCommand(ArrayList<Task> taskList, Scanner sc, String userInput) {
        while (!userInput.equals("bye")) {
            try {
                System.out.println("-----------------------------");
                if (userInput.equals("list")) {
                    displayTaskListMenu();
                    displayAllTasks(taskList);
                } else if (userInput.startsWith("unmark")) {
                    try {
                        Task task = getTask(userInput, taskList);
                        task.markAsNotDone();
                        System.out.println(task);
                    } catch (NullPointerException e) {
                        System.out.println("you have failed to unmark the task");
                    }
                } else if (userInput.startsWith("mark")) {
                    try {
                        Task task = getTask(userInput, taskList);
                        task.markAsDone();
                        System.out.println(task);
                    } catch (NullPointerException e) {
                        System.out.println("you have failed to mark the task");
                    }
                } else if (userInput.startsWith("deadline")) {
                    try {
                        Deadline deadline = getDeadline(userInput);
                        taskList.add(deadline);
                        System.out.println(taskAddedSuccessfully);
                        System.out.println(deadline);
                        printTotalNumberOfTasks(taskList);
                    } catch (IllegalDeadlineException e) {
                        System.out.println("OOPS!!! Deadline must have a description and due date.");
                    }
                } else if (userInput.startsWith("event")) {
                    try {
                        Event event = getEvent(userInput);
                        taskList.add(event);
                        System.out.println(taskAddedSuccessfully);
                        System.out.println(event);
                        printTotalNumberOfTasks(taskList);
                    } catch (IllegalEventException e) {
                        System.out.println("OOPS!!! Event must have a description and date");
                    }
                } else if (userInput.startsWith("todo")) {
                    try {
                        Todo todo = getTodo(userInput);
                        taskList.add(todo);
                        System.out.println(taskAddedSuccessfully);
                        System.out.println(todo);
                        printTotalNumberOfTasks(taskList);
                    } catch (IllegalTodoException e) {
                        System.out.println("OOPS!!! The description of todo cannot be empty");
                    }
                } else if (userInput.startsWith("delete")) {
                    try {
                        int indexToDelete = getIndexToDelete(userInput);
                        Task task = taskList.get(indexToDelete);
                        taskList.remove(indexToDelete);
                        System.out.println("Noted. Olivia has removed this task:");
                        System.out.println(task);
                        printTotalNumberOfTasks(taskList);
                    } catch (IllegalDeleteException e) {
                        System.out.println("To delete a task, please enter in the following format: delete <Task Number>");
                    } catch (NumberFormatException e) {
                        System.out.println("You must enter a task number to delete it");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("The task number you entered does not exist");
                    }
                } else if (userInput.startsWith("find")) {
                    try {
                        String keyword = getUserInputtedKeyword(userInput);
                        printTasksMatchingKeyword(taskList, keyword);
                    } catch (IllegalFindException e) {
                        System.out.println("You can only search by a single keyword, try again.");
                    }
                }
                userInput = sc.nextLine();
                checkUserInputValidity(userInput);
                System.out.println("-----------------------------");
            } catch (DukeException e) {
                printIllegalCommandErrorMessage();
            }
        }
    }
    /**
     * Print all tasks matching keyword
     * If no tasks found, print no match message.
     *
     * @param taskList list of all tasks
     * @param keyword user inputted keyword to search for
     */
    private static void printTasksMatchingKeyword(ArrayList<Task> taskList, String keyword) {
        boolean isFound = false;
        int i = 1;
        for (Task task : taskList) {
            if (task.getDescription().contains(keyword)) {
                if (isFound == false) {
                    System.out.println("Here are the matching tasks in your list: ");
                    isFound = true;
                }
                System.out.print(i + ".");
                System.out.println(task);
                ++i;
            }
        }
        if (isFound == false) {
            System.out.println("Unfortunately, there are no matching keywords!");
        }
    }
    /**
     * Returns keyword specified by user.
     *
     * @param userInput string of raw user inputted keyword
     * @return Keyword entered by user.
     * @throws IllegalFindException If length of user input is not 2.
     */
    private static String getUserInputtedKeyword(String userInput) throws IllegalFindException {
        String[] tokenArray = stringToToken(userInput, " ");
        if (tokenArray.length != 2) {
            throw new IllegalFindException();
        }
        return tokenArray[1].trim();
    }
    /**
     * Returns the index to delete from tasklist array.
     *
     * @param userInput User Inputted Index to delete
     * @return Index of task to delete based on index of task list.
     * @throws IllegalDeleteException If user does not enter index or enters more than one index.
     */
    private static int getIndexToDelete(String userInput) throws IllegalDeleteException {
        String[] tokenArray = stringToToken(userInput," ");
        if (tokenArray.length != 2) {
            throw new IllegalDeleteException();
        }
        return Integer.parseInt(tokenArray[1].trim()) - 1;
    }

    /**
     * Print Message prompting to user to enter a valid command.
     */
    private static void printIllegalCommandErrorMessage() {
        System.out.println("OOPS you have confused Olivia, please enter a valid command");
        System.out.println("Please make sure your command starts with find, todo, event, deadline, mark,"
                + " unmark or equals list or bye");
    }
    /**
     * Checks the validity of user input command.
     *
     * @param userInput Raw user input string
     * @throws DukeException If user command does not match list of valid command.
     */
    private static void checkUserInputValidity(String userInput) throws DukeException {
        //checking validity of commands which uses "start with"
        boolean isStartWithCommandValid = VALID_COMMANDS_FOR_STARTS_WITH.stream().anyMatch(userInput::startsWith);
        //checking validity of commands which uses "equals"
        boolean isEqualsToCommandValid = VALID_COMMANDS_FOR_EQUALS.stream().anyMatch(userInput::equals);
        //if either 1 is true -> don't throw exception, else throw an exception
        if (!(isStartWithCommandValid ^ isEqualsToCommandValid)) {
            throw new DukeException();
        }
    }
    /**
     * Print total number of tasks in task list
     */
    private static void printTotalNumberOfTasks(ArrayList<Task> taskList) {
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
    /**
     * Returns a newly created Todo Object
     * If user input is invalid, exception is thrown.
     *
     * @param userInput raw user input of todo
     * @return Todo object.
     * @throws IllegalTodoException If user input does not contain a todo action.
     */
    private static Todo getTodo(String userInput) throws IllegalTodoException {
        String[] tokenArray = stringToToken(userInput,"todo");
        if (tokenArray.length < 2) {
            throw new IllegalTodoException();
        }
        String description = tokenArray[1];
        return new Todo(description);
    }
    /**
     * Returns newly created Event Object
     * If the user input is invalid, returns nothing.
     *
     * @param userInput raw user input of Event
     * @return Event object.
     * @throws IllegalEventException If Event does not contain /at
     * @throws IllegalEventException If Event does not contain description
     */
    private static Event getEvent(String userInput) throws IllegalEventException {
        if (!userInput.contains("/at")) {
            throw new IllegalEventException();
        }
        String[] tokenArray = stringToToken(userInput,"/at");
        if (tokenArray.length < 2) {
            throw new IllegalEventException();
        }
        String duration = tokenArray[1].trim();
        String description = tokenArray[0].split("event")[1].trim();
        if (description.isBlank()) {
            throw new IllegalEventException();
        }
        //Event event = new Event(description,duration);
        return new Event(description,duration);
    }
    /**
     * Returns newly created Deadline Object
     * If user input is invalid, NaN is returned.
     *
     * @param userInput raw user input of a deadline.
     * @return Deadline Object
     * @throws IllegalDeadlineException If user input does not contain /by
     * @throws IllegalDeadlineException If user input does not contain description
     */
    private static Deadline getDeadline(String userInput) throws IllegalDeadlineException {
        if (!userInput.contains("/by")) {
            throw new IllegalDeadlineException();
        }
        String[] tokenArray = stringToToken(userInput,"/by");
        if (tokenArray.length < 2) {
            throw new IllegalDeadlineException();
        }
        String by = tokenArray[1].trim();
        String description = tokenArray[0].split("deadline")[1].trim();
        if (description.isBlank()) {
            throw new IllegalDeadlineException();
        }
        //Deadline deadline = new Deadline(description,by);
        return new Deadline(description,by);
    }
    /**
     * Returns task object
     * If the task does not exist, return NaN.
     *
     * @param userInput raw user input of task index
     * @param taskList list of all tasks
     * @return task object
     * @throws NumberFormatException If task number is not numeric.
     * @throws IndexOutOfBoundsException If task number does not exist in the list.
     */
    private static Task getTask(String userInput,ArrayList<Task> taskList) {
        try {
            int taskIndex = getTaskIndex(userInput);
            return taskList.get(taskIndex);
        } catch (NumberFormatException e) {
            System.out.print("OOPS!!!! Invalid task number,");
        } catch (IndexOutOfBoundsException e) {
            System.out.print("OOPS!!!! The task number you entered doesn't exist, ");
        }
        return null;
    }
    /**
     * Returns index as per task list.
     *
     * @param userInput Raw user input of task index
     * @return Index of task in task list
     */
    private static int getTaskIndex(String userInput) {
        String[] tokenArray = stringToToken(userInput," ");
        return Integer.parseInt(tokenArray[1]) - 1; //index is raw value - 1
    }
    /**
     * Returns array of Strings corresponding to user input
     *
     * @param userInput Raw user input
     * @param delimiter Specified delimiter to split raw user input by
     * @return Array of Strings that have been split
     */
    private static String[] stringToToken(String userInput,String delimiter) {
        return userInput.split(delimiter);
    }
    /**
     * Print all tasks in the task list
     *
     * @param taskList List of all tasks
     */
    private static void displayAllTasks(ArrayList<Task> taskList) {
        int i = 1;
        for (Task task : taskList) {
            System.out.print(i + ".");
            System.out.println(task);
            ++i;
        }
        System.out.println("-----------------------------");
    }
    /**
     * Prints head statement when displaying task list menu
     */
    private static void displayTaskListMenu() {
        System.out.println("Olivia presents you a list of tasks for you to do:");
    }
    /**
     * Prints message indicating termination of program
     */
    private static void printGoodbyeMessage() {
        System.out.println("-----------------------------");
        System.out.println("Bye. Hope to see you again soon! With Love, Olivia");
        System.out.println("-----------------------------");
    }
    /**
     * Prints message indicating start of program
     */
    private static void printWelcomeMessage() {
        String botLogo = "  ___                             __\n"
                       + "|  _  |  |   |  \\        / |     /  \\\n"
                       + "| | | |  |   |   \\      /  |    / _  \\\n"
                       + "| | | |  |   |    \\    /   |   / /_\\  \\\n"
                       + "| |_| |  |   |     \\  /    |  /   _    \\\n"
                       + "| ___ |  |__ |      \\/     | /___/ \\____\\\n";
        System.out.println("Hello from\n" + botLogo);
        System.out.println("-----------------------------");
        System.out.println("Greetings! I'm Olivia, your lovely personal assistant.");
        System.out.println("What can Olivia do for you my love?");
        System.out.println("-----------------------------");
    }
}

