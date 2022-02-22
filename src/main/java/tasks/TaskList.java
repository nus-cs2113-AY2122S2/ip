package tasks;

import sora.SoraStorage;
import sora.SoraUI;
import sora.InvalidCommandException;
import util.Helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * This class is an implementation for a way for Sora to store tasks in a centralised data structure.
 */
public class TaskList {
    private static final int EVENT_DEADLINE_TASK_NUMBER_OF_PARAMETERS = 2;
    private static final int EVENT_DEADLINE_TASK_DESCRIPTION_INDEX_NUM = 0;
    private static final int EVENT_DEADLINE_TASK_DATE_INDEX_NUM = 1;

    public static final int FILE_DATA_TASK_TYPE_INDEX_NUM = 0;
    public static final int FILE_DATA_DESCRIPTION_INDEX_NUM = 2;
    public static final int FILE_DATA_FLAG_VALUE_INDEX_NUM = 3;

    private ArrayList<Task> list;
    private int numberOfTasks;
    SoraStorage soraStorage;

    /**
     * Constructs a TaskList instance, and instantiates an empty ArrayList<Task>.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
        this.numberOfTasks = 0;
    }

    /**
     * Gets the ArrayList list containing the tasks added to Sora.
     *
     * @return The ArrayList list.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Determines if the list is empty. That is, there are no tasks in it.
     *
     * @return true if the list is empty. Otherwise, false is returned.
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Gets the variable that stores the number of tasks currently stored in the list.
     *
     * @return The number of tasks currently stored in the list.
     */
    public int getNumberOfTasks() {
        return this.numberOfTasks;
    }

    /**
     * Sets the variable that stores the number of tasks stored in the list to a specified number.
     *
     * @param numberOfTasks The new number of tasks that the list has.
     */
    public void setNumberOfTasks(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }

    /**
     * Increases the number stored in the variable that stores the number of tasks in the list by 1.
     */
    public void incrementNumberOfTasks() {
        this.numberOfTasks += 1;
    }

    /**
     * Adds a new task to the task list. Depending on the type of task the user has entered, different checks
     * and procedures will be carried out to ensure that the user's input follows the correct formatting rules
     * before it is added to the task list.
     *
     * @param userInput The user's input containing the new task information to be added.
     * @return A new Task instance that contains the information that the user has provided. However, if an error
     * has occurred (e.g. due to failing formatting checks), null will be returned.
     * @throws InvalidCommandException If userInput fails the formatting checks.
     * @throws DateTimeParseException If the date and time formatting in the user's input is incorrect.
     */
    public Task addTask(String userInput) throws InvalidCommandException, DateTimeParseException {
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
                String[] eventDescriptionAndDate = extractDescriptionAndDate(userInput,
                        SoraUI.ADD_EVENT_FLAG_KEYWORD);
                LocalDateTime eventDateTime =
                        getDateTimeObject(eventDescriptionAndDate[EVENT_DEADLINE_TASK_DATE_INDEX_NUM]);
                newTask = new Event(eventDescriptionAndDate[EVENT_DEADLINE_TASK_DESCRIPTION_INDEX_NUM],
                        eventDateTime);
                list.add(newTask);
                break;
            case SoraUI.ADD_DEADLINE_COMMAND_KEYWORD:
                checkEventDeadlineCommand(userInput);
                String[] deadlineDescriptionAndDate = extractDescriptionAndDate(userInput,
                        SoraUI.ADD_DEADLINE_FLAG_KEYWORD);
                LocalDateTime deadlineDateTime =
                        getDateTimeObject(deadlineDescriptionAndDate[EVENT_DEADLINE_TASK_DATE_INDEX_NUM]);
                newTask = new Deadline(deadlineDescriptionAndDate[EVENT_DEADLINE_TASK_DESCRIPTION_INDEX_NUM],
                        deadlineDateTime);
                list.add(newTask);
                break;
            default:
                throw new InvalidCommandException(InvalidCommandException.NO_SUCH_COMMAND_MSG);
            }
        } catch (InvalidCommandException e) {
            // Re-throw it to caller method
            throw e;
        } catch (DateTimeParseException e) {
            // Re-throw it to caller method
            throw e;
        }

        incrementNumberOfTasks();

        return newTask;
    }

    /**
     * Adds a new task that was read from a file in the user's system storage.
     *
     * @param taskDataFromFile A string array containing the information for the new task.
     */
    public void addTaskFromFile(String[] taskDataFromFile) {
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

    /**
     * Returns the command keyword based on the provided abbreviation.
     * <ul>
     *     <li>"D" = "deadline"</li>
     *     <li>"E" = "event"</li>
     *     <li>"T" = "todo"</li>
     * </ul>
     *
     * @param abbreviation The abbreviated task type.
     * @return The command keyword based on the specified task type abbreviation.
     */
    private String getTaskType(String abbreviation) {
        switch (abbreviation) {
        case SoraStorage.TODO_TYPE_FILE_ABBREVIATION:
            return SoraUI.ADD_TODO_COMMAND_KEYWORD;
        case SoraStorage.EVENT_TYPE_FILE_ABBREVIATION:
            return SoraUI.ADD_EVENT_COMMAND_KEYWORD;
        case SoraStorage.DEADLINE_TYPE_FILE_ABBREVIATION:
            return SoraUI.ADD_DEADLINE_COMMAND_KEYWORD;
        default:
            // TODO: Implement exception?
            return "";
        }
    }

    /**
     * Returns the flags required by the command based on the specified type of task.
     *
     * @param taskType The type of task (i.e. "deadline", "event", or "todo").
     * @return The flags corresponding to the task type specified.
     */
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

    /**
     * Conducts checks on a todo command that the user has entered to ensure that it complies
     * with the following formatting rules:
     * <ul>
     *     <li>Has a description</li>
     * </ul>
     *
     * @param todoUserInput The todo command that the user has entered.
     * @throws InvalidCommandException If the todo command given fails the checks.
     */
    private void checkTodoCommand(String todoUserInput) throws InvalidCommandException {
        // Check if user command has a description
        String[] commandAndDescription = todoUserInput.split(" ", 2);

        if (commandAndDescription.length < 2) {
            // User input has no description
            throw new InvalidCommandException(InvalidCommandException.TODO_NO_DESCRIPTION);
        }

        // Checks are complete, the command is okay. Method will return to caller.
    }

    /**
     * Conducts checks on a deadline or event command that the user has entered to ensure that it
     * complies with the following formatting rules:
     * <ul>
     *     <li>Has a description</li>
     *     <li>Has a date that follows the date-time format</li>
     *     <li>Correct flag is used</li>
     *     <li>Does not have unnecessary or extra flags</li>
     * </ul>
     *
     * @param eventDeadlineUserInput The deadline or event command that the user has entered.
     * @throws InvalidCommandException If the deadline or todo command given fails the checks.
     */
    private void checkEventDeadlineCommand(String eventDeadlineUserInput) throws InvalidCommandException {
        // Check if user input has a description
        String taskType = eventDeadlineUserInput.split(" ")[0];
        String commandFlagKeyword = getDeadlineOrEventFlag(taskType);
        String[] splitByFlag = eventDeadlineUserInput.split(commandFlagKeyword);
        String[] commandAndDescription = splitByFlag[0].split(" ", 2);

        boolean hasLessThanTwoElements = commandAndDescription.length < 2;
        boolean hasEmptySecondElement = commandAndDescription[1].equals("");

        if (hasLessThanTwoElements || hasEmptySecondElement) {
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

    /**
     * Returns either the event or deadline flag keyword based on the specified type of task.
     *
     * @param taskType The type of task. Must only be either "event" or "deadline".
     * @return Returns the event flag keyword if the task type is "event", otherwise the deadline flag
     * keyword is returned.
     */
    private String getDeadlineOrEventFlag(String taskType) {
        if (taskType.equalsIgnoreCase(SoraUI.ADD_EVENT_COMMAND_KEYWORD)) {
            return SoraUI.ADD_EVENT_FLAG_KEYWORD;
        } else {
            return SoraUI.ADD_DEADLINE_FLAG_KEYWORD;
        }
    }

    /**
     * Returns either the event or deadline 'no description' exception message depending on the
     * type of task.
     *
     * @param taskType The type of task. Must only be either "event" or "deadline".
     * @return Returns the event exception message if the task type is "event", otherwise the deadline
     * exception message is returned.
     */
    private String getDeadlineOrEventNoDescriptionExceptionMsg(String taskType) {
        if (taskType.equalsIgnoreCase(SoraUI.ADD_EVENT_COMMAND_KEYWORD)) {
            return InvalidCommandException.EVENT_NO_DESCRIPTION;
        } else {
            return InvalidCommandException.DEADLINE_NO_DESCRIPTION;
        }
    }

    /**
     * Returns either the event or deadline 'missing flag' exception message depending on the
     * type of task.
     *
     * @param taskType The type of task. Must only be either "event" or "deadline".
     * @return Returns the event exception message if the task type is "event", otherwise the deadline
     * exception message is returned.
     */
    private String getDeadlineOrEventNoFlagExceptionMsg(String taskType) {
        if (taskType.equalsIgnoreCase(SoraUI.ADD_EVENT_COMMAND_KEYWORD)) {
            return InvalidCommandException.EVENT_MISSING_FLAG;
        } else {
            return InvalidCommandException.DEADLINE_MISSING_FLAG;
        }
    }

    /**
     * Returns either the event or deadline 'no date-time' exception message depending on the
     * type of task.
     *
     * @param taskType The type of task. Must only be either "event" or "deadline"
     * @return Returns the event exception message if the task type is "event", otherwise the deadline
     * exception message is returned.
     */
    private String getDeadlineOrEventNoDateExceptionMsg(String taskType) {
        if (taskType.equalsIgnoreCase(SoraUI.ADD_EVENT_COMMAND_KEYWORD)) {
            return InvalidCommandException.EVENT_NO_PERIOD;
        } else {
            return InvalidCommandException.DEADLINE_NO_DUE_DATE;
        }
    }

    /**
     * Returns either the event or deadline 'invalid flags' exception message depending on the
     * type of task.
     *
     * @param taskType The type of task. Must only be either "event" or "deadline".
     * @return Returns the event exception message if the task type is "event", otherwise the deadline
     * exception message is returned.
     */
    private String getDeadlineOrEventInvalidFlagExceptionMsg(String taskType) {
        if (taskType.equalsIgnoreCase(SoraUI.ADD_EVENT_COMMAND_KEYWORD)) {
            return InvalidCommandException.EVENT_INVALID_FLAGS;
        } else {
            return InvalidCommandException.DEADLINE_INVALID_FLAGS;
        }
    }

    /**
     * Checks against a list of flags (SoraUI.LIST_OF_FLAG_KEYWORDS) to check if the user's input
     * contains at least one incorrect flag.
     *
     * @param userInput The string that the user has entered.
     * @param correctFlag The flag that should be paired with the command that the user has entered.
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

    /**
     * Extracts the task type (deadline, event, or todo), in particular the first word
     * of the specified text, and returns it.
     *
     * @param text The string with the task type to be extracted.
     * @return The extracted task type.
     */
    private String extractTaskType(String text) {
        String taskType = text.toLowerCase().split(" ", 2)[0];
        return taskType;
    }

    /**
     * Removes the command keyword (deadline, event, or todo), in particular the first
     * word of the specified text, and returns the remaining text.
     *
     * @param text The string with the command to be removed.
     * @return The string with the command removed.
     */
    private String removeCommandKeyword(String text) {
        String description = text.split(" ", 2)[1];
        return description;
    }

    /**
     * Extracts the task description and date from the specified text and returns them
     * in the form of an array. This method should only be used with event/deadline commands.
     *
     * @param text The string with the description and date to be extracted
     * @param flagKeyword The flag that will be used to separate the description and date.
     * @return A string array containing the extracted description and date.
     */
    private String[] extractDescriptionAndDate(String text, String flagKeyword) {
        String taskDescriptionAndDateString = removeCommandKeyword(text);
        String[] taskDescriptionAndDate = new String[EVENT_DEADLINE_TASK_NUMBER_OF_PARAMETERS];

        for (int i = 0; i < taskDescriptionAndDate.length; i += 1) {
            taskDescriptionAndDate[i] = taskDescriptionAndDateString.split(flagKeyword)[i].trim();
        }

        return taskDescriptionAndDate;
    }

    private LocalDateTime getDateTimeObject(String dateTimeString) throws DateTimeParseException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(SoraUI.DATE_TIME_INPUT_FORMAT);
        LocalDateTime dateTimeObject = null;

        try {
            dateTimeObject = LocalDateTime.parse(dateTimeString, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            // Re-throw it to calling method to handle
            throw e;
        }

        return dateTimeObject;
    }

    /**
     * Marks a task as either done or not done.
     *
     * @param taskNum The task number that represents the task that will be updated.
     * @param status true to mark the task as done; false to mark the task as not done.
     * @return true to indicate that the update process is successful.
     */
    public boolean updateDoneStatus(int taskNum, boolean status) throws ArrayIndexOutOfBoundsException {
        // Check if task number is within the range of the list
        boolean taskNumWithinRange = checkTaskNumberWithinRange(taskNum);
        if (!taskNumWithinRange) {
            throw new ArrayIndexOutOfBoundsException("Task number is out of permitted range of list.");
        }

        // Calculate index number of task in the ArrayList
        int indexNum = taskNum - 1;

        // Update the task status
        getList().get(indexNum).setDone(status);
        return true;
    }

    /**
     * Deletes a task from Sora's list.
     *
     * @param taskNum The task number that represents the task that will be deleted.
     * @return The Task instance that has been removed from the list.
     * @throws EmptyListException If the list is empty.
     */
    public Task deleteTask(int taskNum) throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException(EmptyListException.EMPTY_LIST_MSG);
        }

        // Check if task number is within the range of the list
        boolean taskNumWithinRange = checkTaskNumberWithinRange(taskNum);
        if (!taskNumWithinRange) {
            throw new ArrayIndexOutOfBoundsException("Task number is out of permitted range of list.");
        }

        // Calculate index number of task in the ArrayList
        int indexNum = taskNum - 1;

        // Remove the task from the list
        Task taskRemoved = getList().remove(indexNum);
        return taskRemoved;
    }

    /**
     * Checks if the specified task number is within the quantity range of the current number of tasks
     * in the list.
     *
     * @param taskNum The task number that will be used to reference a potential task on the list.
     * @return true if the task number is referencing a valid task item. Otherwise, false is returned.
     */
    public boolean checkTaskNumberWithinRange(int taskNum) {
        if (taskNum > 0 && taskNum <= getNumberOfTasks()) {
            return true;
        }

        return false;
    }

    /**
     * Displays to the user a Task instance that is represented by a specified task number.
     *
     * @param taskNum The task number that will be displayed to the user.
     */
    public void displayTask(int taskNum) {
        int taskIndex = taskNum - 1;
        Task taskToDisplay = getList().get(taskIndex);
        System.out.println("\t" + taskToDisplay.toString());
    }

    /**
     * Displays a representation of the specified Task instance to the user.
     *
     * @param taskObject The Task instance that will be displayed to the user.
     */
    public void displayTask(Task taskObject) {
        System.out.println("\t" + taskObject.toString());
    }

    /**
     * Displays all the tasks that can be found in the list.
     */
    public void displayAllTasks() {
        for (int i = 0; i < getList().size(); i += 1) {
            System.out.println("\t" + (i + 1) + "." + getList().get(i).toString());
        }
    }
}
