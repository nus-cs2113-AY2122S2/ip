package duke;

import duke.task.Task;
import org.json.simple.JSONArray;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    private static final String OUT_DIR = "output";    // Honestly create a config file or smth
    private static final String SAVE_PATH = String.format("%s/%s", OUT_DIR, "SAVE.json");

    public String readSaveFile(){
        String saveStr = "";
        try {
            FileReader fr = new FileReader(SAVE_PATH);
            int i;
            while ((i=fr.read()) != -1){
                saveStr += ((char)i);
            }
//            System.out.println(saveStr);
            fr.close();
        } catch (IOException e){
            System.err.println("Failed to open save file!" + e.getMessage());
        }
        return saveStr;
    }

    public void writeSaveFile(String storeStr){
        Path dir = Paths.get(OUT_DIR);
//        File f = new File(OUT_DIR);
        if (!Files.exists(dir)) {   //createTempDirectory
            try {
                Files.createDirectory(Path.of(OUT_DIR));
            } catch (IOException e) {
                System.err.println("Failed to create directory!" + e.getMessage());
            }
        }
//        String filePath = String.format("%s/%s", OUT_DIR, "SAVE.json");
        try {
            FileWriter fw = new FileWriter(SAVE_PATH);
            fw.write(storeStr);
            fw.close();
        } catch (IOException e) {
            System.err.println("Failed to write to save file!" + e.getMessage());
        }

    }

}
