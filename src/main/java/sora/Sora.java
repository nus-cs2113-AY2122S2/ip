package sora;

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
    private SoraIOHandler soraIOHandler;
    private SoraExceptionHandler exceptionHandler;

    public Sora() {
        // Instantiate components
        soraUI = new SoraUI();
        tasksManager = new TasksManager();
        soraIOHandler = new SoraIOHandler();
        exceptionHandler = new SoraExceptionHandler(soraUI);

        // Greet user
        soraUI.printGreetings();
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

    public void startContinuousUserPrompt() {
        boolean isFirstPrompt = true;

        while (!doesUserWantsToExit()) {
            // Get user input
            soraUI.printPrompter(isFirstPrompt);
            String userRawInput = soraIOHandler.getUserInput();
            isFirstPrompt = false;
            soraUI.printLine();

            // Execute command
            executeCommand(userRawInput);
        }

        // Bid farewell to user
        soraUI.printGoodbye();
    }

    private void executeCommand(String userRawInput) {
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
                soraUI.printMarkTaskResponseMessage(markSuccess, getTasksManager(), taskNum);
                break;
            case SoraUI.UNMARK_TASK_AS_DONE_COMMAND_KEYWORD:
                taskNum = getTaskNumberFromCommand(userRawInput);
                boolean unmarkSuccess = getTasksManager().updateDoneStatus(taskNum, false);
                soraUI.printUnmarkTaskResponseMessage(unmarkSuccess, getTasksManager(), taskNum);
                break;
            case SoraUI.ADD_TODO_COMMAND_KEYWORD:
                // Fallthrough
            case SoraUI.ADD_EVENT_COMMAND_KEYWORD:
                // Fallthrough
            case SoraUI.ADD_DEADLINE_COMMAND_KEYWORD:
                boolean addSuccess = getTasksManager().addTask(userRawInput);
                soraUI.printAddTaskResponseMessage(addSuccess, getTasksManager());
                break;
            default:
                throw new InvalidCommandException(InvalidCommandException.NO_SUCH_COMMAND_MSG);
            }
        } catch (InvalidCommandException e) {
            exceptionHandler.handleInvalidCommandException(e);
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
