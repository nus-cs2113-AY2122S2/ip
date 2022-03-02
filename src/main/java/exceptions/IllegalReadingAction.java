package exceptions;

public class IllegalReadingAction extends DukeExceptions {
    protected static final String READING_FILE_ERROR_MSG =
            "Sorry, there's something wrong when loading task, please try again later QnQ";

    @Override
    public String toString() {
        return READING_FILE_ERROR_MSG;
    }
}
