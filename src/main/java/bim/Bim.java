package bim;

import bim.task.Deadline;
import bim.task.Event;
import bim.task.Task;
import bim.task.ToDo;
import bim.task.TaskList;

import java.util.ArrayList;

public class Bim {

    private final Ui ui;
    private final Parser parser;
    private final Storage storage;

    private ArrayList<Task> taskStore = new ArrayList<Task>();

    private static final String DELIMITER_EVENT = " /at ";
    private static final String DELIMITER_DEADLINE = " /by ";

    private static final String ERROR_COMMAND = "I didn't understand that!";

    private static final String OP_MARK = "mark";
    private static final String OP_UNMARK = "unmark";
    private static final String OP_ADD_DEADLINE = "deadline";
    private static final String OP_ADD_TODO = "todo";
    private static final String OP_ADD_EVENT = "event";
    private static final String OP_EXIT_PROGRAM = "bye";
    private static final String OP_LIST_TASK = "list";
    private static final String OP_DELETE_TASK = "delete";

    private static final String LINE_INDENT = "\t";

    public Bim() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage();
    }

    public void run() {
        ui.printWelcomeMessage();

        try {
            taskStore = storage.loadDataFile();
        } catch (BimException exception) {
            ui.printErrorInDataFile();
        }

        boolean isExit = false;
        while(!isExit) {
            String fullCommand = parser.readInput();
            String command = parser.parseCommand(fullCommand);
            String commandArg = parser.parseCommandArg(fullCommand);
            ui.printLineSeparator();
            try {
                isExit = handleCommand(command, commandArg);
            } catch (BimException exception) {
                ui.printErrorMessage(exception.getDescription());
            }
            ui.printLineSeparator();
        }
        exitProgram();
    }

    private boolean handleCommand(String command, String commandArg) throws BimException {
        switch (command) {
        case OP_LIST_TASK:
            ui.printTaskList(taskStore);
            break;
        case OP_MARK:
            modifyTask(OP_MARK, commandArg);
            break;
        case OP_UNMARK:
            modifyTask(OP_UNMARK, commandArg);
            break;
        case OP_ADD_TODO:
            addToDo(commandArg);
            break;
        case OP_ADD_DEADLINE:
            addDeadline(commandArg);
            break;
        case OP_ADD_EVENT:
            addEvent(commandArg);
            break;
        case OP_DELETE_TASK:
            deleteTask(commandArg);
            break;
        case OP_EXIT_PROGRAM:
            return true;
        default:
            throw new BimException(ERROR_COMMAND);
        }
        return false;
    }

    private void exitProgram() {
        ui.printExitMessage();
    }

    private void addEvent(String commandArg) {
        if (!parser.isValidArgument(DELIMITER_EVENT, commandArg)) {
            ui.printErrorInCommand();
            return;
        }
        String[] parsedTokens = commandArg.split(DELIMITER_EVENT);
        String eventDescription = parsedTokens[0];
        String eventDate = parsedTokens[1];
        Event newEvent = new Event(eventDescription, eventDate);

        if (addToFile(newEvent)) {
            taskStore.add(newEvent);
            ui.printAddTaskMessage(newEvent.toString(), taskStore.size());
        }
    }

    private void addToDo(String commandArg) {
        if (!parser.isValidArgument(commandArg)) {
            ui.printErrorInCommand();
            return;
        }
        ToDo newToDo = new ToDo(commandArg);

        if (addToFile(newToDo)) {
            taskStore.add(newToDo);
            ui.printAddTaskMessage(newToDo.toString(), taskStore.size());
        }
    }

    private void addDeadline(String commandArg) {
        if (!parser.isValidArgument(DELIMITER_DEADLINE, commandArg)) {
            ui.printErrorInCommand();
            return;
        }
        String[] parsedTokens = commandArg.split(DELIMITER_DEADLINE);
        String deadlineDescription = parsedTokens[0];
        String deadlineDate = parsedTokens[1];
        Deadline newDeadline = new Deadline(deadlineDescription, deadlineDate);
        if (addToFile(newDeadline)) {
            taskStore.add(newDeadline);
            ui.printAddTaskMessage(newDeadline.toString(), taskStore.size());
        }
    }

    private boolean addToFile(Task newTask) {
        try {
            storage.writeData(newTask);
            return true;
        } catch (BimException exception) {
            ui.printErrorMessage(exception.getDescription());
        }
        return false;
    }

    private void modifyTask(String mode, String commandArg) throws BimException {
        int index = parser.parseIndex(commandArg);
        if (!parser.isValidIndex(taskStore.size(), index)) {
            ui.printErrorInIndex();
            return;
        }

        try {
            Task currentTask = taskStore.get(index);
            storage.modifyData(mode, index);
            if (mode.equals(OP_MARK)) {
                currentTask.setAsDone();
                ui.printMarkTaskMessage();
            } else {
                currentTask.setAsNotDone();
                ui.printUnmarkTaskMessage();
            }
            ui.printTask(currentTask);
        } catch (BimException exception) {
            ui.printErrorMessage(exception.getDescription());
        }
    }

    private void deleteTask(String commandArg) {
        int index = parser.parseIndex(commandArg);
        if (!parser.isValidIndex(taskStore.size(), index)) {
            ui.printErrorInIndex();
            return;
        }
        try {
            String taskInfo = taskStore.get(index).toString();
            taskStore.remove(index);
            storage.deleteData(index);
            ui.printDeleteMessage(taskInfo, taskStore.size());
        } catch (BimException exception) {
            ui.printErrorMessage(exception.getDescription());
        }
    }

    public static void main(String[] args) {
        new Bim().run();
    }

}
