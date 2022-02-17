package duke.database;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileWrite {
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


