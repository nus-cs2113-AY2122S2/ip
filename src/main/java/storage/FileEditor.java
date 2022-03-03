package storage;

import tasks.*;
import ui.Ui;

import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class FileEditor {
    private static String directoryName;
    private static String fileName;
    private File userFile;
    private File userDirectory;

    public FileEditor(String fileName, String directoryName) {
        this.directoryName = directoryName;
        this.fileName = fileName;
        try {
            userDirectory = new File(directoryName);
            if (!userDirectory.exists()) {
                userDirectory.mkdir();
                System.out.println(Ui.MISSING_FILE_DIRECTORY_MESSAGE);
            }
            userFile = new File(directoryName + File.separator + fileName);
            if (!userFile.exists()) {
                userFile.createNewFile();
                System.out.println(Ui.MISSING_FILE_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(directoryName + File.separator + fileName);
        for (Task task : tasks) {
            String taskToAppend = task.getDetails();
            fw.write(taskToAppend + System.lineSeparator());
        }
        fw.close();
    }

    public ArrayList<Task> readFileContents() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(userFile);
            ArrayList<String> tasksFromFile = new ArrayList<>();
            while (s.hasNextLine()) {
                tasksFromFile.add(s.nextLine());
            }
            tasks = storeAsTasks(tasksFromFile);
        } catch (IOException | DateTimeException e) {
            System.out.println(Ui.CORRUPT_FILE_MESSAGE);
            new FileWriter("./data/tasks.txt", false).close();
        }
        return tasks;
    }

    private static ArrayList<Task> storeAsTasks(ArrayList<String> taskStrings) throws DateTimeException {
        ArrayList<Task> tasklist = new ArrayList<>();
        for (String taskString : taskStrings) {
            char taskType = taskString.charAt(0);
            Task task = null;
            switch (taskType) {
            case 'T':
                task = new Todo(extractDescriptionFromTaskString(taskString));
                break;
            case 'D':
                task = new Deadline(extractDescriptionFromTaskString(taskString), extractDateTimeFromTaskString(taskString));
                break;
            case 'E':
                task = new Event(extractDescriptionFromTaskString(taskString), extractDurationFromTaskString(taskString));
                break;
            }
            task.setDone(extractDoneStatusFromTaskString(taskString));
            tasklist.add(task);
        }
        return tasklist;
    }

    private static String extractDescriptionFromTaskString(String taskString) {
        String taskDescription;
        int startIndexOfDescription = taskString.indexOf("|", taskString.indexOf("|") + 1)+1;
        int endIndexOfDescription = taskString.indexOf("|", startIndexOfDescription);
        if (endIndexOfDescription == -1) {
            taskDescription = taskString.substring(startIndexOfDescription);
        } else {
            taskDescription = taskString.substring(startIndexOfDescription, endIndexOfDescription);
        }
        return taskDescription;
    }

    private static Boolean extractDoneStatusFromTaskString(String taskString) {
        int startIndex = taskString.indexOf("|")+1;
        char doneStatus = taskString.charAt(startIndex);
        if (doneStatus == '1') {
            return true;
        } else {
            return false;
        }
    }

    private static LocalDateTime extractDateTimeFromTaskString(String taskString) throws DateTimeException {
        int startIndexOfDescription = taskString.indexOf("|", taskString.indexOf("|") + 1)+1;
        int startIndexOfTime = taskString.indexOf("|", startIndexOfDescription)+1;
        String dateTimeString = taskString.substring(startIndexOfTime);
        LocalDateTime dateTime = null;
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        dateTime = LocalDateTime.from(f.parse(dateTimeString));
        return dateTime;
    }

    private static String extractDurationFromTaskString(String taskString) {
        int startIndexOfDescription = taskString.indexOf("|", taskString.indexOf("|") + 1)+1;
        int startIndexOfTime = taskString.indexOf("|", startIndexOfDescription)+1;
        String duration = taskString.substring(startIndexOfTime);
        return duration;
    }

}
