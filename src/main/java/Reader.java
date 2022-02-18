import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class Reader {
    private static final String PATH = "./data/";
    private static final String FILE = "duke.txt";
    private File file;
    private boolean isFileExists;

    public Reader() {
        file = new File(PATH + "/" + FILE);
        isFileExists = file.exists();
    }

    public boolean isFileExists() {
        return isFileExists;
    }

    public TaskManager readFile(TaskManager taskManager) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                taskManager.addTask(getTaskFromLine(line));
            }
        } catch (Exception e) { //Future task: exception
            System.out.println(e);
        }
        return taskManager;
    }

    public Task getTaskFromLine(String line) {
        Task task;
        String[] splitline = splitStringBySlash(line);
        switch (getTaskType(splitline)) {
        case "E":
            task = new Event(getTaskDescription(splitline), getTaskDate(splitline));
            break;
        case "D":
            task = new Deadline(getTaskDescription(splitline), getTaskDate(splitline));
            break;
        default:
            task = new Todo(getTaskDescription(splitline));
        }
        if (isTaskDone(getTaskDone(splitline))) {
            task.setDone(true);
        }
        return task;
    }

    private boolean isTaskDone(int isDone) {
        return isDone == 1;
    }

    private String[] splitStringBySlash(String line) {
        String[] splitLine = line.split("/");
        return splitLine;
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
