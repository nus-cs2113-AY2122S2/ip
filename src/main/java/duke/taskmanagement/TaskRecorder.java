package duke.taskmanagement;

import duke.task.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class TaskRecorder {
    private static final String HOME = System.getProperty("user.dir");
    private static final java.nio.file.Path PATH = java.nio.file.Paths.get(HOME, "data","duke.txt");

    private static ArrayList<Task> taskList = new ArrayList<>();

    public static ArrayList<String[]> loadData() throws IOException {
        checkFileExists();
        ArrayList<String[]> output = new ArrayList<>();
        List<String> lines = Files.readAllLines(PATH);
        for (String line : lines) {
            String[] lineData = line.split("\\|");
            output.add(lineData);
        }
        return output;
    }

    public static void addData(String userInput, int taskCount) throws IOException {
        checkFileExists();
        List<String> lines = Files.readAllLines(PATH);
        String extraLine = taskCount + ".0." + userInput;
        lines.add(extraLine);
        Files.write(PATH, lines);
    }

    public static void deleteData(int taskCount) throws IOException {
        checkFileExists();
        List<String> lines = Files.readAllLines(PATH);
        lines.remove(taskCount);
        Files.write(PATH, lines);
    }
    
    public static void checkFileExists() {
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
            System.out.println("There appears to be a problem creating the data file in the data folder!");
        }
    }

    public static void main(String[] args) throws IOException {
        ArrayList<String[]> test = loadData();
        System.out.println(test.get(0)[2]);
        System.out.println(test.get(1)[2]);
    }
}
