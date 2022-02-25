package serene.operation;

import serene.global.Constant;
import serene.global.Ui;
import serene.task.Task;

public class Parser {
    /**
     * Determines which command did the user input and passes the parameters to the respective command handlers.
     * Returns Constant.DONE if the user inputs "bye", and Constant.CONTINUE otherwise.
     *
     * @param userInput The user's input
     * @param tasks The task list containing the user's tasks
     * @return A constant signifying if program should continue to run
     */
    public static int parseInput(String userInput, TaskList tasks) {
        String[] responsePartition = userInput.split(" ", 2);
        int operationState = Constant.CONTINUE;
        String keyword = responsePartition[Constant.RESPONSE_INDEX_KEYWORD];
        switch (keyword) {
        case "bye":
            operationState = Constant.DONE;
            break;
        case "list":
            Ui.printTaskList(tasks);
            break;
        case "mark":
            TaskList.markTaskDone(responsePartition);
            break;
        case "unmark":
            TaskList.markTaskNotDone(responsePartition);
            break;
        case "delete":
            TaskList.removeTask(responsePartition);
            break;
        case "find":
            Ui.printFoundTasks(tasks.getTaskList(), responsePartition);
            break;
        case "help":
            Ui.printWithPartition(Ui.HELP_MESSAGE);
            break;
        default:
            TaskList.addTask(userInput);
        }
        return operationState;
    }

    /**
     * Extracts the index of the task to be operated on and checks if the user input a valid index.
     * Returns the task index if the input is valid, Constant.ERROR_CODE otherwise.
     *
     * @param userInput The user's input
     * @param taskCount Counter of number of tasks present
     * @return Extracted task index if valid, error code otherwise
     */
    public static int validateIndex(String[] userInput, int taskCount) {
        // Extract index of task to operate on
        String inputNumber = userInput[Constant.RESPONSE_INDEX_BODY];
        int taskIndex = Integer.parseInt(inputNumber) - 1;
        // Validation of provided index
        if (!isWithinRange(taskIndex, taskCount)) {
            Ui.printWithPartition(Ui.INVALID_NUM_ERROR_MESSAGE);
            return Constant.ERROR_CODE;
        }
        return taskIndex;
    }

    /**
     * Returns if the provided value will not cause an out-of-bounds read / write.
     *
     * @param taskIndex Index of the targeted task
     * @param taskCount Counter of number of tasks present
     * @return True if index provided is between 0 to taskCount - 1, false otherwise
     */
    private static boolean isWithinRange(int taskIndex, int taskCount) {
        return (!isTooLowIndex(taskIndex) && !isTooHighIndex(taskIndex, taskCount));
    }

    /**
     * Returns if the provided value will cause an out-of-bounds read / write at the lower end.
     *
     * @param taskIndex Index of the targeted task
     * @return True if index provided is below 0, false otherwise
     */
    private static boolean isTooLowIndex(int taskIndex) {
        return taskIndex < 0;
    }

    /**
     * Returns if the provided value will cause an out-of-bounds read / write at the upper end.
     *
     * @param taskIndex Index of the targeted task
     * @param taskCount Counter of number of tasks present
     * @return True if index provided is above tackCount - 1, false otherwise
     */
    private static boolean isTooHighIndex(int taskIndex, int taskCount) {
        return taskIndex > taskCount - 1;
    }

    /**
     * Returns if the provided task description is compliant with the correct syntax
     * @param userInput The user's input
     * @return True if there is no "/at" or "/by" as the user's first word, false otherwise
     */
    public static boolean isValidDescription(String userInput) {
        String firstWord = userInput.split(" ", 2)[Constant.TASK_INDEX_DESCRIPTION];
        return !isEmpty(firstWord) && !firstWord.contains("/at") && !firstWord.contains("/by");
    }

    /**
     * Returns if the input string is empty.
     *
     * @param input The string to check
     * @return True if string is empty, false otherwise
     */
    public static boolean isEmpty(String input) {
        return input.equals("");
    }

    /**
     * Returns if the task is one of what the user is searching for.
     *
     * @param task The task to check
     * @param toFind The user's search word
     * @return True if the task has what the user is looking for, false otherwise
     */
    public static boolean isOfInterest(Task task, String toFind) {
        return task.getDescription().contains(toFind);
    }
}
