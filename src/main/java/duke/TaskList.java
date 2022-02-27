package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;

/**
 * Contains the list of tasks and performs commands
 * operations.
 */
public class TaskList {

    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_FIND = "find";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_TODO = "todo";
    private static Ui ui;
    private static Parser parser;
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static int itemNumber;
    private static String[] datesArray;
    private static String[] eventsArray;

    /**
     * Gets the task list stored in the task list object and returns
     * the list.
     *
     * @return The current task list
     */
    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Creates a new list of task object to store all the individual
     * tasks the user wants to store.
     *
     * @param newTaskList The task list loaded from the save local file
     * @param savedItemNumber The number of items in the list of the loaded saved local file
     */
    public TaskList(ArrayList<Task> newTaskList, int savedItemNumber) {
        ui = new Ui();
        parser = new Parser();
        this.taskList = newTaskList;
        this.itemNumber = savedItemNumber;
    }

    /**
     * Takes in the user input string and checks the type of command the
     * user wants the program to execute and calls the relevant operation function.
     *
     * @param inputCommand The user input string
     * @throws DukeException If there is an error adding a task
     */
    public static void executeCommand(String inputCommand) throws DukeException {
        String command = parser.parseCommandFromString(inputCommand);
        switch (command) {
        case COMMAND_LIST:
            listAllEntry();
            break;
        case COMMAND_MARK:
            int markNum = parser.parseMarkIndexFromString(inputCommand);
            markEntry(markNum);
            break;
        case COMMAND_UNMARK:
            int unMarkNum = parser.parseUnMarkIndexFromString(inputCommand);
            unMarkEntry(unMarkNum);
            break;
        case COMMAND_DELETE:
            int deleteIndex = parser.parseDeleteIndexFromString(inputCommand);
            deleteEntry(deleteIndex);
            break;
        case COMMAND_FIND:
            findKeyword(inputCommand);
            break;
        case COMMAND_BYE:
            exitProgram();
            break;
        default:
            addTask(inputCommand);
            break;
        }
    }

     /**
     * Takes in the user input, parse the keyword from the user input
     * and checks the list to find the tasks containing the keyword.
     * Prints all task found.
     * 
     * @param inputCommand The user input
     */
    private static void findKeyword(String inputCommand) {
        String keyword = parser.getKeywordFromString(inputCommand).trim();
        int listNum = 1;
        for (Task foundTask : taskList) {
            if (isFound(keyword, foundTask)) {
                ui.printFoundItems(listNum++, foundTask);
            }
        }
        if (listNum == 1) {
            ui.printNoMatchesFound();
        }
    }

    /**
     * Takes in the keyword and an individual task and checks
     * if the word is in the task description.
     *
     * @param keyword The word to check for
     * @param t The task to be checked
     * @return True if the task description contains the word, false otherwise
     */
    private static boolean isFound(String keyword, Task t) {
        return Arrays.asList(t.getDescription().trim().split(" ")).contains(keyword);
    }

     /**
     * Lists all the task in the task list by printing each task
     * to the standard output.
     */
    private static void listAllEntry() {
        int listNum;
        listNum = 1;
        if (itemNumber == 0) {
            ui.printListEmptyMessage();
        }
        else {
            for (Task singleTask : taskList) {
                ui.printAllTaskInList(listNum++, singleTask);
            }
        }
    }

    /**
     * Takes in the index of the item to mark and marks
     * the item in the list as done.
     *
     * @param markNum The index of the item to mark
     */
    private static void markEntry(int markNum) {
        try {
            if (taskList.get(markNum).isDone() == true) {
                ui.printAlreadyMarkDoneMessage();
            }
            else {
                taskList.get(markNum).setDone(true);
                ui.printMarkDoneMessage(taskList.get(markNum));
            }
        } catch(IndexOutOfBoundsException markOutOfBounds) {
            ui.printMarkIndexOutOfBounds();
        }
    }

    /**
     * Takes in the index of the item to mark and marks
     * the item in the list as not done.
     *
     * @param unMarkNum The index of the item to mark as not done
     */
    private static void unMarkEntry(int unMarkNum) {
        try {
            if (taskList.get(unMarkNum).isDone() == false) {
                ui.printAlreadyMarkNotDoneMessage();
            }
            else {
                taskList.get(unMarkNum).setDone(false);
                ui.printUnMarkDoneMessage(taskList.get(unMarkNum));
            }
        } catch(IndexOutOfBoundsException unMarkOutOfBounds) {
            ui.printUnMarkIndexOutOfBounds();
        }
    }

    /**
     * Takes in the index of the item to delete and deletes
     * the item in the list.
     *
     * @param deleteIndex The index of the item to be deleted
     */
    private static void deleteEntry(int deleteIndex) {
        try {

            ui.printAcknowledgeDeleteMessage(taskList.get(deleteIndex), itemNumber - 1);
            taskList.remove(deleteIndex);
            itemNumber--;
        } catch(IndexOutOfBoundsException deleteIndexOutOFBounds) {
            ui.itemNotInListMessage();
        }
    }

    /**
     * Takes in the adding of task command and perform the
     * instructed operation by calling the relevant add type
     * function.
     *
     * @param inCommand The user add task command
     * @throws DukeException If there is an error adding a task
     */
    private static void addTask(String inCommand) throws DukeException {
        String taskType = parser.parseTaskTypeFromString(inCommand);
        String[] taskDescription = parser.parseTaskDescriptionFromString(inCommand);
        switch (taskType) {
        case COMMAND_TODO:
            createToDoEntry(taskDescription);
            break;
        case COMMAND_DEADLINE:
            createDeadLineEntry(taskDescription);
            break;
        case COMMAND_EVENT:
            createEventEntry(taskDescription);
            break;
        default:
            ui.printUnknownCommand();
            break;
        }
    }

    /**
     * Takes in the todo task description, parse the task action
     * and add the task into the list
     * @param taskDescription The todo task description
     * @throws IndexOutOfBoundsException If the input of the action is empty
     */
    private static void createToDoEntry(String[] taskDescription) throws IndexOutOfBoundsException{
        String toDoAction;
        try {
            toDoAction = parser.parseToDoActionFromDescription(taskDescription);
            taskList.add(new ToDo(toDoAction));
            ui.printAcknowledgeAddMessage(taskList.get(itemNumber), itemNumber + 1);
            itemNumber++;
        } catch (IndexOutOfBoundsException todoEmpty) {
            ui.printTodoEmptyException();
        }
    }

    /**
     * Takes in the deadline task description and parse the task action and the
     * due date and adds them to the list.
     * @param taskDescription The deadline task description from the input
     * @throws DukeException If the deadline parameters are invalid
     * @throws ArrayIndexOutOfBoundsException If the deadline parameters are empty
     */
    private static void createDeadLineEntry(String[] taskDescription) throws DukeException, ArrayIndexOutOfBoundsException {
        LocalDate deadLineDate;
        try {
            datesArray = parser.parseDeadLineActionFromDescription(taskDescription);
            deadLineDate = parser.parseDateFormatFromString(datesArray[1]);
            if (isDateFormatInvalid(datesArray)) {
                throw new DukeException();
            }
            taskList.add(new Deadline(datesArray[0], deadLineDate));
            ui.printAcknowledgeAddMessage(taskList.get(itemNumber), itemNumber + 1);
            itemNumber++;
        } catch (ArrayIndexOutOfBoundsException deadlineEmpty) {
            ui.printDeadLineEmptyException();
        } catch (DukeException invalidDateLineInput) {
            ui.printInvalidDeadLineException();
        }
    }

    /**
     * Checks if the action parameter or the due date parameter is empty
     * @param datesArray the deadline description
     * @return True if any parameter is empty, false otherwise
     */
    private static boolean isDateFormatInvalid(String[] datesArray) {
        return datesArray[1].length() == 0 || datesArray[0].length() == 0;
    }

    /**
     * Takes in the event task description and parse the task action and the
     * due date and adds them to the list.
     * @param taskDescription The event task description from the input
     * @throws DukeException If the event parameters are invalid
     * @throws ArrayIndexOutOfBoundsException If the event parameters are empty
     */
    private static void createEventEntry(String[] taskDescription) throws DukeException, ArrayIndexOutOfBoundsException {
        LocalDate eventDate;
        try {
            eventsArray = parser.parseEventsActionFromDescription(taskDescription);
            eventDate = parser.parseDateFormatFromString(eventsArray[1]);
            if (isEventFormatInvalid(eventsArray)) {
                throw new DukeException();
            }
            taskList.add(new Event(eventsArray[0], eventDate));
            ui.printAcknowledgeAddMessage(taskList.get(itemNumber), itemNumber + 1);
            itemNumber++;
        } catch (ArrayIndexOutOfBoundsException eventEmpty) {
            ui.printEventEmptyException();
        } catch (DukeException invalidEventInput) {
            ui.printInvalidEventException();
        }
    }

    /**
     * Checks if the action parameter or the event date parameter is empty.
     * @param eventsArray the event description
     * @return True if any parameter is empty, false otherwise
     */
    private static boolean isEventFormatInvalid(String[] eventsArray) {
        return eventsArray[1].length() == 0 || eventsArray[0].length() == 0;
    }

    /**
     * Terminates the session.
     */
    private static void exitProgram() {
        ui.printGoodbyeMessage();
        System.exit(0);
    }
}
