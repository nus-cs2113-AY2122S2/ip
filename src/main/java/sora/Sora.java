package sora;

import java.io.IOException;
import tasks.Task;
import tasks.TasksManager;

/**
 * Main 'brains' of Sora. Focuses on taking in user input and passing commands
 * to Sora's relevant components.
 */
public class Sora {
    /**
     * When IN_TESTING_MODE is set to true, certain features of Sora will be limited to
     * improve the automated text UI testing.
     */
    protected static final boolean IN_TESTING_MODE = false;
    private boolean isUserExiting = false;

    private TasksManager tasksManager;
    private SoraUI soraUI;
    private SoraReaderWriter soraReaderWriter;
    private SoraExceptionHandler exceptionHandler;

    public Sora() throws IOException {
        // Instantiate components
        soraUI = new SoraUI();
        tasksManager = new TasksManager();
        soraReaderWriter = new SoraReaderWriter();
        exceptionHandler = new SoraExceptionHandler(soraUI);

        // Greet user
        soraUI.printGreetings();

        // Load saved task list from file
        try {
            soraReaderWriter.loadTaskListFromFile(getTasksManager());
        } catch (IOException e) {
            // Throw to caller method to handle to exit
            throw e;
        }
    }

    protected boolean doesUserWantsToExit() {
        return this.isUserExiting;
    }

    protected void setUserExit() {
        this.isUserExiting = true;
    }

    protected TasksManager getTasksManager() {
        return this.tasksManager;
    }

    public void startContinuousUserPrompt()  throws IOException {
        boolean isFirstPrompt = true;

        while (!doesUserWantsToExit()) {
            // Get user input
            soraUI.printPrompter(isFirstPrompt);
            String userRawInput = soraReaderWriter.getUserInput();
            isFirstPrompt = false;
            soraUI.printLine();

            // Execute command
            try {
                executeCommand(userRawInput);
            } catch (IOException e) {
                // Throw it up to Main class for program termination
                throw e;
            }
        }

        // Bid farewell to user
        soraUI.printGoodbye();
    }

    private void executeCommand(String userRawInput) throws IOException {
        String userCommand = extractCommand(userRawInput);

        try {
            switch (userCommand) {
            case SoraUI.EXIT_COMMAND_KEYWORD:
                setUserExit();
                break;
            case SoraUI.LIST_COMMAND_KEYWORD:
                soraUI.displayTaskList(getTasksManager());
                break;
            case SoraUI.MARK_TASK_AS_DONE_COMMAND_KEYWORD:
                int taskNum = getTaskNumberFromCommand(userRawInput);
                boolean markSuccess = getTasksManager().updateDoneStatus(taskNum, true);
                // Update entire file
                soraReaderWriter.rewriteAllTasksToFile(getTasksManager());
                // Print response
                soraUI.printMarkTaskResponseMessage(markSuccess, getTasksManager(), taskNum);
                break;
            case SoraUI.UNMARK_TASK_AS_DONE_COMMAND_KEYWORD:
                taskNum = getTaskNumberFromCommand(userRawInput);
                boolean unmarkSuccess = getTasksManager().updateDoneStatus(taskNum, false);
                // Update entire file
                soraReaderWriter.rewriteAllTasksToFile(getTasksManager());
                // Print response
                soraUI.printUnmarkTaskResponseMessage(unmarkSuccess, getTasksManager(), taskNum);
                break;
            case SoraUI.DELETE_TASK_COMMAND_KEYWORD:
                taskNum = getTaskNumberFromCommand(userRawInput);
                Task taskRemoved = getTasksManager().deleteTask(taskNum);
                // Update entire file
                soraReaderWriter.rewriteAllTasksToFile(getTasksManager());
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
                soraReaderWriter.writeNewTaskToFile(newTask);
                // Print response
                soraUI.printAddTaskResponseMessage(newTask);
                break;
            default:
                throw new InvalidCommandException(InvalidCommandException.NO_SUCH_COMMAND_MSG);
            }
        } catch (InvalidCommandException e) {
            exceptionHandler.handleInvalidCommandException(e);
        } catch (IOException e) {
            // Throw it up to calling method for program termination
            throw e;
        } catch (ArrayIndexOutOfBoundsException e) {
            exceptionHandler.handleOutOfRangeListReferences();
        }
    }

    private String extractCommand(String userRawInput) {
        String userCommand = userRawInput.toLowerCase().split(" ", 2)[0];
        return userCommand;
    }

    private int getTaskNumberFromCommand(String userRawInput) {
        int taskNum = Integer.parseInt(userRawInput.split(" ")[1]);
        return taskNum;
    }
}
