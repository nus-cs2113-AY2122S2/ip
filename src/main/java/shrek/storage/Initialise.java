package shrek.storage;

import shrek.data.CurrentDirectory;
import shrek.data.ErrorCount;
import shrek.exception.InvalidCommandException;

import java.io.File;
import java.io.IOException;

/**
 * Initialises Shrek.
 */
public class Initialise {
    /**
     * Creates the output file and data directory if absent and if ouput file is present, passes to LoadToShrek handler.
     *
     * @throws InvalidCommandException If there is invalid input from the output file.
     */
    public static void initialiseShrek() throws InvalidCommandException {
        try {
            String currentDirectory = System.getProperty("user.dir");
            CurrentDirectory.OUTPUT_FILE_PATH = currentDirectory + "/data/output.txt";
            File dataDirectory = new File(currentDirectory + "/data");
            if (!dataDirectory.exists()) {
                dataDirectory.mkdir();
            }
            File outputFile = new File(CurrentDirectory.OUTPUT_FILE_PATH);
            if (!outputFile.createNewFile()) {
                LoadToShrek.loadData();
            }
        } catch (IOException e) {
            throw new InvalidCommandException("Invalid input!", ErrorCount.errorCount);
        }
    }
}
