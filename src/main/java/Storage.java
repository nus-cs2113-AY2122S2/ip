import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Allow user to save data in a .txt file and load the saved data
 * Check if the .txt file exists, able to save tasks in the file, and load previous tasks from file after program restarts
 */
public class Storage {

    private static final String filePath = "data/duke.txt";

    /**
     * Check if user has the .txt file, create new file if it doesn't
     */
    public static void checkFile() {
        File f = new File(filePath);
        File folder = new File("data");
        if (!folder.exists()) {
            folder.mkdir();
        }
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println(UI.ERROR_FAILED_TO_CREATE_FILE);
            }
        }
        System.out.println("full path: " + f.getAbsolutePath());
    }

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Save updated list of tasks in the .txt file
     * @param instructionsList an ArrayList containing list of tasks
     */
    public static void saveToFile(ArrayList<String> instructionsList) {
        String task;
        ArrayList<String> list = new ArrayList<>();
        try {
            for (int i = 1; i <= instructionsList.size(); i++) {
                task = i + ". " + instructionsList.get(i - 1);
                list.add(task);
            }
            writeToFile(filePath, String.valueOf(instructionsList));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Load the list of tasks previously saved in .txt file
     * @param instructionsList an ArrayList containing list of tasks
     */
    public static void loadFromFile(ArrayList<String> instructionsList) {

        try {
            File f = new File(filePath); // create a File for the given file path
            Scanner scan = new Scanner(f); // create a Scanner using the File as the source
            String[] array;
            while (scan.hasNextLine()) {
                String instructions = scan.nextLine();
                instructions = instructions.replace("[", "");
                instructions = instructions.replace("]", "");
                array = instructions.split(", ", 200);
                for (int i = 0; i < array.length; i++) {
                    instructionsList.add("");
                    instructionsList.set(i, array[i]);
                    Task.number++;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
