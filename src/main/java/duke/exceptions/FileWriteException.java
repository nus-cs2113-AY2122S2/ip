package duke.exceptions;

public class FileWriteException extends DukeException {
    private static final String FILE_IO_ERR_MESSAGE = "Error writing to files while %s.";

    public FileWriteException(String action) {
        super(String.format(FILE_IO_ERR_MESSAGE, action));
    }
}
