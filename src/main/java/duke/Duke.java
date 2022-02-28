package duke;

import duke.task.*;

public class Duke {

    public static final int MINIMUM_LENGTH_OF_TODO_STATEMENT = 4;
    public static final int MINIMUM_LENGTH_OF_DEADLINE_STATEMENT = 8;
    public static final int MINIMUM_LENGTH_OF_EVENT_STATEMENT = 5;
    public static final int MINIMUM_LENGTH_OF_MARK_STATEMENT = 4;
    public static final int MINIMUM_LENGTH_OF_FIND_STATEMENT = 4;
    public static final int MINIMUM_LENGTH_OF_UNMARK_STATEMENT = 6;
    public static final int MINIMUM_LENGTH_OF_DELETE_STATEMENT = 6;
    public static final String DATAFILE_RELATIVE_PATH = "data\\duke.txt";
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final int INVALID_TASKNUMBER = -1;

    static TaskList taskList = new TaskList();
    static boolean isModified = false;      // isModified refers to whether taskList is modified or not.
    static Ui ui = new Ui();
    static Storage storage = new Storage();
    static Parser parser = new Parser();

    public static void addTodoToList(String todoDescription) {
        Todo newTodo = new Todo(todoDescription);
        //add the new object to taskList.
        taskList.add(newTodo);
        ui.printAddedTask(taskList);
    }

    // overloaded function called when loading data.
    public static void addTodoToList(String todoDescription, boolean isUserMode) {
        Todo newTodo = new Todo(todoDescription);
        //add the new object to taskList.
        taskList.add(newTodo);
    }

    public static void addEventToList(String eventDescription, String eventTime) {
        Event newEvent = new Event(eventDescription, eventTime);
        // add the new object to taskList.
        taskList.add(newEvent);
        ui.printAddedTask(taskList);
    }

    // overloaded function called when loading data.
    public static void addEventToList(String eventDescription, String eventTime, boolean isUserMode) {
        Event newEvent = new Event(eventDescription, eventTime);
        // add the new object to taskList.
        taskList.add(newEvent);
    }

    public static void addDeadlineToList(String deadlineDescription, String dueDate) {
        Deadline newDeadline = new Deadline(deadlineDescription, dueDate);
        // add the new object to taskList.
        taskList.add(newDeadline);
        ui.printAddedTask(taskList);
    }

    // overloaded function called when loading data.
    public static void addDeadlineToList(String deadlineDescription, String dueDate, boolean isUserMode) {
        Deadline newDeadline = new Deadline(deadlineDescription, dueDate);
        // add the new object to taskList.
        taskList.add(newDeadline);
    }


    // method adds task to task_list.
    public static void addTaskToList(String userInput) {
        if (parser.isTodo(userInput)) {
            if (parser.isInvalidTodo(userInput)) {
                ui.printInvalidTodo();
                return;
            }

            String todoDescription = parser.getTodoDescription(userInput);
            addTodoToList(todoDescription);

        } else if (parser.isEvent(userInput)) {
            if (parser.isInvalidEvent(userInput)) {
                ui.printInvalidEvent();
                return;
            }

            StringPair pair = parser.getEventDescriptionAndTime(userInput);
            String eventDescription = pair.getFirst();
            String eventTime = pair.getSecond();
            addEventToList(eventDescription, eventTime);

        } else if (parser.isDeadline(userInput)) {
            if (parser.isInvalidDeadline(userInput)) {
                ui.printInvalidDeadline();
                return;
            }

            StringPair pair = parser.getDeadlineDescriptionAndTime(userInput);
            String deadlineDescription = pair.getFirst();
            String dueDate = pair.getSecond();
            addDeadlineToList(deadlineDescription, dueDate);
        }

        // If not a recognizable command, inform user
        else {
            ui.printInvalidCommand();
            return;
        }
        isModified = true;
    }

    //method marks task in list with taskNumber as done.
    public static void markTaskAsDone(int taskNumber) {
        if (taskNumber == INVALID_TASKNUMBER) {
            ui.printInvalidTaskNumber();
            return;
        }
        // mark task with taskNumber as done.
        taskList.get(taskNumber - 1).markAsDone();
        ui.printMarkTaskAsDone(taskList, taskNumber);
        isModified = true;
    }

    //method marks task in list with taskNumber as not yet done.
    public static void unmarkTaskAsDone(int taskNumber) {
        if (taskNumber == INVALID_TASKNUMBER) {
            ui.printInvalidTaskNumber();
            return;
        }
        // mark task with taskNumber as yet to be done.
        taskList.get(taskNumber - 1).unmarkAsDone();
        ui.printUnmarkTaskAsDone(taskList, taskNumber);
        isModified = true;
    }

    public static void deleteTask(int taskNumber) {
        if (taskNumber == INVALID_TASKNUMBER) {
            ui.printInvalidTaskNumber();
            return;
        }
        ui.printDeletedTask(taskList, taskNumber);
        taskList.remove(taskNumber-1);
        isModified = true;
    }


    // load data if data file exists, else create file.
    public static void loadData() {
        storage.loadData(taskList);
    }

    public static void saveData(){
        storage.saveData(taskList);
    }

    public static void printList(){
        ui.printList(taskList);
    }

    public static void printTasksThatMatchWith(String keyword){
        if (keyword.length() == 0){
            ui.printKeywordError();
            return;
        }
        ui.printSelectiveList(taskList, keyword);
    }

    // method runs main echo functionality of duke.
    public static void echo() {
        while (true) {
            isModified = false;

            //read input from user.
            String userInput = ui.getUserInput();

            // Check userInput for respective command.

            if (parser.isByeCommand(userInput)) {
                return;
            } else if (parser.isListCommand(userInput)) {
                printList();
            } else if(parser.isFindCommand(userInput)) {
                printTasksThatMatchWith(parser.getKeyword(userInput));
            } else if (parser.isMarkCommand(userInput)) {
                int taskNumber = parser.getTaskNumber(taskList, userInput);
                markTaskAsDone(taskNumber);
            } else if (parser.isUnmarkCommand(userInput)) {
                int taskNumber = parser.getTaskNumber(taskList, userInput);
                unmarkTaskAsDone(taskNumber);
            } else if (parser.isDeleteCommand(userInput)) {
                int taskNumber = parser.getTaskNumber(taskList, userInput);
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

    public static void greeting(){
        ui.greeting();
    }

    public static void goodbye(){
        ui.goodbye();
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
