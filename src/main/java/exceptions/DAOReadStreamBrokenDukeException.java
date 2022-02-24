package exceptions;

public class DAOReadStreamBrokenDukeException extends DukeException {
    static final private String FILE_CREATE_EXCEPTION_WORDS = " ☹ OOPS!!! The read stream brokes";

    @Override
    public String toString() {
        return FILE_CREATE_EXCEPTION_WORDS;
    }

}
