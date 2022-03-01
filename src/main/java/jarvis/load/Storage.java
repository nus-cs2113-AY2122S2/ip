package jarvis.load;
import jarvis.commands.UserList;
import jarvis.display.DisplayMessages;
import jarvis.exceptions.JarvisNoSavedData;

import java.io.File;

public class Storage { //add throw exception
    private static File savedFile;
    public Storage() {
        savedFile = new File("data/Jarvis.txt");
    }

    public static void load() throws JarvisNoSavedData {
        if (hasLoadedFile(savedFile)) {
            DisplayMessages.savedFileDetected();
            UserList.loadFile(savedFile);
            DisplayMessages.fileLoaded();
        } else {
            throw new JarvisNoSavedData();
        }
    }

    protected static boolean hasLoadedFile(File savedFile) {
        return savedFile.exists();
    }
}
