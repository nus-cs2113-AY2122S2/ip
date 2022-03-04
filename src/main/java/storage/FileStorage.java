package storage;

import exceptions.DukeException;
import exceptions.FileCreateDukeException;
import exceptions.FileNotFoundDukeException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * File data access object handle the storage and load of data, abstract the storage and memory
 */
public abstract class FileStorage {
    private String basePath;
    private String fileName;
    protected File targetFile;
    protected static final char START_INDICATOR = '$';

    /**
     * Initializes a file data access object
     *
     * @param basePath The base path for the file
     * @param fileName The name of the file
     * @throws DukeException
     */
    public FileStorage(String basePath, String fileName) throws DukeException {
        targetFile = new File(String.format("%s/%s", basePath, fileName));
        if (!targetFile.exists()) {
            try {
                targetFile = createTargetFile(targetFile);
            } catch (DukeException e) {
                throw e;
            }
        }
    }

    /**
     * Initializes an empty file with metadata.
     *
     * @param taskFile The file to be initialized.
     */
    private File createTargetFile(File taskFile) throws DukeException {
        // Create the directory, if not exists yet.
        if (!taskFile.exists()) {
            try {
                taskFile.getParentFile().mkdirs();
                taskFile.createNewFile();
            } catch (Exception e) {
                throw new FileCreateDukeException();
            }
        }
        try {
            FileOutputStream writeData = new FileOutputStream(taskFile);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            writeStream.writeInt(0);
            writeStream.writeChar(START_INDICATOR);
            writeStream.flush();
            writeStream.close();
            return taskFile;
        } catch (Exception e) {
            throw new FileNotFoundDukeException();
        }
    }


}
