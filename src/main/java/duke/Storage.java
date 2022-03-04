package duke;

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;

public class Storage {
    private final String path;
    private final String filename;
    private File file;

    public static final String EVENT = "E";
    public static final String DEADLINE = "D";
    public static final String TODO = "T";

    public Storage(String path, String filename) {
        this.path = path;
        this.filename = filename;
        this.file = new File(path + "/" + filename);
    }

    public void writeFile(String str) {
        try {
            File dir = new File(path);
            dir.mkdir();
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(path + "/" + filename, false);
            fileWriter.write(str);
            fileWriter.close();
        } catch (Exception e) {
            DukeException exception = new DukeException(Ui.writingFileError(e));
            Ui.printError(exception);
        }
    }

    public boolean isFileExists() {
        return file.exists();
    }

    public List<Task> readFile() {
        List<Task> tasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Task newTask = createTask(line);
                tasks.add(newTask);
            }
        } catch (Exception e) {
            DukeException exception = new DukeException(Ui.readingFileError(e));
            Ui.printError(exception);
        }
        return tasks;
    }

    private Task createTask(String line) throws DukeException {
        try {
            Task task;
            String[] splitLine = splitStringBySlash(line);
            switch (getTaskType(splitLine)) {
            case EVENT:
                task = new Event(getTaskDescription(splitLine), getTaskDate(splitLine));
                break;
            case DEADLINE:
                task = new Deadline(getTaskDescription(splitLine), getTaskDate(splitLine));
                break;
            case TODO:
                task = new Todo(getTaskDescription(splitLine));
                break;
            default:
                throw new DukeException(Ui.wrongFileFormatError(line));
            }
            if (isTaskDone(getTaskDone(splitLine))) {
                task.setDone(true);
            }
            return task;
        } catch (Exception e) {
            throw new DukeException(Ui.wrongFileFormatError(line));
        }
    }

    private String[] splitStringBySlash(String line) {
        String[] splitLine = line.split("/");
        return splitLine;
    }

    private boolean isTaskDone(int isDone) {
        return isDone == 1;
    }

    private String getTaskType(String[] splitLine) {
        return splitLine[0].trim();
    }

    private String getTaskDescription(String[] splitLine) {
        return splitLine[2].trim();
    }

    private String getTaskDate(String[] splitLine) {
        return splitLine[3].trim();
    }

    private int getTaskDone(String[] splitLine) {
        return Integer.parseInt(splitLine[1].trim());
    }
}