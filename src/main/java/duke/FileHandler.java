package duke;

import java.io.File;

public abstract class FileHandler {

    public static File loadOrCreateSaveFile(String path) {
        File saveFile = new File(path);
        try {
            saveFile.createNewFile();
        } catch (Exception e) {
            System.out.println("Error loading/creating save file: " + e.getMessage());
        }
        return saveFile;
    }

}
