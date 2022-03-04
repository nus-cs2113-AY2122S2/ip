package exceptions;

public class DaoWriteStreamBrokenDukeException extends DukeException {
    private static final String FILE_CREATE_EXCEPTION_WORDS = " ☹ OOPS!!! The write stream brokes";

    @Override
    public String toString() {
        return FILE_CREATE_EXCEPTION_WORDS;
    }

}
