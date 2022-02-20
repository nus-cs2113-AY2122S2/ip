package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileSaver {
    public FileSaver() {
        File dataDirectory = new File("./data");
        if (!dataDirectory.exists()) {
            try {
                Files.createDirectory(Paths.get("./data"));
            } catch (IOException e) {
                System.out.println("Hmm...I cannot create the data directory.");
            }
        }

        File dukeData = new File("./data/dukeData.txt");
        try {
            if (dukeData.createNewFile()) {
                System.out.println("Creating a new data file...");
            }
        } catch (IOException e) {
            System.out.println("Hmm... I cannot create the data file.");
        }
    }

    public void writeToFile(String textToWrite) throws IOException {
        FileWriter fw = new FileWriter("./data/dukeData.txt"); // create a FileWriter in append mode
        fw.write(textToWrite);
        fw.close();
    }
}
