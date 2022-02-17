package duke.database;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;


public class FileRead {
    public static ArrayList<String> readFileContentByLine(String filePath) {
        ArrayList<String> entryList = new ArrayList<>();
        try {
            File file = new File(filePath); // create a File for the given file path
            Scanner sc = new Scanner(file); // create a Scanner using the File as the source
            while (sc.hasNextLine()) {
                entryList.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found, no data was loaded. However, what can I do for you?");
        }
        return entryList;
    }
}



