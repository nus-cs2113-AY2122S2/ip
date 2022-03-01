package duke;

import java.io.File;
import java.io.IOException;

/**
 * A simple FileManager Class that open the given file.
 */
public class FileManager {

    /**
     * FileManger constructor that store and open the given file.
     * @param file the given file.
     */
    public FileManager(File file) {
        File duke = file;
        openFile(duke);
    }

    private File openFile(File file) {
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


