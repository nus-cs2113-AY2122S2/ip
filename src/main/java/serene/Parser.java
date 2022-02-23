package serene;

import serene.global.Constant;
import serene.global.Ui;

public class Parser {
    public static int parseInput(String userInput) {
        // Split keyword from the rest of the input
        String[] responsePartition = userInput.split(" ", 2);
        String keyword = responsePartition[Constant.RESPONSE_INDEX_KEYWORD];
        int operationState = Constant.CONTINUE;
        switch (keyword) {
        case "bye":
            operationState = Constant.DONE;
            break;
        case "list":
            Serene.printTaskList();
            break;
        case "mark":
            Serene.markTaskDone(responsePartition);
            break;
        case "unmark":
            Serene.markTaskNotDone(responsePartition);
            break;
        case "delete":
            Serene.removeTask(responsePartition);
            break;
        default:
            Serene.addTask(userInput);
        }
        return operationState;
    }

    public static int validateIndex(String[] userInput) {
        // Extract index of task to remove
        String inputNumber = userInput[Constant.RESPONSE_INDEX_BODY];
        int taskIndex = Integer.parseInt(inputNumber) - 1;
        // Validation of provided index
        if (!isWithinRange(taskIndex)) {
            Serene.printWithPartition(Ui.INVALID_NUM_ERROR_MESSAGE);
            return Constant.ERROR_CODE;
        }
        return taskIndex;
    }

    private static boolean isWithinRange(int taskIndex) {
        return taskIndex >= 0 && taskIndex <= Serene.taskCount - 1;
    }

    public static boolean isValidDescription(String userInput) {
        String firstWord = userInput.split(" ", 2)[Constant.TASK_INDEX_DESCRIPTION];
        return !firstWord.strip().equals("") && !firstWord.contains("/at") && !firstWord.contains("/by");
    }
}
