package duke;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) throws IOException {
        duke.Ui.greet();
        try {
            Storage.loadFileContents();
        } catch (FileNotFoundException e) {
            File file = new File(duke.Storage.FILE_PATH);
            file.createNewFile();
        }
        Ui.acceptInputs();
        duke.Ui.exit();
    }
}
