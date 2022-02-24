package duke;

import java.io.File;
import java.io.IOException;

public class FileManager {
    public FileManager() {
    }

    static File openFile() {
        File duke = new File("./duke.txt");
        if (duke.exists()) {
            return duke;
        } else {
            try {
                duke.createNewFile();
                return duke;
            } catch (IOException e) {
                System.out.println("Error IO");
            } catch (SecurityException e) {
                System.out.println("Error Security");
            }
        }
        return duke;
    }
}


