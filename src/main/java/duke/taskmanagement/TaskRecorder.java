package duke.taskmanagement;

import duke.customexceptions.EmptyDescriptionException;
import duke.customexceptions.EmptyTimingDetailsException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.userinterface.UserInterface;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * In charge of managing the file data of tasks created by the user. This includes reading, writing, updating and
 * deleting tasks as the user interacts with the program.
 */
public class TaskRecorder {
    private static final String HOME = System.getProperty("user.dir");
    private static final java.nio.file.Path PATH = java.nio.file.Paths.get(HOME, "data","duke.txt");
    private static UserInterface ui = new UserInterface();

    /**
     * Create the initial list of tasks based on file data when Duke first starts up.
     * @param fileData An arraylist of each line extracted from the data file.
     * @return An arraylist of tasks from the data file.
     * @throws EmptyDescriptionException If no description is written.
     * @throws EmptyTimingDetailsException If no timing information is written.
     */
    public ArrayList<Task> createTasks(ArrayList<String[]> fileData) throws EmptyDescriptionException,
            EmptyTimingDetailsException {
        int taskUniqueID = 0;
        String isMarked = "1";
        ArrayList<Task> tasks = new ArrayList<>();
        for (String[] data : fileData) {
            String marker = data[0];
            String input = data[1];
            String command = ui.getCommand(input);
            String description = ui.getDescription(input);
            switch (command) {
            case "todo":
                Todo newTodo = new Todo(description, taskUniqueID);
                tasks.add(newTodo);
                break;
            case "deadline":
                String by = ui.getTimingDetails(input);
                Deadline newDeadline = new Deadline(description, taskUniqueID, by);
                tasks.add(newDeadline);
                break;
            case "event":
                String at = ui.getTimingDetails(input);
                Event newEvent = new Event(description, taskUniqueID, at);
                tasks.add(newEvent);
                break;
            }
            if (marker.equals(isMarked)) {
                tasks.get(tasks.size() - 1).setIsMarked();
            }
            taskUniqueID++;
        }
        return tasks;
    }

    /**
     * Extracts each line of the data file into a readable format by Duke.
     * @return An arraylist of each line of data.
     * @throws IOException If the file cannot be loaded.
     */
    public ArrayList<String[]> loadData() throws IOException {
        checkFileExists();
        ArrayList<String[]> tasks = new ArrayList<>();
        List<String> lines = Files.readAllLines(PATH);
        for (String line : lines) {
            String[] lineData = line.split("\\|");
            tasks.add(lineData);
        }
        return tasks;
    }

    /**
     * Updates the file data with a new line of data to be in sync with the current arraylist of tasks.
     * @param userInput Text from user to be stored into the file data
     * @throws IOException If the file cannot be updated.
     */
    public void addData(String userInput) throws IOException {
        checkFileExists();
        List<String> lines = Files.readAllLines(PATH);
        String extraLine = "0|" + userInput;
        lines.add(extraLine);
        Files.write(PATH, lines);
    }

    /**
     * Updates the file data by deleting a line of data to be in sync with the current arraylist of tasks.
     * @param taskCount Index of the task to be deleted.
     * @throws IOException If the file cannot be updated.
     */
    public void deleteData(int taskCount) throws IOException {
        checkFileExists();
        List<String> lines = Files.readAllLines(PATH);
        lines.remove(taskCount);
        Files.write(PATH, lines);
    }

    /**
     * Updates the file data to mark the selected data to be in sync with current arraylist of tasks.
     * @param taskNumber Index of task in the arraylist to be deleted.
     * @throws IOException If the file cannot be updated.
     */
    public void markOrUnmarkData(int taskNumber) throws IOException {
        checkFileExists();
        List<String> lines = Files.readAllLines(PATH);
        if (lines.get(taskNumber).startsWith("0")) {
            String editedData = lines.get(taskNumber).replaceFirst("0","1");
            lines.remove(taskNumber);
            lines.add(taskNumber, editedData);
        } else {
            String editedData = lines.get(taskNumber).replaceFirst("1","0");
            lines.remove(taskNumber);
            lines.add(taskNumber, editedData);
        }
        Files.write(PATH, lines);
    }

    /**
     * Creates the required folder and file to be referenced by Duke if not found
     */
    public void checkFileExists() {
        try {
            if (!Files.exists(PATH.getParent())) {
                Files.createDirectory(PATH.getParent());
                ui.printCreateSaveDataDirectoryMessage();
            }
            if (!Files.exists(PATH)) {
                Files.createFile(PATH);
                ui.printCreateSaveDataFileMessage();
            }
        } catch (IOException e) {
            ui.printIOExceptionMessageLoad();
        }
    }
}
