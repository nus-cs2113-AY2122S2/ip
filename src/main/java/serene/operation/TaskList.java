package serene.operation;

import serene.Serene;
import serene.global.Constant;
import serene.global.Ui;
import serene.task.Deadline;
import serene.task.Event;
import serene.task.Task;
import serene.task.ToDo;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList;
    private static int taskCount;

    public TaskList() {
        taskList = new ArrayList<>();
        taskCount = 0;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public static void markTaskDone(String[] userInput) {
        try {
            // Extract index of task to mark
            int taskIndex = Parser.validateIndex(userInput, taskCount);
            if (taskIndex == Constant.ERROR_CODE) {
                return;
            }
            // Checking if task has not already been marked
            if (!taskList.get(taskIndex).isDone()) {
                taskList.get(taskIndex).markDone();
                Ui.printWithPartition("Good job~ This task is now done:" + System.lineSeparator() +
                        taskList.get(taskIndex));
            }
            else {
                Ui.printWithPartition("Huh? Didn't you complete this already?" + System.lineSeparator() +
                        taskList.get(taskIndex));
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            Ui.printWithPartition(Ui.INVALID_NUM_ERROR_MESSAGE);
        }
    }

    public static void markTaskNotDone(String[] userInput) {
        try {
            // Extract index of task to unmark
            int taskIndex = Parser.validateIndex(userInput, taskCount);
            if (taskIndex == Constant.ERROR_CODE) {
                return;
            }
            // Checking if task has already been marked
            if (taskList.get(taskIndex).isDone()) {
                taskList.get(taskIndex).markNotDone();
                Ui.printWithPartition("Sigh. Here we go again:" + System.lineSeparator() +
                        taskList.get(taskIndex));
            }
            else {
                Ui.printWithPartition("Bruh. You never completed this in the first place." + System.lineSeparator() +
                        taskList.get(taskIndex));
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            Ui.printWithPartition(Ui.INVALID_NUM_ERROR_MESSAGE);
        }
    }

    public static void removeTask(String[] userInput) {
        try {
            int taskIndex = Parser.validateIndex(userInput, taskCount);
            if (taskIndex == Constant.ERROR_CODE) {
                return;
            }
            if (taskCount == Constant.TO_SINGULAR) {
                Ui.printWithPartition("Mmkay~ Shall remove this task:" + System.lineSeparator() +
                        taskList.get(taskIndex) + System.lineSeparator() +
                        "Now you have " + (taskCount - 1) + " task left in the list");
            }
            else {
                Ui.printWithPartition("Mmkay~ Shall remove this task:" + System.lineSeparator() +
                        taskList.get(taskIndex) + System.lineSeparator() +
                        "Now you have " + (taskCount - 1) + " tasks left in the list");
            }
            taskList.remove(taskIndex);
            taskCount--;
            Storage.rewriteSaveFile(Serene.SAVE_FILE_PATH, taskList);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            Ui.printWithPartition(Ui.INVALID_NUM_ERROR_MESSAGE);
        }
    }

    public static void addTask(String userInput) {
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
            Ui.printWithPartition(Ui.INPUT_ERROR_MESSAGE);
        }
    }

    private static void addToDo(String userInput) {
        String[] responsePartition = userInput.split(" ", 2);
        try {
            String description = responsePartition[Constant.RESPONSE_INDEX_BODY];
            ToDo task = new ToDo(description);
            allocateTask(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printWithPartition(Ui.EMPTY_DESC_ERROR_MESSAGE);
        } catch (IOException e) {
            Ui.printWithPartition(Ui.IO_FAIL_MESSAGE);
        }
    }

    private static void addEvent(String userInput) {
        String[] responsePartition = userInput.split(" ", 2);
        String[] taskPartition;
        try {
            String description = responsePartition[Constant.RESPONSE_INDEX_BODY];
            // Checking if a valid description has been provided
            if (!Parser.isValidDescription(description)) {
                Ui.printWithPartition(Ui.EMPTY_DESC_ERROR_MESSAGE);
                return;
            }
            taskPartition = description.split(" /at ");
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printWithPartition(Ui.EMPTY_DESC_ERROR_MESSAGE);
            return;
        }
        try {
            Event task = new Event(taskPartition[Constant.TASK_INDEX_DESCRIPTION], taskPartition[Constant.TASK_INDEX_OPTIONS]);
            allocateTask(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printWithPartition(Ui.EMPTY_AT_ERROR_MESSAGE);
        } catch (IOException e) {
            Ui.printWithPartition(Ui.IO_FAIL_MESSAGE);
        }
    }

    private static void addDeadline(String userInput) {
        String[] responsePartition = userInput.split(" ", 2);
        String[] taskPartition;
        try {
            String description = responsePartition[Constant.RESPONSE_INDEX_BODY];
            // Checking if a valid description has been provided
            if (!Parser.isValidDescription(description)) {
                Ui.printWithPartition(Ui.EMPTY_DESC_ERROR_MESSAGE);
                return;
            }
            taskPartition = description.split(" /by ");
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printWithPartition(Ui.EMPTY_DESC_ERROR_MESSAGE);
            return;
        }
        try {
            Deadline task = new Deadline(taskPartition[Constant.TASK_INDEX_DESCRIPTION],
                    taskPartition[Constant.TASK_INDEX_OPTIONS]);
            allocateTask(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printWithPartition(Ui.EMPTY_BY_ERROR_MESSAGE);
        } catch (IOException e) {
            Ui.printWithPartition(Ui.IO_FAIL_MESSAGE);
        }
    }

    private static void allocateTask(Task inputTask) throws IOException {
        taskList.add(inputTask);
        taskCount++;
        Storage.appendSave(inputTask.toString(), Serene.SAVE_FILE_PATH);
        Ui.printAddedTask(inputTask, taskCount);
    }
}
