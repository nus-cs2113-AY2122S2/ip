import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileManager {
    public static void create(){
        try {
            File data = new File("data.txt");
            if (data.createNewFile()) {
                System.out.println("No data file detected, I have created a new data file for you!");
            } else {
                System.out.println("I have successfully loaded the data file!");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void save(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter("data.txt",false);
        try {
            fw.write(textToAdd+System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Something went wrong, the data is not successfully saved in the file.");
        }
        fw.close();
    }
}
