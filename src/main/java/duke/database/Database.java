package duke.database;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;


/**
 * Storage interface to be implemented.
 */
public interface Database {
    /**
     * Read data from file and store.
     *
     * @param fileName Name of file read from.
     * @throws FileNotFoundException If file does not exist.
     */
    public ArrayList read(String fileName) throws FileNotFoundException;

    /**
     * Saves data from program to external file.
     *
     * @param fileName Name of file to save to
     * @param items List of tasks to store to text file.
     * @throws IOException If data cannot be saved.
     */
    public void save(String filename, ArrayList items) throws IOException;
}
