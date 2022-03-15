package duke;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) throws IOException {
        duke.Ui.showGreeting();
        try {
            Storage.loadFileContents();
        } catch (FileNotFoundException e) {
            File file = new File(duke.Storage.FILE_PATH);
            File directory = new File(Storage.FILE_DIRECTORY_PATH);
            if (!directory.exists()) {
                directory.mkdir();
            }
            file.createNewFile();
        }
        Ui.acceptInputs();
        duke.Ui.exit();
    }
}
