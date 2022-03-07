package exceptions;

public class DaoReadStreamBrokenDukeException extends DukeException {
    private static final String FILE_CREATE_EXCEPTION_WORDS = " ☹ OOPS!!! The read stream brokes";

    @Override
    public String toString() {
        return FILE_CREATE_EXCEPTION_WORDS;
    }

}
