public class Sora {
    protected static final boolean IN_TESTING_MODE = false;
    private boolean userWantsToExit = false;

    TasksManager tasksManager;
    SoraUI soraUI = new SoraUI();
    SoraReader soraReader;

    protected Sora() {
        // Instantiate components
        tasksManager = new TasksManager();
        soraReader = new SoraReader();

        // Greet user
        soraUI.printGreetings();
    }

    protected boolean doesUserWantsToExit() {
        return this.userWantsToExit;
    }

    protected void setUserWantsToExit() {
        this.userWantsToExit = true;
    }

    protected void startContinuousUserPrompt() {
        boolean isFirstPrompt = true;

        while (!doesUserWantsToExit()) {
            // Get user input
            soraUI.printPrompter(isFirstPrompt);
            String userRawInput = soraReader.getUserInput();
            isFirstPrompt = false;
            soraUI.printLine();

            // Execute command
            executeCommand(userRawInput);
        }

        // Bid farewell to user
        soraUI.printGoodbye();
    }

    private void executeCommand(String userRawInput) {
        if (userRawInput.equalsIgnoreCase(SoraUI.EXIT_COMMAND_KEYWORD)) {
            // Set userWantsToExit to true and return to calling method
            setUserWantsToExit();
        } else if (userRawInput.equalsIgnoreCase(SoraUI.LIST_COMMAND_KEYWORD)) {
            // Display the task list
            soraUI.displayTaskList(tasksManager);
        } else if (userRawInput.toLowerCase().startsWith(SoraUI.MARK_TASK_AS_DONE_COMMAND_KEYWORD)) {
            // Obtain task number
            int taskNum = Integer.parseInt(userRawInput.split(" ")[1]);
            boolean markSuccess = tasksManager.updateDoneStatus(taskNum, true);

            if (markSuccess) {
                System.out.println(soraUI.getRandomAcknowledgement()
                        + ", I've marked this task as done:");
                System.out.println();
                tasksManager.displayTask(taskNum);
                System.out.println();
            } else {
                System.out.println("Oops, I couldn't mark that task as done.");
                System.out.println("Sorry about that... (-ω-、)");
            }
        } else if (userRawInput.toLowerCase().startsWith(SoraUI.UNMARK_TASK_AS_DONE_COMMAND_KEYWORD)) {
            // Obtain task number
            int taskNum = Integer.parseInt(userRawInput.split(" ")[1]);
            boolean unmarkSuccess = tasksManager.updateDoneStatus(taskNum, false);

            if (unmarkSuccess) {
                System.out.println(soraUI.getRandomAcknowledgement()
                        + ", I've marked this task as not done:");
                System.out.println();
                tasksManager.displayTask(taskNum);
                System.out.println();
            } else {
                System.out.println("Oops, I couldn't mark that task as not done.");
                System.out.println("Sorry about that... (-ω-、)");
            }
        } else if (userRawInput.toLowerCase().startsWith(SoraUI.ADD_TODO_COMMAND_KEYWORD) ||
                userRawInput.toLowerCase().startsWith(SoraUI.ADD_EVENT_COMMAND_KEYWORD) ||
                userRawInput.toLowerCase().startsWith(SoraUI.ADD_DEADLINE_COMMAND_KEYWORD)){
            // Add text to list
            boolean addSuccess = tasksManager.addTask(userRawInput);

            if (addSuccess) {
                System.out.println(soraUI.getRandomAcknowledgement()
                        + ", I have added your new task to my list.");
            } else {
                System.out.println("Oops! Somehow I wasn't able to add your text to my list...");
                System.out.println("Sorry about that! (-ω-、)");
            }
        } else {
            System.out.println("Oops! I can't understand what you've just typed...");
            System.out.println("Could you try again?");
        }
    }
}
