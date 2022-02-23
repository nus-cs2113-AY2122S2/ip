package serene;

import serene.global.Constant;
import serene.global.Ui;
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
    public static int taskCount = 0;
    private static int statusOfSerene = Constant.CONTINUE;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            initiateSerene();
        } catch (IOException e) {
            System.out.println(Ui.IO_FAIL_MESSAGE);
            Ui.printExitMessage();
            return;
        }
        Ui.printWelcomeMessage();
        operateSerene(in);
        Ui.printExitMessage();
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
            System.out.println(Ui.IO_FAIL_MESSAGE);
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

    private static void operateSerene(Scanner in) {
        while (statusOfSerene != Constant.DONE) {
            String userInput = in.nextLine();
            statusOfSerene = Parser.parseInput(userInput, taskList);
        }
    }

    public static void markTaskDone(String[] userInput) {
        try {
            // Extract index of task to mark
            int taskIndex = Parser.validateIndex(userInput);
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
            int taskIndex = Parser.validateIndex(userInput);
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
            int taskIndex = Parser.validateIndex(userInput);
            if (taskIndex == Constant.ERROR_CODE) {
                return;
            }
            if (taskCount == 2) {
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
            rewriteSaveFile();
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            Ui.printWithPartition(Ui.INVALID_NUM_ERROR_MESSAGE);
        }
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
            Ui.printWithPartition(Ui.IO_FAIL_MESSAGE);
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
        appendSave(inputTask.toString());
        Ui.printAddedTask(inputTask);
    }

    private static void appendSave(String inputTask) throws IOException {
        FileWriter fw = new FileWriter(SAVE_FILE_PATH, true);
        fw.write(inputTask + System.lineSeparator());
        fw.close();
    }

}