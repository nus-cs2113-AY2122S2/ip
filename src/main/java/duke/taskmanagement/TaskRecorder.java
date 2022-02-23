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

public class TaskRecorder {
    private static final String HOME = System.getProperty("user.dir");
    private static final java.nio.file.Path PATH = java.nio.file.Paths.get(HOME, "data","duke.txt");
    private static UserInterface ui = new UserInterface();

    public ArrayList<Task> createTasks(ArrayList<String[]> fileData) throws EmptyDescriptionException,
            EmptyTimingDetailsException {
        int taskUniqueID = 0;
        String isMarked = "1";
        ArrayList<Task> tasks = new ArrayList<>();
        for (String[] data : fileData) {
            String Marker = data[0];
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
            if (Marker.equals(isMarked)) {
                tasks.get(tasks.size() - 1).setIsMarked();
            }
            taskUniqueID++;
        }
        return tasks;
    }

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

    public void addData(String userInput) throws IOException {
        checkFileExists();
        List<String> lines = Files.readAllLines(PATH);
        String extraLine = "0|" + userInput;
        lines.add(extraLine);
        Files.write(PATH, lines);
    }

    public void deleteData(int taskCount) throws IOException {
        checkFileExists();
        List<String> lines = Files.readAllLines(PATH);
        lines.remove(taskCount);
        Files.write(PATH, lines);
    }

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

    public void checkFileExists() {
        try {
            // create parent folder if it does not exist
            if (!Files.exists(PATH.getParent())) {
                Files.createDirectory(PATH.getParent());
            }
            // create file if it does not exists
            if (!Files.exists(PATH)) {
                Files.createFile(PATH);
            }
        } catch (IOException e) {
            ui.printIOExceptionMessageLoad();
        }
    }
}
