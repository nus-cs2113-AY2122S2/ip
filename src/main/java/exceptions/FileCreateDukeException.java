package exceptions;

public class FileCreateDukeException extends DukeException{
    static final private String FILE_CREATE_EXCEPTION_WORDS = " ☹ OOPS!!! Fail to create the file:(";

    @Override
    public String toString() {
        return FILE_CREATE_EXCEPTION_WORDS;
    }

}
