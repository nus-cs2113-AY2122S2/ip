package serene.operation;

import serene.global.Constant;
import serene.global.Ui;

public class Parser {
    public static int parseInput(String userInput, TaskList tasks) {
        // Split keyword from the rest of the input
        String[] responsePartition = userInput.split(" ", 2);
        String keyword = responsePartition[Constant.RESPONSE_INDEX_KEYWORD];
        int operationState = Constant.CONTINUE;
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
        default:
            TaskList.addTask(userInput);
        }
        return operationState;
    }

    public static int validateIndex(String[] userInput, int taskCount) {
        // Extract index of task to remove
        String inputNumber = userInput[Constant.RESPONSE_INDEX_BODY];
        int taskIndex = Integer.parseInt(inputNumber) - 1;
        // Validation of provided index
        if (!isWithinRange(taskIndex, taskCount)) {
            Ui.printWithPartition(Ui.INVALID_NUM_ERROR_MESSAGE);
            return Constant.ERROR_CODE;
        }
        return taskIndex;
    }

    private static boolean isWithinRange(int taskIndex, int taskCount) {
        return (!isTooLow(taskIndex) && !isTooHigh(taskIndex, taskCount));
    }

    private static boolean isTooLow(int taskIndex) {
        return taskIndex < 0;
    }

    private static boolean isTooHigh(int taskIndex, int taskCount) {
        return taskIndex > taskCount - 1;
    }

    public static boolean isValidDescription(String userInput) {
        String firstWord = userInput.split(" ", 2)[Constant.TASK_INDEX_DESCRIPTION];
        return !firstWord.strip().equals("") && !firstWord.contains("/at") && !firstWord.contains("/by");
    }
}
