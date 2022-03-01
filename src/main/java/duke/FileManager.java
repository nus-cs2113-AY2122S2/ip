package duke;

import java.io.File;
import java.io.IOException;

public class FileManager {
    public FileManager(File file) {
        File duke = file;
        openFile(duke);
    }

    static File openFile(File file) {
        if (file.exists()) {
            return file;
        } else {
            try {
                file.createNewFile();
                return file;
            } catch (IOException e) {
                System.out.println("Error IO");
            } catch (SecurityException e) {
                System.out.println("Error Security");
            }
        }
        return file;
    }
}


