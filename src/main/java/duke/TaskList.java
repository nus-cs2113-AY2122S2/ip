package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;

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

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

    public TaskList(ArrayList<Task> newTaskList, int savedItemNumber) throws DukeException, IOException {
        ui = new Ui();
        parser = new Parser();
        this.taskList = newTaskList;
        this.itemNumber = savedItemNumber;
    }

    public static void executeCommand(String inputCommand) throws DukeException, IOException {
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
    }

    private static boolean isFound(String keyword, Task t) {
        return Arrays.asList(t.getDescription().split(" ")).contains(keyword);
    }

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

    private static void deleteEntry(int deleteIndex) {
        try {

            ui.printAcknowledgeDeleteMessage(taskList.get(deleteIndex), itemNumber - 1);
            taskList.remove(deleteIndex);
            itemNumber--;
        } catch(IndexOutOfBoundsException deleteIndexOutOFBounds) {
            ui.itemNotInListMessage();
        }
    }

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

    private static void createToDoEntry(String[] taskDescription) {
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

    private static void createDeadLineEntry(String[] taskDescription) {
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

    private static boolean isDateFormatInvalid(String[] datesArray) {
        return datesArray[1].length() == 0 || datesArray[0].length() == 0;
    }

    private static void createEventEntry(String[] taskDescription) {
        try {
            eventsArray = parser.parseEventsActionFromDesciption(taskDescription);
            if (isEventFormatInvalid(eventsArray)) {
                throw new DukeException();
            }
            taskList.add(new Event(eventsArray[0], eventsArray[1]));
            ui.printAcknowledgeAddMessage(taskList.get(itemNumber), itemNumber + 1);
            itemNumber++;
        } catch (ArrayIndexOutOfBoundsException eventEmpty) {
            ui.printEventEmptyException();
        } catch (DukeException invalidEventInput) {
            ui.printInvalidEventException();
        }
    }

    private static boolean isEventFormatInvalid(String[] eventsArray) {
        return eventsArray[1].length() == 0 || eventsArray[0].length() == 0;
    }

    private static void exitProgram() {
        ui.printGoodbyeMessage();
        System.exit(0);
    }
}
