package exceptions;

public class FileCreateDukeException extends DukeException {
    private static final String FILE_CREATE_EXCEPTION_WORDS = " ☹ OOPS!!! Fail to create the file:(";

    @Override
    public String toString() {
        return FILE_CREATE_EXCEPTION_WORDS;
    }

}
