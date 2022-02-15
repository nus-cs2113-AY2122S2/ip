package tasks;

import sora.SoraReaderWriter;
import sora.SoraUI;
import sora.InvalidCommandException;
import util.Helper;

import java.util.ArrayList;

public class TasksManager {
    private static final int EVENT_DEADLINE_TASK_NUMBER_OF_PARAMETERS = 2;
    public static final int FILE_DATA_TASK_TYPE_INDEX_NUM = 0;
    public static final int FILE_DATA_DESCRIPTION_INDEX_NUM = 2;
    public static final int FILE_DATA_FLAG_VALUE_INDEX_NUM = 3;

    private ArrayList<Task> list;
    private int numberOfTasks;
    SoraReaderWriter soraReaderWriter;

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

    public Task addTask(String userInput) throws InvalidCommandException {
        // Create new Task object with text
        Task newTask = null;
        String taskType = extractTaskType(userInput);

        try {
            switch (taskType) {
            case SoraUI.ADD_TODO_COMMAND_KEYWORD:
                checkTodoCommand(userInput);
                String todoDescription = removeCommandKeyword(userInput);
                newTask = new Todo(todoDescription);
                list.add(newTask);
                break;
            case SoraUI.ADD_EVENT_COMMAND_KEYWORD:
                checkEventDeadlineCommand(userInput);
                String[] eventDescriptionAndDate = extractDescriptionAndDate(userInput, SoraUI.ADD_EVENT_FLAG_KEYWORD);
                newTask = new Event(eventDescriptionAndDate);
                list.add(newTask);
                break;
            case SoraUI.ADD_DEADLINE_COMMAND_KEYWORD:
                checkEventDeadlineCommand(userInput);
                String[] deadlineDescriptionAndDate = extractDescriptionAndDate(userInput, SoraUI.ADD_DEADLINE_FLAG_KEYWORD);
                newTask = new Deadline(deadlineDescriptionAndDate);
                list.add(newTask);
                break;
            default:
                // TODO: Implement exception?
            }
        } catch (InvalidCommandException e) {
            // Rethrow it to caller method
            throw e;
        }

        incrementNumberOfTasks();

        return newTask;
    }

    /**
     * (WIP Documentation) Specially meant to handle tasks being added from saved data file
     *
     * @param taskDataFromFile
     * @return
     */
    public void addTask(String[] taskDataFromFile) {
        String type;
        String description;
        String flag;
        String flagValue;
        String isDoneValue = taskDataFromFile[1];
        boolean isDone = (isDoneValue.equals("1")) ? true : false;

        // Get details of task
        type = getTaskType(taskDataFromFile[FILE_DATA_TASK_TYPE_INDEX_NUM]);
        description = taskDataFromFile[FILE_DATA_DESCRIPTION_INDEX_NUM];

        if (!type.equalsIgnoreCase(SoraUI.ADD_TODO_COMMAND_KEYWORD)) {
            flag = getFlag(type);
            flagValue = taskDataFromFile[FILE_DATA_FLAG_VALUE_INDEX_NUM];
        } else {
            flag = "";
            flagValue = "";
        }

        // Craft the command to add task to list
        StringBuilder commandBuilder = new StringBuilder(type + " " + description);

        if (!type.equalsIgnoreCase(SoraUI.ADD_TODO_COMMAND_KEYWORD)) {
            // Include flag and flag value
            commandBuilder.append(flag + " " + flagValue);
        }

        String newTaskCommand = commandBuilder.toString();

        // Add the task to the list
        try {
            addTask(newTaskCommand);
        } catch (InvalidCommandException e) {
            System.out.println("Oh no! I failed to add a task from the saved data file to my task list.");
            System.out.println("Here's some details about the error: ");
            System.out.println("\t" + e.getMessage());

            // TODO: Refine exception handling
        }

        // If task is marked as done in the file, reflect it in Sora's task list
        if (isDone) {
            updateDoneStatus(getNumberOfTasks(), true);
        }
    }

    private String getTaskType(String abbreviation) {
        switch (abbreviation) {
        case SoraReaderWriter.TODO_TYPE_FILE_ABBREVIATION:
            return SoraUI.ADD_TODO_COMMAND_KEYWORD;
        case SoraReaderWriter.EVENT_TYPE_FILE_ABBREVIATION:
            return SoraUI.ADD_EVENT_COMMAND_KEYWORD;
        case SoraReaderWriter.DEADLINE_TYPE_FILE_ABBREVIATION:
            return SoraUI.ADD_DEADLINE_COMMAND_KEYWORD;
        default:
            // TODO: Implement exception?
            return "";
        }
    }

    private String getFlag(String taskType) {
        switch (taskType) {
        case SoraUI.ADD_EVENT_COMMAND_KEYWORD:
            return SoraUI.ADD_EVENT_FLAG_KEYWORD;
        case SoraUI.ADD_DEADLINE_COMMAND_KEYWORD:
            return SoraUI.ADD_DEADLINE_FLAG_KEYWORD;
        default:
            // TODO: Implement exception?
            return "";
        }
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

    private void checkEventDeadlineCommand(String eventDeadlineUserInput) throws InvalidCommandException {
        // Check if user input has a description
        String taskType = eventDeadlineUserInput.split(" ")[0];
        String commandFlagKeyword = getDeadlineOrEventFlag(taskType);
        String[] splitByFlag = eventDeadlineUserInput.split(commandFlagKeyword);
        String[] commandAndDescription = splitByFlag[0].split(" ", 2);

        if (commandAndDescription.length < 2) {
            // User input has no description
            String noDescriptionExceptionMsg = getDeadlineOrEventNoDescriptionExceptionMsg(taskType);
            throw new InvalidCommandException(noDescriptionExceptionMsg);
        }

        // Check if user input has the correct flag
        if (!eventDeadlineUserInput.contains(commandFlagKeyword)) {
            String missingFlagExceptionMsg = getDeadlineOrEventNoFlagExceptionMsg(taskType);
            throw new InvalidCommandException(missingFlagExceptionMsg);
        }

        // Check if user input has a date period
        if (splitByFlag.length < 2) {
            // User input has no date period
            String noDateExceptionMsg = getDeadlineOrEventNoDateExceptionMsg(taskType);
            throw new InvalidCommandException(noDateExceptionMsg);
        }

        // Check if user input has multiple '/at' flags
        boolean multipleAtFlagOccurrences = Helper.checkMultipleOccurrences(eventDeadlineUserInput, commandFlagKeyword);
        String invalidFlagsExceptionMsg = getDeadlineOrEventInvalidFlagExceptionMsg(taskType);

        if (multipleAtFlagOccurrences) {
            // User input has too many 'at' flags
            throw new InvalidCommandException(invalidFlagsExceptionMsg);
        }

        // Check if user input has extraneous flags
        if (checkForExtraneousFlags(eventDeadlineUserInput, commandFlagKeyword)) {
            // User input contains extraneous flags
            throw new InvalidCommandException(invalidFlagsExceptionMsg);
        }

        // Checks are complete, the command is okay. Method will return to caller.
    }

    private String getDeadlineOrEventFlag(String taskType) {
        if (taskType.equalsIgnoreCase(SoraUI.ADD_EVENT_COMMAND_KEYWORD)) {
            return SoraUI.ADD_EVENT_FLAG_KEYWORD;
        } else {
            return SoraUI.ADD_DEADLINE_FLAG_KEYWORD;
        }
    }

    private String getDeadlineOrEventNoDescriptionExceptionMsg(String taskType) {
        if (taskType.equalsIgnoreCase(SoraUI.ADD_EVENT_COMMAND_KEYWORD)) {
            return InvalidCommandException.EVENT_NO_DESCRIPTION;
        } else {
            return InvalidCommandException.DEADLINE_NO_DESCRIPTION;
        }
    }

    private String getDeadlineOrEventNoFlagExceptionMsg(String taskType) {
        if (taskType.equalsIgnoreCase(SoraUI.ADD_EVENT_COMMAND_KEYWORD)) {
            return InvalidCommandException.EVENT_MISSING_FLAG;
        } else {
            return InvalidCommandException.DEADLINE_MISSING_FLAG;
        }
    }

    private String getDeadlineOrEventNoDateExceptionMsg(String taskType) {
        if (taskType.equalsIgnoreCase(SoraUI.ADD_EVENT_COMMAND_KEYWORD)) {
            return InvalidCommandException.EVENT_NO_PERIOD;
        } else {
            return InvalidCommandException.DEADLINE_NO_DUE_DATE;
        }
    }

    private String getDeadlineOrEventInvalidFlagExceptionMsg(String taskType) {
        if (taskType.equalsIgnoreCase(SoraUI.ADD_EVENT_COMMAND_KEYWORD)) {
            return InvalidCommandException.EVENT_INVALID_FLAGS;
        } else {
            return InvalidCommandException.DEADLINE_INVALID_FLAGS;
        }
    }

    /**
     * (WIP Documentation) This method checks for against a list of flags to check if a given command contains
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

    /**
     * (WIP) Marks a task as either done or not done.
     *
     * @param taskNum
     * @param status
     * @return
     */
    public boolean updateDoneStatus(int taskNum, boolean status) {
        if (isEmpty()) {
            // TODO: throw EmptyListException instead
            return false;
        }

        // Calculate index number of task in the ArrayList
        int indexNum = taskNum - 1;

        // Update the task status
        getList().get(indexNum).setDone(status);
        return true;
    }

    public Task deleteTask(int taskNum) {
        if (isEmpty()) {
            // TODO: throw EmptyListException instead
        }

        // Calculate index number of task in the ArrayList
        int indexNum = taskNum - 1;

        // Remove the task from the list
        Task taskRemoved = getList().remove(indexNum);
        // TODO: Consider handling scenario where task removal was unsuccessful?
        return taskRemoved;
    }

    public void displayTask(int taskNum) {
        if (isEmpty()) {
            // TODO: throw EmptyListException instead
            return;
        }

        int taskIndex = taskNum - 1;
        Task taskToDisplay = getList().get(taskIndex);
        System.out.println("\t" + taskToDisplay.toString());
    }

    public void displayTask(Task taskObject) {
        System.out.println("\t" + taskObject.toString());
    }

    public void displayLastAddedTask() {
        if (isEmpty()) {
            // TODO: throw EmptyListException instead
            return;
        }

        int taskIndex = getNumberOfTasks() - 1;
        Task taskToDisplay = getList().get(taskIndex);
        System.out.println("\t" + taskToDisplay.toString());
    }

    public void displayAllTasks() {
        if (isEmpty()) {
            // TODO: throw EmptyListException instead
            return;
        }

        for (int i = 0; i < getList().size(); i += 1) {
            System.out.println("\t" + (i + 1) + "." + getList().get(i).toString());
        }
    }
}
