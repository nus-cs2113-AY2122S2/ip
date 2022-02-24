package exceptions;

public class FileNotFoundDukeException extends DukeException {
    static final private String FILE_CREATE_EXCEPTION_WORDS = " ☹ OOPS!!! Fail to find the file:(";

    @Override
    public String toString() {
        return FILE_CREATE_EXCEPTION_WORDS;
    }

}
