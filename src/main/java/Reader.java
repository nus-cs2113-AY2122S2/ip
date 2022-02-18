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

    public TaskManager read(TaskManager taskManager) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                taskManager.addTask(getTaskFromLine(line));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return taskManager;
    }

    public Task getTaskFromLine(String line) {
        Task task;
        String[] splitline = splitStringBySlash(line);
        switch (getType(splitline)) {
        case "E":
            task = new Event(getDescription(splitline), getDate(splitline));
            break;
        case "D":
            task = new Deadline(getDescription(splitline), getDate(splitline));
            break;
        default:
            task = new Todo(getDescription(splitline));
        }
        if (isTaskDone(getDone(splitline))) {
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

    private String getType(String[] splitLine) {
        return splitLine[0].trim();
    }

    private String getDescription(String[] splitLine) {
        return splitLine[2].trim();
    }

    private String getDate(String[] splitLine) {
        return splitLine[3].trim();
    }

    private int getDone(String[] splitLine) {
        return Integer.parseInt(splitLine[1].trim());
    }
}
