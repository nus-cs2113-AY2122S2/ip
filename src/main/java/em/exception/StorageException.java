package em.exception;

public class StorageException extends Exception{
    /**
     * List of pre-defined error messages when reading and writing of file.
     */
    public static final String INVALID_FILE = "☹ OOPS!!! File is not found :-(";
    public static final String IO_EXCEPTION = "☹ OOPS!!! Something wrong with the input-output operation.";
    public static final String INVALID_FILE_INPUT = "☹ OOPS!!! The file information is corrupted.";

    /**
     * Constructs an StorageException with a specified error message.
     *
     * @param errorMsg The error message written by the throwing method.
     */
    public StorageException(String errorMsg) {
        super(errorMsg);
    }
}
