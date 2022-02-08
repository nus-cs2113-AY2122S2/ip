package tasks;

import sora.SoraUI;
import sora.InvalidCommandException;
import util.Helper;

import java.util.ArrayList;

public class TasksManager {
    private static final int EVENT_DEADLINE_TASK_NUMBER_OF_PARAMETERS = 2;

    private ArrayList<Task> list;
    private int numberOfTasks;

    public TasksManager() {
        this.list = new ArrayList<Task>();
        this.numberOfTasks = 0;
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int getNumberOfTasks() {
        return this.numberOfTasks;
    }

    public void setNumberOfTasks(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }

    public void incrementNumberOfTasks() {
        this.numberOfTasks += 1;
    }

    public boolean addTask(String userInput) throws InvalidCommandException {
        // Create new Task object with text
        boolean isTaskAdded;
        String taskType = extractTaskType(userInput);

        try {
            switch (taskType) {
            case SoraUI.ADD_TODO_COMMAND_KEYWORD:
                checkTodoCommand(userInput);
                String todoDescription = removeCommandKeyword(userInput);
                isTaskAdded = list.add(new Todo(todoDescription));
                break;
            case SoraUI.ADD_EVENT_COMMAND_KEYWORD:
                checkEventCommand(userInput);
                String[] eventDescriptionAndDate = extractDescriptionAndDate(userInput, SoraUI.ADD_EVENT_FLAG_KEYWORD);
                isTaskAdded = list.add(new Event(eventDescriptionAndDate));
                break;
            case SoraUI.ADD_DEADLINE_COMMAND_KEYWORD:
                checkDeadlineCommand(userInput);
                String[] deadlineDescriptionAndDate = extractDescriptionAndDate(userInput, SoraUI.ADD_DEADLINE_FLAG_KEYWORD);
                isTaskAdded = list.add(new Deadline(deadlineDescriptionAndDate));
                break;
            default:
                isTaskAdded = false;
            }
        } catch (InvalidCommandException e) {
            // Rethrow it to caller method
            throw e;
        }

        if (isTaskAdded) {
            incrementNumberOfTasks();
            return true;
        }

        return false;
    }

    private void checkTodoCommand(String todoUserInput) throws InvalidCommandException {
        // Check if user command has a description
        String[] commandAndDescription = todoUserInput.split(" ", 2);

        if (commandAndDescription.length < 2) {
            // User input has no description
            throw new InvalidCommandException(InvalidCommandException.TODO_NO_DESCRIPTION);
        }

        // Checks are complete, the command is okay. Method will return to caller.
    }

    private void checkEventCommand(String eventUserInput) throws InvalidCommandException {
        // Check if user input has a description
        String[] splitByFlag = eventUserInput.split("/at");
        String[] commandAndDescription = splitByFlag[0].split(" ", 2);

        if (commandAndDescription.length < 2) {
            // User input has no description
            throw new InvalidCommandException(InvalidCommandException.EVENT_NO_DESCRIPTION);
        }

        // Check if user input has the correct flag
        if (!eventUserInput.contains("/at")) {
            throw new InvalidCommandException(InvalidCommandException.EVENT_MISSING_FLAG);
        }

        // Check if user input has a date period
        if (splitByFlag.length < 2) {
            // User input has no date period
            throw new InvalidCommandException(InvalidCommandException.EVENT_NO_PERIOD);
        }

        // Check if user input has multiple '/at' flags
        boolean multipleAtFlagOccurrences = Helper.checkMultipleOccurrences(eventUserInput, "/at");

        if (multipleAtFlagOccurrences) {
            // User input has too many 'at' flags
            throw new InvalidCommandException(InvalidCommandException.EVENT_INVALID_FLAGS);
        }

        // Check if user input has extraneous flags
        if (checkForExtraneousFlags(eventUserInput, SoraUI.ADD_EVENT_FLAG_KEYWORD)) {
            // User input contains extraneous flags
            throw new InvalidCommandException(InvalidCommandException.EVENT_INVALID_FLAGS);
        }



        // Checks are complete, the command is okay. Method will return to caller.
    }

    private void checkDeadlineCommand(String deadlineUserInput) throws InvalidCommandException {
        // TODO Implement checks for deadline command
    }

    /**
     * (WIP) This method checks for against a list of flags to check if a given command contains
     * a flag that is not the specified correct flag.
     *
     * @param userInput
     * @param correctFlag
     * @return Returns true if there is at least one flag that is invalid. Returns false otherwise.
     */
    private boolean checkForExtraneousFlags(String userInput, String correctFlag) {
        String[] arrayOfWords = userInput.split(" ");

        // Go through all possible flags
        for (String flag : SoraUI.LIST_OF_FLAG_KEYWORDS) {
            if (flag.equals(correctFlag)) {
                // Ignore the flag that is marked as the correct flag
                continue;
            }

            for (String word : arrayOfWords) {
                if (word.contains(flag)) {
                    // userInput contains an invalid flag
                    return true;
                }
            }
        }

        return false;
    }

    private String extractTaskType(String text) {
        String taskType = text.toLowerCase().split(" ", 2)[0];
        return taskType;
    }

    private String removeCommandKeyword(String text) {
        String description = text.split(" ", 2)[1];
        return description;
    }

    private String[] extractDescriptionAndDate(String text, String flagKeyword) {
        String taskDescriptionAndDateString = removeCommandKeyword(text);
        String[] taskDescriptionAndDate = new String[EVENT_DEADLINE_TASK_NUMBER_OF_PARAMETERS];

        for (int i = 0; i < taskDescriptionAndDate.length; i += 1) {
            taskDescriptionAndDate[i] = taskDescriptionAndDateString.split(flagKeyword)[i].trim();
        }

        return taskDescriptionAndDate;
    }

    public boolean updateDoneStatus(int taskNum, boolean status) {
        // Marks a task as either done or not done
        if (isEmpty()) {
            return false;
        }

        // Calculate index number of task in the ArrayList
        int indexNum = taskNum - 1;

        // Update the task status
        getList().get(indexNum).setDone(status);
        return true;
    }

    public void displayTask(int taskNum) {
        if (isEmpty()) {
            return;
        }

        int taskIndex = taskNum - 1;
        Task taskToDisplay = getList().get(taskIndex);
        System.out.println("\t" + taskToDisplay.toString());
    }

    public void displayLastAddedTask() {
        if (isEmpty()) {
            return;
        }

        int taskIndex = getNumberOfTasks() - 1;
        Task taskToDisplay = getList().get(taskIndex);
        System.out.println("\t" + taskToDisplay.toString());
    }

    public void displayAllTasks() {
        if (isEmpty()) {
            return;
        }

        for (int i = 0; i < getList().size(); i += 1) {
            System.out.println("\t" + (i + 1) + "." + getList().get(i).toString());
        }
    }
}
