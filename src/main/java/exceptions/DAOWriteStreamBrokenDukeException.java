package exceptions;

public class DAOWriteStreamBrokenDukeException extends DukeException {
    static final private String FILE_CREATE_EXCEPTION_WORDS = " ☹ OOPS!!! The write stream brokes";

    @Override
    public String toString() {
        return FILE_CREATE_EXCEPTION_WORDS;
    }

}
