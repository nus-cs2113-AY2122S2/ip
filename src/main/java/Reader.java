import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Reader {
    private static final String PATH = "./data/";
    private static final String FILE = "duke.txt";
    public static final String EVENT = "E";
    public static final String DEADLINE = "D";
    public static final String TODO = "T";
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
            Scanner scanner = new Scanner(file);
            taskManager = readByLine(taskManager, scanner);
        } catch (Exception e) { //Future task: exception
            Ui.printError(e);
        }
        return taskManager;
    }

    private TaskManager readByLine(TaskManager taskManager, Scanner sc) {
        String line;
        while (sc.hasNext()) {
            try {
                line = sc.nextLine();
                taskManager.addTask(createTask(line));
            } catch (Exception e) { //Future task: exception
                Ui.printError(e);
            }
        }
        return taskManager;
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
                throw new DukeException(Ui.wrongFileFormat(line));
            }
            if (isTaskDone(getTaskDone(splitLine))) {
                task.setDone(true);
            }
            return task;
        } catch (Exception e) {
            throw new DukeException(Ui.wrongFileFormat(line));
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
