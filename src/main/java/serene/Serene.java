package serene;

import serene.global.Constant;
import serene.global.UI;
import serene.task.Deadline;
import serene.task.Event;
import serene.task.Task;
import serene.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Serene {
    private static final String SAVE_FILE_PATH = "data/serene.txt";
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static int taskCount = 0;
    private static int statusOfSerene = Constant.CONTINUE;



    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            initiateSerene();
        } catch (IOException e) {
            System.out.println(UI.IO_FAIL_MESSAGE);
            printExitMessage();
            return;
        }
        printWelcomeMessage();
        operateSerene(in);
        printExitMessage();
    }

    private static void initiateSerene() throws IOException {
        File dataDirectory = new File("data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
            System.out.println("Data directory created~");
        }
        File save = new File(SAVE_FILE_PATH);
        if (save.createNewFile()) {
            System.out.println("Save file created~");
            return;
        }
        try {
            readSavedContents(save);
        } catch (FileNotFoundException e) {
            System.out.println(UI.IO_FAIL_MESSAGE);
        }
    }

    private static void readSavedContents(File save) throws FileNotFoundException {
        Scanner s = new Scanner(save);
        while(s.hasNext()) {
            recoverTask(s.nextLine());
            taskCount++;
        }
    }

    private static void recoverTask(String savedTask) {
        // Extract task type
        String taskType = savedTask.substring(Constant.SAVED_INDEX_TYPE, Constant.SAVED_INDEX_TYPE + 1);
        // Extract isDone
        String marker = savedTask.substring(Constant.SAVED_INDEX_IS_DONE, Constant.SAVED_INDEX_IS_DONE + 1);
        // Extract description
        String descriptionAndTime = savedTask.substring(Constant.SAVED_INDEX_DESCRIPTION);
        int timeIndex;
        String description;
        switch(taskType) {
        case "T":
            ToDo todo = new ToDo(descriptionAndTime);
            if (marker.equals("X")) {
                todo.markDone();
            }
            taskList.add(todo);
            break;
        case "D":
            timeIndex = descriptionAndTime.indexOf(" (by: ");
            description = descriptionAndTime.substring(0, timeIndex);
            String by = descriptionAndTime.substring(timeIndex + Constant.TIME_OFFSET, descriptionAndTime.length() - 1);
            Deadline deadline = new Deadline(description, by);
            if (marker.equals("X")) {
                deadline.markDone();
            }
            taskList.add(deadline);
            break;
        case "E":
            timeIndex = descriptionAndTime.indexOf(" (at: ");
            description = descriptionAndTime.substring(0, timeIndex);
            String at = descriptionAndTime.substring(timeIndex + Constant.TIME_OFFSET, descriptionAndTime.length() - 1);
            Event event = new Event(description, at);
            if (marker.equals("X")) {
                event.markDone();
            }
            taskList.add(event);
            break;
        }
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
        System.out.println(UI.PARTITION_LINE);
        System.out.println("Booting up");
        System.out.println(logo);
        printWithPartition(greetLine);
    }

    private static void printWithPartition(String input) {
        System.out.println(UI.PARTITION_LINE);
        System.out.println(input);
        System.out.println(UI.PARTITION_LINE);
    }

    private static void operateSerene(Scanner in) {
        while (statusOfSerene != Constant.DONE) {
            String userInput = in.nextLine();
            statusOfSerene = parseInput(userInput);
        }
    }

    private static int parseInput(String userInput) {
        // Split keyword from the rest of the input
        String[] responsePartition = userInput.split(" ", 2);
        String keyword = responsePartition[Constant.RESPONSE_INDEX_KEYWORD];
        int operationState = Constant.CONTINUE;
        switch (keyword) {
        case "bye":
            operationState = Constant.DONE;
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
        case "delete":
            removeTask(responsePartition);
            break;
        default:
            addTask(userInput);
        }
        return operationState;
    }

    private static void printTaskList() {
        System.out.println(UI.PARTITION_LINE);
        System.out.println("Here is your task list:");
        int i = 1;
        for (Task task : taskList) {
            System.out.println((i) + "." + task);
            i++;
        }
        System.out.println(UI.PARTITION_LINE);
    }

    private static void markTaskDone(String[] userInput) {
        try {
            // Extract index of task to mark
            int taskIndex = validateIndex(userInput);
            if (taskIndex == Constant.ERROR_CODE) {
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
            printWithPartition(UI.INVALID_NUM_ERROR_MESSAGE);
        }
    }

    private static void markTaskNotDone(String[] userInput) {
        try {
            // Extract index of task to unmark
            int taskIndex = validateIndex(userInput);
            if (taskIndex == Constant.ERROR_CODE) {
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
            printWithPartition(UI.INVALID_NUM_ERROR_MESSAGE);
        }
    }

    private static void removeTask(String[] userInput) {
        try {
            int taskIndex = validateIndex(userInput);
            if (taskIndex == Constant.ERROR_CODE) {
                return;
            }
            if (taskCount == 2) {
                printWithPartition("Mmkay~ Shall remove this task:" + System.lineSeparator() +
                        taskList.get(taskIndex) + System.lineSeparator() +
                        "Now you have " + (taskCount - 1) + " task left in the list");
            }
            else {
                printWithPartition("Mmkay~ Shall remove this task:" + System.lineSeparator() +
                        taskList.get(taskIndex) + System.lineSeparator() +
                        "Now you have " + (taskCount - 1) + " tasks left in the list");
            }
            taskList.remove(taskIndex);
            taskCount--;
            rewriteSaveFile();
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            printWithPartition(UI.INVALID_NUM_ERROR_MESSAGE);
        }
    }

    private static int validateIndex(String[] userInput) {
        // Extract index of task to remove
        String inputNumber = userInput[Constant.RESPONSE_INDEX_BODY];
        int taskIndex = Integer.parseInt(inputNumber) - 1;
        // Validation of provided index
        if (!isWithinRange(taskIndex)) {
            printWithPartition(UI.INVALID_NUM_ERROR_MESSAGE);
            return Constant.ERROR_CODE;
        }
        return taskIndex;
    }

    private static boolean isWithinRange(int taskIndex) {
        return taskIndex >= 0 && taskIndex <= taskCount - 1;
    }

    private static void rewriteSaveFile() {
        try {
            // Clear contents of file
            new FileWriter(SAVE_FILE_PATH, false).close();
            // Rewrite all tasks
            for (Task task: taskList) {
                appendSave(task.toString());
            }
        } catch (IOException e) {
            printWithPartition(UI.IO_FAIL_MESSAGE);
        }
    }

    private static void addTask(String userInput) {
        // Extracting which type of task does the user want to add
        String[] responsePartition = userInput.split(" ", 2);
        String keyword = responsePartition[Constant.RESPONSE_INDEX_KEYWORD];
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
            printWithPartition(UI.INPUT_ERROR_MESSAGE);
        }
    }

    private static void addToDo(String userInput) {
        String[] responsePartition = userInput.split(" ", 2);
        try {
            String description = responsePartition[Constant.RESPONSE_INDEX_BODY];
            ToDo task = new ToDo(description);
            allocateTask(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            printWithPartition(UI.EMPTY_DESC_ERROR_MESSAGE);
        } catch (IOException e) {
            printWithPartition(UI.IO_FAIL_MESSAGE);
        }
    }

    private static void addEvent(String userInput) {
        String[] responsePartition = userInput.split(" ", 2);
        String[] taskPartition;
        try {
            String description = responsePartition[Constant.RESPONSE_INDEX_BODY];
            // Checking if a valid description has been provided
            if (!isValidDescription(description)) {
                printWithPartition(UI.EMPTY_DESC_ERROR_MESSAGE);
                return;
            }
            taskPartition = description.split(" /at ");
        } catch (ArrayIndexOutOfBoundsException e) {
            printWithPartition(UI.EMPTY_DESC_ERROR_MESSAGE);
            return;
        }
        try {
            Event task = new Event(taskPartition[Constant.TASK_INDEX_DESCRIPTION], taskPartition[Constant.TASK_INDEX_OPTIONS]);
            allocateTask(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            printWithPartition(UI.EMPTY_AT_ERROR_MESSAGE);
        } catch (IOException e) {
            printWithPartition(UI.IO_FAIL_MESSAGE);
        }
    }

    private static void addDeadline(String userInput) {
        String[] responsePartition = userInput.split(" ", 2);
        String[] taskPartition;
        try {
            String description = responsePartition[Constant.RESPONSE_INDEX_BODY];
            // Checking if a valid description has been provided
            if (!isValidDescription(description)) {
                printWithPartition(UI.EMPTY_DESC_ERROR_MESSAGE);
                return;
            }
            taskPartition = description.split(" /by ");
        } catch (ArrayIndexOutOfBoundsException e) {
            printWithPartition(UI.EMPTY_DESC_ERROR_MESSAGE);
            return;
        }
        try {
            Deadline task = new Deadline(taskPartition[Constant.TASK_INDEX_DESCRIPTION],
                    taskPartition[Constant.TASK_INDEX_OPTIONS]);
            allocateTask(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            printWithPartition(UI.EMPTY_BY_ERROR_MESSAGE);
        } catch (IOException e) {
            printWithPartition(UI.IO_FAIL_MESSAGE);
        }
    }

    private static boolean isValidDescription(String userInput) {
        String firstWord = userInput.split(" ", 2)[Constant.TASK_INDEX_DESCRIPTION];
        return !firstWord.strip().equals("") && !firstWord.contains("/at") && !firstWord.contains("/by");
    }

    private static void allocateTask(Task inputTask) throws IOException {
        taskList.add(inputTask);
        taskCount++;
        appendSave(inputTask.toString());
        printAddedTask(inputTask);
    }

    private static void appendSave(String inputTask) throws IOException {
        FileWriter fw = new FileWriter(SAVE_FILE_PATH, true);
        fw.write(inputTask + System.lineSeparator());
        fw.close();
    }

    private static void printAddedTask(Task inputTask) {
        String toPrint;
        if (taskCount == 1) {
            toPrint = "Okay, I've added this for you:" + System.lineSeparator() +
                    inputTask + System.lineSeparator() +
                    "Now you have " + taskCount + " task in the list.";
        }
        else {
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