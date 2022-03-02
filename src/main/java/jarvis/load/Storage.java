package jarvis.load;
import jarvis.commands.TaskList;
import jarvis.display.Ui;
import jarvis.exceptions.JarvisNoSavedData;

import java.io.File;

public class Storage {
    private static File savedFile;

    /**
     * Constructor for the Storage class.
     */
    public Storage() {
        savedFile = new File("data/Jarvis.txt");
    }

    /**
     * Function to load any saved file by the user.
     *
     * @throws JarvisNoSavedData exception to signal that the user does not have any saved file.
     */
    public static void load() throws JarvisNoSavedData {
        if (hasLoadedFile(savedFile)) {
            Ui.savedFileDetected();
            TaskList.loadFile(savedFile);
            Ui.fileLoaded();
        } else {
            throw new JarvisNoSavedData();
        }
    }

    /**
     * Function checks if the file exists and returns a boolean to indicate the existence.
     *
     * @param savedFile File argument to check for existence
     * @return True if file does exist. False otherwise
     */
    protected static boolean hasLoadedFile(File savedFile) {
        return savedFile.exists();
    }
}
