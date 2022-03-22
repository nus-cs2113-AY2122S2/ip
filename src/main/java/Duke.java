
public class Duke {
    private boolean userWantsToExit = false;
    TasksManager tasksManager;
    DukeUI dukeUI = new DukeUI();
    DukeReader dukeReader;

    protected Duke() {
        // Instantiate components
        tasksManager = new TasksManager();
        dukeReader = new DukeReader();
        dukeUI.printGreetings();

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
            dukeUI.printPrompter(isFirstPrompt);
            String userRawInput = dukeReader.getUserInput();
            isFirstPrompt = false;
            dukeUI.printLine();

            // Execute command
            executeCommand(userRawInput);
        }

        // Bid farewell to user
        dukeUI.printGoodbye();
    }

        private void executeCommand(String userRawInput) {
            if (userRawInput.equalsIgnoreCase(DukeUI.exit_command)) {
                // Set userWantsToExit to true and return to calling method
                setUserWantsToExit();
            } else if (userRawInput.equalsIgnoreCase(DukeUI.list_command)) {
                // Display the task list
                DukeUI.displayTaskList(tasksManager);
            } else if (userRawInput.toLowerCase().startsWith(DukeUI.mark_command)) {
                // Obtain task number
                int taskNum = Integer.parseInt(userRawInput.split(" ")[1]);
                boolean markSuccess = tasksManager.updateDoneStatus(taskNum, true);

                if (markSuccess) {

                    tasksManager.displayTask(taskNum);
                    System.out.println();
                } else {
                    System.out.println("Oops sorry, I couldn't mark that task as done.");

                }
            } else if (userRawInput.toLowerCase().startsWith(DukeUI.unmark_command)) {
                // Obtain task number
                int taskNum = Integer.parseInt(userRawInput.split(" ")[1]);
                boolean unmarkSuccess = tasksManager.updateDoneStatus(taskNum, false);

                if (unmarkSuccess) {

                    tasksManager.displayTask(taskNum);
                    System.out.println();
                } else {
                    System.out.println("Oops, I couldn't mark that task as not done.");
                    System.out.println("Sorry about that... (-ω-、)");
                }
            } else if (userRawInput.toLowerCase().startsWith(DukeUI.todo_command) ||
                    userRawInput.toLowerCase().startsWith(DukeUI.event_command) ||
                    userRawInput.toLowerCase().startsWith(DukeUI.deadline_command)){
                // Add text to list
                boolean isSuccess = tasksManager.addTask(userRawInput);

                if (isSuccess) {
                    System.out.println("I have added your new task to my list.");
                } else {
                    System.out.println("Oops sorry! Somehow I wasn't able to add your text to my list.");
                }
            } else {
                System.out.println("Oops sorry! I can't understand what you've just typed.");
                System.out.println("Could you try again?");
            }
        }
    }
