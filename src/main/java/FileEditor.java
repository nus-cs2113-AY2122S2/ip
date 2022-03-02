import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileEditor {
    private static final String DIRECTORY = "data";
    private static final String FILENAME = "duke.txt";
    private static final String FILEPATH = DIRECTORY + File.separator + FILENAME;

    public static void updateFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(FILEPATH);
        for (Task task : tasks) {
            String taskToAppend = task.getDetails();
            fw.write(taskToAppend + System.lineSeparator());
        }
        fw.close();
    }

    public ArrayList<String> readFileContents() throws IOException {
        File directory = new File(DIRECTORY);
        if(!directory.exists()) {
            directory.mkdir();
        }
        File file = new File(DIRECTORY + File.separator + FILENAME);
        if(!file.exists()) {
            file.createNewFile();
        }
        Scanner s = new Scanner(file);
        ArrayList<String> tasksFromFile = new ArrayList<>();
        while (s.hasNextLine()) {
            tasksFromFile.add(s.nextLine());
        }
        return tasksFromFile;
    }
}
