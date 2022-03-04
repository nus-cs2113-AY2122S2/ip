package duke.database;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Write program data to external file.
 */
public class FileWrite {
    /**
     * Stores all data into text file.
     *
     * @param filePath file path of text file to be saved to
     * @param textToAdd list of entries separated by rows
     */
    public static void writeToFile(String filePath, ArrayList<String> textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        try {
            for (String s : textToAdd) {
                fw.write(s);
                fw.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Failed to save data");
        } finally {
            fw.close();
            System.out.println("Your data has been saved");
        }
    }
}


