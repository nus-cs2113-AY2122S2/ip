package serene;

import serene.task.Deadline;
import serene.task.Event;
import serene.task.Task;
import serene.task.ToDo;

import java.util.ArrayList;
import java.util.Scanner;

public class Serene {
    private static final String PARTITION_LINE = "____________________________________________________________";
    private static final String INPUT_ERROR_MESSAGE = "Uhm... I'm afraid I don't know what you mean by that :/";
    private static final String INVALID_NUM_ERROR_MESSAGE = "Please enter a valid task number ;-;";
    private static final String EMPTY_DESC_ERROR_MESSAGE = "Hey! Don't try to make me record nothing for fun :<";
    private static final String EMPTY_BY_ERROR_MESSAGE = "No time input? Please remember your /by~";
    private static final String EMPTY_AT_ERROR_MESSAGE = "No time input? Please remember your /at~";
    private static final int DONE = -1;
    private static final int CONTINUE = -2;
    private static final int RESPONSE_INDEX_KEYWORD = 0;
    private static final int RESPONSE_INDEX_BODY = 1;
    private static final int TASK_INDEX_DESCRIPTION = 0;
    private static final int TASK_INDEX_OPTIONS = 1;
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static int taskCount = 0;
    private static int statusOfSerene = CONTINUE;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        printWelcomeMessage();
        operateSerene(in);
        printExitMessage();
    }

    private static void printWelcomeMessage() {
        String logo = " #####  ####### ######  ####### #     # ####### \n"
                + "#     # #       #     # #       ##    # #       \n"
                + "#       #       #     # #       # #   # #       \n"
                + " #####  #####   ######  #####   #  #  # #####   \n"
                + "      # #       #   #   #       #   # # #       \n"
                + "#     # #       #    #  #       #    ## #       \n"
                + " #####  ####### #     # ####### #     # ####### ";
        String greetLine = "Hello~ I'm Serene" + System.lineSeparator() + "What can I do for you?";
        System.out.println(PARTITION_LINE);
        System.out.println("Booting up");
        System.out.println(logo);
        printWithPartition(greetLine);
    }

    private static void printWithPartition(String input) {
        System.out.println(PARTITION_LINE);
        System.out.println(input);
        System.out.println(PARTITION_LINE);
    }

    private static void operateSerene(Scanner in) {
        while (statusOfSerene != DONE) {
            String userInput = in.nextLine();
            statusOfSerene = parseInput(userInput);
        }
    }

    private static int parseInput(String userInput) {
        // Split keyword from the rest of the input
        String[] responsePartition = userInput.split(" ", 2);
        String keyword = responsePartition[RESPONSE_INDEX_KEYWORD];
        int operationState = CONTINUE;
        switch (keyword) {
        case "bye":
            operationState = DONE;
            break;
        case "list":
            printTaskList();
            break;
        case "mark":
            markTaskDone(responsePartition);
            break;
        case "unmark":
            markTaskNotDone(responsePartition);
            break;
        default:
            addTask(userInput);
        }
        return operationState;
    }

    private static void printTaskList() {
        System.out.println(PARTITION_LINE);
        System.out.println("Here is your task list:");
        int i = 1;
        for (Task task: taskList) {
            System.out.println((i) + "." + task);
            i++;
        }
        System.out.println(PARTITION_LINE);
    }

    private static void markTaskDone(String[] userInput) {
        try {
            // Extract index of task to mark
            String inputNumber = userInput[RESPONSE_INDEX_BODY];
            int taskIndex = Integer.parseInt(inputNumber) - 1;
            // Validation of provided index
            if (!isWithinRange(taskIndex)) {
                printWithPartition(INVALID_NUM_ERROR_MESSAGE);
                return;
            }
            // Checking if task has not already been marked
            if (!taskList.get(taskIndex).isDone()) {
                taskList.get(taskIndex).markDone();
                printWithPartition("Good job~ This task is now done:" + System.lineSeparator() +
                        taskList.get(taskIndex));
            }
            else {
                printWithPartition("Huh? Didn't you complete this already?" + System.lineSeparator() +
                        taskList.get(taskIndex));
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            printWithPartition(INVALID_NUM_ERROR_MESSAGE);
        }
    }

    private static void markTaskNotDone(String[] userInput) {
        try {
            // Extract index of task to unmark
            String inputNumber = userInput[RESPONSE_INDEX_BODY];
            int taskIndex = Integer.parseInt(inputNumber) - 1;
            // Validation of provided index
            if (!isWithinRange(taskIndex)) {
                printWithPartition(INVALID_NUM_ERROR_MESSAGE);
                return;
            }
            // Checking if task has already been marked
            if (taskList.get(taskIndex).isDone()) {
                taskList.get(taskIndex).markNotDone();
                printWithPartition("Sigh. Here we go again:" + System.lineSeparator() +
                        taskList.get(taskIndex));
            }
            else {
                printWithPartition("Bruh. You never completed this in the first place." + System.lineSeparator() +
                        taskList.get(taskIndex));
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            printWithPartition(INVALID_NUM_ERROR_MESSAGE);
        }
    }

    private static boolean isWithinRange(int taskIndex) {
        return taskIndex >= 0 && taskIndex <= taskCount - 1;
    }

    private static void addTask(String userInput) {
        // Extracting which type of task does the user want to add
        String[] responsePartition = userInput.split(" ", 2);
        String keyword = responsePartition[RESPONSE_INDEX_KEYWORD];
        switch (keyword) {
        case "todo":
            addToDo(userInput);
            break;
        case "event":
            addEvent(userInput);
            break;
        case "deadline":
            addDeadline(userInput);
            break;
        default:
            printWithPartition(INPUT_ERROR_MESSAGE);
        }
    }

    private static void addToDo(String userInput) {
        String[] responsePartition = userInput.split(" ", 2);
        try {
            String description = responsePartition[RESPONSE_INDEX_BODY];
            ToDo task = new ToDo(description);
            allocateTask(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            printWithPartition(EMPTY_DESC_ERROR_MESSAGE);
        }
    }

    private static void addEvent(String userInput) {
        String[] responsePartition = userInput.split(" ", 2);
        String[] taskPartition;
        try {
            String description = responsePartition[RESPONSE_INDEX_BODY];
            // Checking if a valid description has been provided
            if (!isValidDescription(description)) {
                printWithPartition(EMPTY_DESC_ERROR_MESSAGE);
                return;
            }
            taskPartition = description.split(" /at ");
        } catch (ArrayIndexOutOfBoundsException e) {
            printWithPartition(EMPTY_DESC_ERROR_MESSAGE);
            return;
        }
        try {
            Event task = new Event(taskPartition[TASK_INDEX_DESCRIPTION], taskPartition[TASK_INDEX_OPTIONS]);
            allocateTask(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            printWithPartition(EMPTY_AT_ERROR_MESSAGE);
        }
    }

    private static void addDeadline(String userInput) {
        String[] responsePartition = userInput.split(" ", 2);
        String[] taskPartition;
        try {
            String description = responsePartition[RESPONSE_INDEX_BODY];
            // Checking if a valid description has been provided
            if (!isValidDescription(description)) {
                printWithPartition(EMPTY_DESC_ERROR_MESSAGE);
                return;
            }
            taskPartition = description.split(" /by ");
        } catch (ArrayIndexOutOfBoundsException e) {
            printWithPartition(EMPTY_DESC_ERROR_MESSAGE);
            return;
        }
        try {
            Deadline task = new Deadline(taskPartition[TASK_INDEX_DESCRIPTION], taskPartition[TASK_INDEX_OPTIONS]);
            allocateTask(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            printWithPartition(EMPTY_BY_ERROR_MESSAGE);
        }
    }

    private static boolean isValidDescription(String userInput) {
        String firstWord = userInput.split(" ", 2)[TASK_INDEX_DESCRIPTION];
        return !firstWord.strip().equals("") && !firstWord.contains("/at") && !firstWord.contains("/by");
    }

    private static void allocateTask(Task inputTask) {
        taskList.add(inputTask);
        taskCount++;
        printAddedTask(inputTask);
    }

    private static void printAddedTask(Task inputTask) {
        String toPrint;
        if (taskCount == 1) {
            toPrint = "Okay, I've added this for you:" + System.lineSeparator() +
                    inputTask + System.lineSeparator() +
                    "Now you have " + taskCount + " task in the list.";
        } else {
            toPrint = "Okay, I've added this for you:" + System.lineSeparator() +
                    inputTask + System.lineSeparator() +
                    "Now you have " + taskCount + " tasks in the list.";
        }
        printWithPartition(toPrint);
    }

    private static void printExitMessage() {
        printWithPartition("Till next time. Hope to see you again soon~");
    }
}