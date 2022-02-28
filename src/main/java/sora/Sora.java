package sora;

import java.io.IOException;
import java.util.ArrayList;
import java.time.format.DateTimeParseException;

import tasks.EmptyListException;
import tasks.Task;
import tasks.TaskList;

/**
 * The main 'brains' of Sora. Focuses on passing the user's input to her relevant components.
 */
public class Sora {
    /**
     * When IN_TESTING_MODE is set to true, certain features of Sora will be limited to
     * improve the reliability of automated text UI testing.
     */
    protected static final boolean IN_TESTING_MODE = false;
    private boolean isUserExiting = false;

    private TaskList taskList;
    private SoraUI soraUI;
    private SoraParser soraParser;
    private SoraStorage soraStorage;
    private SoraExceptionHandler soraExceptionHandler;

    /**
     * Initialises the various components of Sora, greets the user, and loads the tasks from the storage
     * file (if the storage file exists).
     *
     * @throws IOException If the user's command involves reading and/or writing to the storage file and
     * Sora is unable to open the file.
     */
    public Sora() throws IOException {
        // Instantiate components
        taskList = new TaskList();
        soraUI = new SoraUI();
        soraParser = new SoraParser();
        soraStorage = new SoraStorage();
        soraExceptionHandler = new SoraExceptionHandler(soraUI);

        // Greet user
        soraUI.printGreetings();

        // Load saved task list from file
        try {
            soraStorage.loadTaskListFromFile(getTasksManager());
        } catch (IOException e) {
            // Throw to caller method to handle to exit
            soraUI.printIOExceptionRethrowMessage("Sora", "Main");
            throw e;
        }
    }

    /**
     * Gets the isUserExiting field
     *
     * @return The isUserExiting field
     */
    protected boolean doesUserWantsToExit() {
        return this.isUserExiting;
    }

    /**
     * Sets the isUserExiting field's boolean value to be true.
     */
    protected void setUserExit() {
        this.isUserExiting = true;
    }

    /**
     * Returns the taskList field, which contains an instance of the taskList class.
     *
     * @return
     */
    protected TaskList getTasksManager() {
        return this.taskList;
    }

    /**
     * Prompts the user for an input and passes it to executeCommand method for command execution.
     *
     * @throws IOException If the user's command involves writing to the storage file and Sora is unable
     * to open the file.
     */
    public void startContinuousUserPrompt() throws IOException {
        boolean isFirstPrompt = true;

        while (!doesUserWantsToExit()) {
            // Get user input
            String userRawInput = "";

            soraUI.printPrompter(isFirstPrompt);

            try {
                userRawInput = soraParser.getUserInput();
            } catch (IllegalCharacterException e) {
                soraUI.printLine();
                soraExceptionHandler.handleIllegalCharacterException();
                continue;
            } finally {
                isFirstPrompt = false;
            }

            soraUI.printLine();

            // Execute command
            try {
                executeCommand(userRawInput);
            } catch (IOException e) {
                // Throw it up to Main class for program termination
                soraUI.printIOExceptionRethrowMessage("Sora", "Main");
                throw e;
            }
        }

        // Bid farewell to user
        soraUI.printGoodbye();
    }

    /**
     * Takes in the user's input, processes it with SoraParser class and determines the appropriate courses
     * of actions to take on the command.
     *
     * @param userRawInput The user's input.
     * @throws IOException If the user's command involves writing to the storage file and Sora is unable
     * to open the file.
     */
    private void executeCommand(String userRawInput) throws IOException {
        String userCommand = soraParser.extractCommand(userRawInput);

        try {
            switch (userCommand) {
            case SoraUI.EXIT_COMMAND_KEYWORD:
                setUserExit();
                break;
            case SoraUI.LIST_COMMAND_KEYWORD:
                soraUI.printTaskList(getTasksManager());
                break;
            case SoraUI.FIND_COMMAND_KEYWORD:
                String searchString = soraParser.getSearchString(userRawInput);
                ArrayList<String> searchResult = taskList.searchTasks(searchString);
                soraUI.printSearchResults(searchResult);
                break;
            case SoraUI.MARK_TASK_AS_DONE_COMMAND_KEYWORD:
                int taskNum = soraParser.getTaskNumberFromCommand(userRawInput);
                boolean markSuccess = getTasksManager().updateDoneStatus(taskNum, true);
                // Update entire file
                soraStorage.rewriteAllTasksToFile(getTasksManager());
                // Print response
                soraUI.printMarkTaskResponseMessage(markSuccess, getTasksManager(), taskNum);
                break;
            case SoraUI.UNMARK_TASK_AS_DONE_COMMAND_KEYWORD:
                taskNum = soraParser.getTaskNumberFromCommand(userRawInput);
                boolean unmarkSuccess = getTasksManager().updateDoneStatus(taskNum, false);
                // Update entire file
                soraStorage.rewriteAllTasksToFile(getTasksManager());
                // Print response
                soraUI.printUnmarkTaskResponseMessage(unmarkSuccess, getTasksManager(), taskNum);
                break;
            case SoraUI.DELETE_TASK_COMMAND_KEYWORD:
                taskNum = soraParser.getTaskNumberFromCommand(userRawInput);
                Task taskRemoved = getTasksManager().deleteTask(taskNum);
                // Update entire file
                soraStorage.rewriteAllTasksToFile(getTasksManager());
                // Print response
                soraUI.printDeleteTaskResponseMessage(taskRemoved, getTasksManager());
                break;
            case SoraUI.ADD_TODO_COMMAND_KEYWORD:
                // Fallthrough
            case SoraUI.ADD_EVENT_COMMAND_KEYWORD:
                // Fallthrough
            case SoraUI.ADD_DEADLINE_COMMAND_KEYWORD:
                Task newTask = getTasksManager().addTask(userRawInput);
                // Update file
                soraStorage.writeNewTaskToFile(newTask);
                // Print response
                soraUI.printAddTaskResponseMessage(newTask);
                break;
            default:
                throw new InvalidCommandException(InvalidCommandException.NO_SUCH_COMMAND_MSG);
            }
        } catch (InvalidCommandException e) {
            soraExceptionHandler.handleInvalidCommandException(e);
        } catch (DateTimeParseException e) {
            soraExceptionHandler.handleInvalidDateTimeInputFormat();
        } catch (IOException e) {
            // Throw it up to calling method for program termination
            soraUI.printIOExceptionRethrowMessage("Sora", "Main");
            throw e;
        } catch (ArrayIndexOutOfBoundsException e) {
            soraExceptionHandler.handleOutOfRangeListReferences();
        } catch (EmptyListException e) {
            soraExceptionHandler.handleEmptyListException();
        } catch (NumberFormatException e) {
            soraExceptionHandler.handleInvalidTaskNumber();
        }
    }
}
