package duke;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadWriteUtil {
    private static final String currDir = System.getProperty("user.dir");

    // Adapted from https://www.sghill.net/how-do-i-make-cross-platform-file-paths-in-java.html
    protected static Path findFile() {
        Path dataDirPath = Paths.get(currDir, "data");
        Path tasksPath = Paths.get(dataDirPath.toString(), "tasks.txt");
        boolean directoryExists = Files.exists(dataDirPath);
        boolean fileExists = Files.exists(tasksPath);
        File tasksFile = new File(tasksPath.toString());

        if (!directoryExists) {
            try {
                Files.createDirectories(dataDirPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!fileExists) {
            try {
                tasksFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return tasksPath;
    }
}
