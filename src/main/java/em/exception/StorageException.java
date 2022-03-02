package em.exception;

public class StorageException extends Exception{
    /**
     * List of pre-defined error messages when reading and writing of file.
     */
    public static final String INVALID_FILE = "☹ OOPS!!! File is not found :-(";
    public static final String IO_EXCEPTION = "☹ OOPS!!! Something wrong with the input-output operation.";
    public static final String INVALID_FILE_INPUT = "☹ OOPS!!! The file information is corrupted. " +
            "Please ensure that the data in the file is separated by commas and in the format of\n " +
            "[Task Type, task status, task description, task timing(applicable for event/deadline task)]\n"+
            " For example:\n " + " T,0,read book\n" + "  E,1,meet kelly,Nov-11-2012 12:30PM\n" +
            "Please also ensure that there are no spaces after the commas.";

    /**
     * Constructs an StorageException with a specified error message.
     *
     * @param errorMsg The error message written by the throwing method.
     */
    public StorageException(String errorMsg) {
        super(errorMsg);
    }
}
