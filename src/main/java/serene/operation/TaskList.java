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

    /**
     * Constructs a new TaskList.
     */
    public TaskList() {
        taskList = new ArrayList<>();
        taskCount = 0;
    }

    /**
     * Returns the array of tasks stored.
     * @return The array of tasks stored
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Returns the number of tasks stored.
     * @return A counter of number of tasks present
     */
    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Increments the counter for number of tasks present.
     */
    public void incrementTaskCount() {
        taskCount++;
    }

    /**
     * Adds a task to the array of tasks.
     * @param task The task to be added
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Marks the task whose index is that given in the user's input.
     *
     * @param userInput The user's input
     */
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

    /**
     * Unmarks the task whose index is that given in the user's input.
     *
     * @param userInput The user's input
     */
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

    /**
     * Removes the task whose index is that given in the user's input.
     *
     * @param userInput The user's input
     */
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

    /**
     * Adds the task given in the user's input to the array of tasks.
     *
     * @param userInput The user's input
     */
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

    /**
     * Adds the task given in the user's input to the array of tasks, as a ToDo.
     *
     * @param userInput
     */
    private static void addToDo(String userInput) {
        String[] responsePartition = userInput.split(" ", 2);
        try {
            String description = responsePartition[Constant.RESPONSE_INDEX_BODY].strip();
            // Checking if a valid description has been provided
            if (description.equals("")) {
                Ui.printWithPartition(Ui.EMPTY_DESC_ERROR_MESSAGE);
                return;
            }
            ToDo task = new ToDo(description);
            allocateTask(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printWithPartition(Ui.EMPTY_DESC_ERROR_MESSAGE);
        } catch (IOException e) {
            Ui.printWithPartition(Ui.IO_FAIL_MESSAGE);
        }
    }

    /**
     * Adds the task given in the user's input to the array of tasks, as an Event.
     *
     * @param userInput
     */
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
            String time = taskPartition[Constant.TASK_INDEX_OPTIONS].strip();
            // Checking if time provided is not just an empty string
            if (Parser.isEmpty(time)) {
                Ui.printWithPartition(Ui.EMPTY_TIME_ERROR_MESSAGE);
                return;
            }
            Event task = new Event(taskPartition[Constant.TASK_INDEX_DESCRIPTION], time);
            allocateTask(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printWithPartition(Ui.EMPTY_AT_ERROR_MESSAGE);
        } catch (IOException e) {
            Ui.printWithPartition(Ui.IO_FAIL_MESSAGE);
        }
    }

    /**
     * Adds the task given in the user's input to the array of tasks, as a Deadline.
     *
     * @param userInput
     */
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
            String time = taskPartition[Constant.TASK_INDEX_OPTIONS].strip();
            // Checking if time provided is not just an empty string
            if (Parser.isEmpty(time)) {
                Ui.printWithPartition(Ui.EMPTY_TIME_ERROR_MESSAGE);
                return;
            }
            Deadline task = new Deadline(taskPartition[Constant.TASK_INDEX_DESCRIPTION], time);
            allocateTask(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printWithPartition(Ui.EMPTY_BY_ERROR_MESSAGE);
        } catch (IOException e) {
            Ui.printWithPartition(Ui.IO_FAIL_MESSAGE);
        }
    }

    /**
     * Adds the input task to the array of tasks.
     *
     * @param inputTask The task to be added
     * @throws IOException If appendSave method fails to construct a FileWriter
     */
    private static void allocateTask(Task inputTask) throws IOException {
        taskList.add(inputTask);
        taskCount++;
        Storage.appendSave(inputTask.toString(), Serene.SAVE_FILE_PATH);
        Ui.printAddedTask(inputTask, taskCount);
    }
}
